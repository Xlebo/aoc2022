package day01

import getOrFetchInputData
import readInput

fun main() {
    fun List<String>.asGruppedIntegers(): List<List<Int>> =
        fold(mutableListOf(mutableListOf<Int>())) { acc, item ->
            if (item.isBlank()) {
                acc.add(mutableListOf())
            } else {
                acc.last().add(item.toInt())
            }
            acc
        }

    fun part1(input: List<String>): Int {
        return input.asGruppedIntegers()
            .maxOfOrNull { it.sum() } ?: 0
    }

    fun part2(input: List<String>): Int {
        return input.asGruppedIntegers()
            .map { it.sum() }
            .sortedDescending()
            .take(3)
            .sum()
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test", "day01")
    val result1 = part1(testInput)
    val result2 = part2(testInput)
    check(result1 == 24000) { "Got: $result1" }
    check(result2 == 45000) { "Got: $result2" }

    val input = getOrFetchInputData(1)
    println(part1(input))
    println(part2(input))
}
