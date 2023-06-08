package com.example.projetoestruturas2

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.projetoestruturas2.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var matrix: Array<IntArray>

        db.collection("matriz")
            .document("matrizz")
            .get()
            .addOnSuccessListener { result ->
                    val h11 = intArrayOf(Integer.parseInt(result["h11"].toString()))
                    val h15 = intArrayOf(Integer.parseInt(result["h15"].toString()))
                    val cta = intArrayOf(Integer.parseInt(result["cta"].toString()))
                    val ctb = intArrayOf(Integer.parseInt(result["ctb"].toString()))
                    val h14 = intArrayOf(Integer.parseInt(result["h14"].toString()))
                    val h12 = intArrayOf(Integer.parseInt(result["h12"].toString()))
                    val h9 = intArrayOf(Integer.parseInt(result["h9"].toString()))
                    matrix = arrayOf(h11,h15,cta,ctb,h14,h12,h9)

                binding.bCalculo.setOnClickListener {
                    if(binding.etPF.text!!.isEmpty() || binding.etPI.text!!.isEmpty()){
                        binding.etPI.error = "Digite o prédio inicial"
                        binding.etPF.error = "Digite o prédio destino"
                    } else {

                        val dijkstra = MetodosCalculo(matrix)
                        binding.tvResultado.text= dijkstra.cCaminho(Integer.parseInt(binding.etPI.text.toString()), Integer.parseInt(binding.etPF.text.toString()))

                    }
                }
                }

        /*arrayOf( /*h11*/ intArrayOf(0, 40,122, 140,0,0,33),/*h15*/ intArrayOf(40, 0, 87,0,0,180,97),
            /*cta*/intArrayOf(122,81,0,30,0,0,0), /*ctb*/intArrayOf(140,0,30,0,60,131,0),
            /*h14*/ intArrayOf(0,0,0,60,0,121,0),/*h12*/intArrayOf(0,180,0,151,121,0,153), /*h9*/ intArrayOf(33,97,0,0,0,153,0)
        )*/

        val matriz = arrayOf( /*h11*/ intArrayOf(0, 40,122, 140,0,0,33),/*h15*/ intArrayOf(40, 0, 87,0,0,180,97),
            /*cta*/intArrayOf(122,81,0,30,0,0,0), /*ctb*/intArrayOf(140,0,30,0,60,131,0),
            /*h14*/ intArrayOf(0,0,0,60,0,121,0),/*h12*/intArrayOf(0,180,0,151,121,0,153), /*h9*/ intArrayOf(33,97,0,0,0,153,0)
        )



       /* binding.bCalculo.setOnClickListener {
            if(binding.etPF.text!!.isEmpty() || binding.etPI.text!!.isEmpty()){
                binding.etPI.error = "Digite o prédio inicial"
                binding.etPF.error = "Digite o prédio destino"
            } else {

                val dijkstra = MetodosCalculo(matrix)
                binding.tvResultado.text= dijkstra.cCaminho(Integer.parseInt(binding.etPI.text.toString()), Integer.parseInt(binding.etPF.text.toString()))

            }
        } */

    }
    private fun caixinhasDeTexto1(): Boolean {
        return !(binding.etPI.text.toString()!="0"
                || binding.etPI.text.toString()!="1"
                || binding.etPI.text.toString()!="2"
                || binding.etPI.text.toString()!="3"
                || binding.etPI.text.toString()!="4"
                || binding.etPI.text.toString()!="5"
                || binding.etPI.text.toString()!="6")
    }

    private fun caixinhaDeTexto2(): Boolean{
        return !(binding.etPF.text.toString()!="0"
                || binding.etPF.text.toString()!="1"
                || binding.etPF.text.toString()!="2"
                || binding.etPF.text.toString()!="3"
                || binding.etPF.text.toString()!="4"
                || binding.etPF.text.toString()!="5"
                || binding.etPF.text.toString()!="6")
    }

}