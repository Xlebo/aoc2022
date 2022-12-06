package day05

import getOrFetchInputData
import readInput
import java.util.*

fun main() {

    val operation1: (Int, Stack<Char>, Stack<Char>) -> Unit = { amount: Int, base: Stack<Char>, target: Stack<Char> ->
        repeat(amount) { target.add(base.pop()) }
    }

    val operation2: (Int, Stack<Char>, Stack<Char>) -> Unit = { amount: Int, base: Stack<Char>, target: Stack<Char> ->
        if (amount == 1) {
            target.push(base.pop())
        } else {
            val temp = Stack<Char>()
            repeat(amount) {
                temp.push(base.pop())
            }
            repeat(amount) {
                target.push(temp.pop())
            }
        }
    }

    fun part1(input: List<String>, operation: (amount: Int, base: Stack<Char>, target: Stack<Char>) -> Unit = operation1): String {
        val bottomRegex = Regex("[\\s|\\d]+")
        val bottom = input.indexOfFirst { bottomRegex.matches(it) }

        val cratesRegex = Regex("(\\s{3})\\s|(\\[.])")
        val stacks = mutableListOf<Stack<Char>>()
        (bottom - 1 downTo 0).forEach {
            val matchesInRow = cratesRegex.findAll(input[it])
            var matchCounter = 0
            matchesInRow.forEach {
                if (stacks.size == matchCounter) {
                    stacks += Stack<Char>()
                }
                val value = it.value[1]
                if (value != ' ') {
                    stacks[matchCounter].push(value)
                }
                matchCounter++
            }
        }
        var operationCounter = bottom + 2

        val operationsRegex = Regex("move (\\d+)|from (\\d+)|to (\\d+)")
        while (input.size > operationCounter) {
            val matchesInRow = operationsRegex.findAll(input[operationCounter])
                .map { match ->
                    match.groupValues.first { it.toIntOrNull() != null }
                }
                .map { it.toInt() }
                .toList()
            operation(matchesInRow[0], stacks[matchesInRow[1] - 1], stacks[matchesInRow[2] - 1])
            operationCounter++
        }
        return stacks.map { it.pop() }.joinToString("")
    }

    fun part2(input: List<String>): String {
        return part1(input, operation2)
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test", "day05")
    val result1 = part1(testInput)
    val result2 = part2(testInput)
    check(result1 == "CMZ") { "Got: $result1" }
    check(result2 == "MCD") { "Got: $result2" }

    val input = getOrFetchInputData(5)
    println(part1(input))
    println(part2(input))
}
