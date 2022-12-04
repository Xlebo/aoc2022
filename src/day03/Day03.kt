package day03

import getOrFetchInputData
import readInput

fun main() {

    fun getValueOfItem(item: Char): Int {
        return if (item.isLowerCase()) {
            item.code - 96
        } else {
            item.code - 38
        }
    }

    fun part1(input: List<String>): Int {
        return input.map { Pair(it.substring(0, it.length / 2), it.substring(it.length / 2, it.length)) }
            .map {
                pair ->
                pair.first.first { pair.second.contains(it) }
            }
            .sumOf { getValueOfItem(it) }
    }

    fun part2(input: List<String>): Int {
        return input.chunked(3)
            .map { group -> group[0].first {
                group[1].contains(it) && group[2].contains(it)
            } }
            .sumOf { getValueOfItem(it) }
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test", "day03")
    val result1 = part1(testInput)
    val result2 = part2(testInput)
    check(result1 == 157) { "Got: $result1" }
    check(result2 == 70) { "Got: $result2" }

    val input = getOrFetchInputData(3)
    println(part1(input))
    println(part2(input))
}
