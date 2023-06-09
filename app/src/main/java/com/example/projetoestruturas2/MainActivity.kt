package com.example.projetoestruturas2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projetoestruturas2.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Suppress("UNCHECKED_CAST")
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
                    val h11 = (result.get("h11") as ArrayList<Int>).toIntArray()
                    val h15 = (result.get("h15") as ArrayList<Int>).toIntArray()
                    val cta = (result.get("cta") as ArrayList<Int>).toIntArray()
                    val ctb = (result.get("ctb") as ArrayList<Int>).toIntArray()
                    val h14 = (result.get("h14") as ArrayList<Int>).toIntArray()
                    val h12 = (result.get("h12") as ArrayList<Int>).toIntArray()
                    val h9 = (result.get("h9") as ArrayList<Int>).toIntArray()

                    matrix = arrayOf(h11,h15,cta,ctb,h14,h12,h9)
                binding.bCalculo.setOnClickListener {
                    val dijkstra = MetodosCalculo(matrix)
                    when{
                        (binding.etPI.text!!.toString() == "7" || binding.etPI.text!!.toString() == "8" || binding.etPI.text!!.toString() == "9") -> binding.etPI.error = "Digite um numero entre 0 e 6"
                        (binding.etPF.text!!.toString() == "7" || binding.etPF.text!!.toString() == "8" || binding.etPF.text!!.toString() == "9") -> binding.etPF.error = "Digite um numero entre 0 e 6"
                        binding.etPI.text!!.isEmpty() -> binding.etPI.error = "Digite o prédio inicial"
                        binding.etPF.text!!.isEmpty() -> binding.etPF.error = "Digite o prédio destino"
                        else -> binding.tvResultado.text= dijkstra.cCaminho(Integer.parseInt(binding.etPI.text.toString()), Integer.parseInt(binding.etPF.text.toString()))
                    }
                }
            }
    }
}