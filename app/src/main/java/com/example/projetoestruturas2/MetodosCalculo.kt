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

        return "O caminho mais curto entre $origem e $destino leva ${dist[destino]} segundos ou aproximadamente ${(dist[destino])/60} minutos. O caminho foi: $lista."
    }
}