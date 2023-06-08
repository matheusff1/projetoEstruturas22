package com.example.projetoestruturas2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projetoestruturas2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val matriz = arrayOf( /*h11*/ intArrayOf(0, 40,122, 140,0,0,33),/*h15*/ intArrayOf(40, 0, 87,0,0,180,97),
            /*cta*/intArrayOf(122,81,0,30,0,0,0), /*ctb*/intArrayOf(140,0,30,0,60,131,0),
            /*h14*/ intArrayOf(0,0,0,60,0,121,0),/*h12*/intArrayOf(0,180,0,151,121,0,153), /*h9*/ intArrayOf(33,77,0,0,0,153,0)
        )

        val dijkstra = MetodosCalculo(matriz)

        val origin = 1
        val destination =4

        binding.bCalculo.setOnClickListener {
            if(binding.etPF.text!!.isEmpty() || binding.etPI.text!!.isEmpty()){
                binding.etPI.error = "Digite o prédio inicial"
                binding.etPF.error = "Digite o prédio destino"
            } else {
                val origem  = binding.etPI.toString().toInt()
                val destino =  binding.etPF.toString().toInt()
                binding.tvResultado.text= dijkstra.cCaminho(origem, destino)

            }
        }

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