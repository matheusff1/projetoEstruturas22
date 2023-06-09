package com.example.projetoestruturas2

import java.util.*

class MetodosCalculo(private val matriz: Array<IntArray>) {
    private val vertices: Int = matriz.size

    private fun distanciaMinima(dist: IntArray, visitado: BooleanArray): Int {
        var min = Int.MAX_VALUE
        var minIndice = -1

        for (j in 0 until vertices) {
            if (!visitado[j] && dist[j] <= min) {
                min = dist[j]
                minIndice = j
            }
        }

        return minIndice
    }

    fun cCaminho(origem: Int, destino: Int): String {
        val dist = IntArray(vertices) { Int.MAX_VALUE }
        val visitado = BooleanArray(vertices)
        val prev = IntArray(vertices) { -1 }

        dist[origem] = 0

        for (count in 0 until vertices - 1) {
            val i = distanciaMinima(dist, visitado)
            visitado[i] = true

            for (j in 0 until vertices) {
                if (!visitado[j] && matriz[i][j] != 0 && dist[i] != Int.MAX_VALUE
                    && dist[i] + matriz[i][j] < dist[j]
                ) {
                    dist[j] = dist[i] + matriz[i][j]
                    prev[j] = i
                }
            }
        }

        return mostrarSolucao(dist, prev, origem, destino)
    }

    private fun mostrarSolucao(dist: IntArray, prev: IntArray, origem: Int, destino: Int): String {
        var a=""
        var b=""
        if (dist[destino] == Int.MAX_VALUE) {
            return "ERRO, nehum caminho foi encontrado entre $origem e $destino"
        }

        val caminho = LinkedList<Int>()
        var no = destino
        while (no != -1) {
            caminho.addFirst(no)
            no = prev[no]
        }

        val lista = mutableListOf<Int>()

        for (i in caminho) {
            lista.add(i)
        }
        when (origem) {
            0 -> a= "H11"
            1 -> a= "H15"
            2 -> a= "CTA"
            3 -> a= "CTB"
            4 -> a= "H14"
            5 -> a= "H12"
            6 -> a= "H9"
        }
        when (destino) {
            0 -> b= "H11"
            1 -> b= "H15"
            2 -> b= "CTA"
            3 -> b= "CTB"
            4 -> b= "H14"
            5 -> b= "H12"
            6 -> b= "H9"
        }


        return "O caminho mais curto entre $a e $b leva ${dist[destino]} segundos ou aproximadamente ${(dist[destino]).toFloat()/60} minutos. O caminho foi: $lista."
    }
}