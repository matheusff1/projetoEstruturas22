package com.example.projetoestruturas2

import java.util.*

class MetodosCalculo(private val matriz: Array<IntArray>) {
    private val vertices: Int = matriz.size

    private fun minDistance(dist: IntArray, visited: BooleanArray): Int {
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

    fun shortestPath(origin: Int, destination: Int) {
        val dist = IntArray(vertices) { Int.MAX_VALUE }
        val visited = BooleanArray(vertices)
        val prev = IntArray(vertices) { -1 }

        dist[origin] = 0

        for (count in 0 until vertices - 1) {
            val i = minDistance(dist, visited)
            visited[i] = true

            for (j in 0 until vertices) {
                if (!visited[j] && matriz[i][j] != 0 && dist[i] != Int.MAX_VALUE
                    && dist[i] + matriz[i][j] < dist[j]) {
                    dist[j] = dist[i] + matriz[i][j]
                    prev[j] = i
                }
            }
        }

        printSolution(dist, prev, origin, destination)
    }

    private fun printSolution(dist: IntArray, prev: IntArray, origin: Int, destination: Int) {
        if (dist[destination] == Int.MAX_VALUE) {
            println("No path found from $origin to $destination")
            return
        }

        val path = LinkedList<Int>()
        var node = destination
        while (node != -1) {
            path.addFirst(node)
            node = prev[node]
        }

        println("Shortest path from $origin to $destination:")
        for (i in path) {
            print("$i ")
        }
        println("\nTotal distance: ${dist[destination]}")
    }
}