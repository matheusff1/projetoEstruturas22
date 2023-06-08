package com.example.projetoestruturas2

import java.util.*

class MetodosCalculo(private val matriz: Array<IntArray>) {
    private val vertices: Int = matriz.size

    private fun distanciaMinima(dist: IntArray, visited: BooleanArray): Int {
        var min = Int.MAX_VALUE
        var minIndex = -1

        for (j in 0 until vertices) {
            if (!visited[j] && dist[j] <= min) {
                min = dist[j]
                minIndex = j
            }
        }

        return minIndex
    }

    fun cCaminho(origem: Int, destino: Int): String {
        val dist = IntArray(vertices) { Int.MAX_VALUE }
        val visited = BooleanArray(vertices)
        val prev = IntArray(vertices) { -1 }

        dist[origem] = 0

        for (count in 0 until vertices - 1) {
            val i = distanciaMinima(dist, visited)
            visited[i] = true

            for (j in 0 until vertices) {
                if (!visited[j] && matriz[i][j] != 0 && dist[i] != Int.MAX_VALUE
                    && dist[i] + matriz[i][j] < dist[j]
                ) {
                    dist[j] = dist[i] + matriz[i][j]
                    prev[j] = i
                }
            }
        }

        return mostrarSolucao(dist, prev, origem, destino)
    }

    private fun mostrarSolucao(dist: IntArray, prev: IntArray, origin: Int, destination: Int): String {
        if (dist[destination] == Int.MAX_VALUE) {
            return "ERRO, nehum caminho foi encontrado entre $origin e $destination"
        }

        val path = LinkedList<Int>()
        var node = destination
        while (node != -1) {
            path.addFirst(node)
            node = prev[node]
        }

        val Lista = mutableListOf<Int>()

        println("Caminho mais curto entre $origin e $destination:")
        for (i in path) {
            print("$i ")
            Lista.add(i)
        }
        println("\nDistância total: ${dist[destination]}")

        return "O caminho mais curto entre $origin e $destination leva ${dist[destination]} segundos. O caminho foi: $Lista."
    }
}