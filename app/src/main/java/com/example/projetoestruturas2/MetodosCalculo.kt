package com.example.projetoestruturas2

import java.util.*

class MetodosCalculo(private val matrix: Array<IntArray>) {
    private val vertices: Int = matrix.size

    private fun minDistance(dist: IntArray, visited: BooleanArray): Int {
        var min = Int.MAX_VALUE
        var minIndex = -1

        for (v in 0 until vertices) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v]
                minIndex = v
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
            val u = minDistance(dist, visited)
            visited[u] = true

            for (v in 0 until vertices) {
                if (!visited[v] && matrix[u][v] != 0 && dist[u] != Int.MAX_VALUE
                    && dist[u] + matrix[u][v] < dist[v]) {
                    dist[v] = dist[u] + matrix[u][v]
                    prev[v] = u
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