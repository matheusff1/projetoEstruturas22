package com.example.projetoestruturas2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val matrix = arrayOf( /*h11*/ intArrayOf(0, 40,122, 140,0,0),/*h15*/ intArrayOf(40, 0, 87,0,0,180),
            /*cta*/intArrayOf(122,81,0,30,0,0), /*ctb*/intArrayOf(140,0,30,0,60,131),
            /*h14*/ intArrayOf(0,0,0,60,0,121),/*h12*/intArrayOf(0,180,0,151,121,0))

        val dijkstra = MetodosCalculo(matrix)

        val origin = 0
        val destination = 2
        dijkstra.shortestPath(origin, destination)
    }





}