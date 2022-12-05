package day02

import getOrFetchInputData
import readInput
import kotlin.math.abs

fun main() {

    val enemyInput = mapOf('A' to 1, 'B' to 2, 'C' to 3)
    val playerInput = mapOf('X' to 1, 'Y' to 2, 'Z' to 3)

    val incrementBasedOnResult = mapOf('X' to -1, 'Y' to 0, 'Z' to 1)

    fun determinePlayerInput(enemy: Char, result: Char): Int {
        var playerScore = enemyInput[enemy]!! + incrementBasedOnResult[result]!!
        playerScore = playerScore.mod(3)
        return if (playerScore == 0) {
            3
        } else {
            playerScore
        }
    }

    fun calculateScore(enemy: Char, player: Char): Int {
        val selectionScore = playerInput[player]!!
        var score = selectionScore - enemyInput[enemy]!!
        if (abs(score) > 1)
            score /= -2
        return (score + 1) * 3 + selectionScore
    }

    fun part1(input: List<String>): Int {
        return input
            .map {
                it
                    .split(" ")
                    .map { play -> play[0] }
            }.sumOf { calculateScore(it[0], it[1]) }
    }

    fun part2(input: List<String>): Int {
        return input
            .map {
                it
                    .split(" ")
                    .map { play -> play[0] }
            }
            .sumOf { determinePlayerInput(it[0], it[1]) + (incrementBasedOnResult[it[1]]!! + 1) * 3 }
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test", "day02")
    val result1 = part1(testInput)
    val result2 = part2(testInput)
    check(result1 == 15) { "Got: $result1" }
    check(result2 == 12) { "Got: $result2" }

    val input = getOrFetchInputData(2)
    println(part1(input))
    println(part2(input))
}
