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

        val matrix = arrayOf( /*h11*/ intArrayOf(0, 40,122, 140,0,0),/*h15*/ intArrayOf(40, 0, 87,0,0,180),
            /*cta*/intArrayOf(122,81,0,30,0,0), /*ctb*/intArrayOf(140,0,30,0,60,131),
            /*h14*/ intArrayOf(0,0,0,60,0,121),/*h12*/intArrayOf(0,180,0,151,121,0))

        val dijkstra = MetodosCalculo(matrix)

        val origin = 0
        val destination = 2

        binding.bCalculo.setOnClickListener {
            if(binding.etPF.text!!.isEmpty() || binding.etPI.text!!.isEmpty()){
                binding.etPF.error = "Digite o prédio inicial"
                binding.etPI.error = "Digite o prédio destino"
            } else {
                if(caixinhasDeTexto() ) {
                    binding.tvResultado.text= dijkstra.shortestPath(origin, destination)
                } else{
                    binding.tvResultado.text= dijkstra.shortestPath(origin, destination)
                    binding.etPI.error = "Digite apenas o nome do prédio, exemplo: H15"
                    binding.etPF.error = "Digite apenas o nome do prédio, exemplo: H15"


                }
            }
        }

    }
    private fun caixinhasDeTexto(): Boolean {
        if (binding.etPI.text.toString().uppercase()!="CTA"
                || binding.etPI.text.toString().uppercase()!="H15"
                || binding.etPI.text.toString().uppercase()!="H11"
                || binding.etPI.text.toString().uppercase()!="H14"
                || binding.etPI.text.toString().uppercase()!="H12"
                || binding.etPI.text.toString().uppercase()!="CTB"
                &&
                binding.etPF.text.toString().uppercase()!="CTA"
                || binding.etPF.text.toString().uppercase()!="H15"
                || binding.etPF.text.toString().uppercase()!="H11"
                || binding.etPF.text.toString().uppercase()!="H14"
                || binding.etPF.text.toString().uppercase()!="H12"
                || binding.etPF.text.toString().uppercase()!="CTB") {
            return false
        } else{
            return true
        }
    }







}