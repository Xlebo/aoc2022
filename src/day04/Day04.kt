package day04

import getOrFetchInputData
import readInput

fun main() {

    fun List<String>.asSections(): List<Pair<Pair<Int, Int>, Pair<Int, Int>>> =
        this.map { row ->
            row.split(",")
                .map { section -> section.split("-").map { it.toInt() } }
                .map { Pair(it[0], it[1]) }
        }
            .map { Pair(it[0], it[1]) }

    fun part1(input: List<String>): Int {
        return input.asSections()
            .count {
                (it.first.first >= it.second.first && it.first.second <= it.second.second) ||
                        (it.first.first <= it.second.first && it.first.second >= it.second.second)
            }

    }

    fun part2(input: List<String>): Int {
        return input.asSections()
            .count {
                (it.first.first <= it.second.first && it.first.second >= it.second.first) ||
                        (it.first.first >= it.second.first && it.first.first <= it.second.second)
            }
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test", "day04")
    val result1 = part1(testInput)
    val result2 = part2(testInput)
    check(result1 == 2) { "Got: $result1" }
    check(result2 == 4) { "Got: $result2" }

    val input = getOrFetchInputData(4)
    println(part1(input))
    println(part2(input))
}
