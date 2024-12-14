package zeljko.com

import java.io.File

fun main() {

    val input = File("src/main/resources/day3.txt").readLines()
    partOne(input)
    partTwo(input)
}

private fun partOne(input: List<String>) {
    val pattern = Regex("mul\\(\\d+,\\d+\\)")
    val muls = pattern.findAll(input.toString())
    var result = 0;
    muls.forEach { mul ->
        val values = mul.value.substringAfter("mul(").substringBefore(")")
        val (a, b) = values.split(",").map { it.toInt() }
        result += a * b
    }

    println(result)
}

private fun partTwo(input: List<String>) {
    val pattern = Regex("mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)")
    val muls = pattern.findAll(input.toString())
    var result = 0
    var enabled = true

    muls.forEach { mul ->
        println(mul.value)
        when (mul.value) {
            "do()" -> enabled = true
            "don't()" -> enabled = false
            else -> if (enabled) {
                val values = mul.value.substringAfter("mul(").substringBefore(")")
                val (a, b) = values.split(",").map { it.toInt() }
                result += a * b
            }
        }
    }
    println(result)
}

