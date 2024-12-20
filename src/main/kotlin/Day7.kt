package zeljko.com

import java.io.File

private const val pathName = "src/main/resources/day7.txt"

fun main() {
    val input = File(pathName).readLines()
    println(bridgeRepair(input))
}

fun bridgeRepair(input: List<String>): Long {
    var result: Long = 0
    input.forEach { line ->
        val numbers = line.substringAfter(": ").split(" ").map { it.toLong() }.toList()
        val value = line.substringBefore(":").toLong()

        if (isValueValid(numbers, value)) {
            result += value
        }
    }

    return result
}

private fun isValueValid(numbers: List<Long>, value: Long, i: Int = 1, current: Long = numbers[0]): Boolean {
    if (i == numbers.size) {
        return current == value
    }

    if (isValueValid(numbers, value, i + 1, current + numbers[i])) {
        return true
    }
    if (isValueValid(numbers, value, i + 1, current.concatenation(numbers[i]))) {
        return true
    }

    return isValueValid(numbers, value, i + 1, current * numbers[i])
}

private fun Long.concatenation(l: Long): Long {
    return "$this$l".toLong()
}
