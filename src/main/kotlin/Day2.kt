package zeljko.com

import java.io.File
import kotlin.math.abs

fun main(args: Array<String>) {

    partOne()
    partTwo()
}

private fun partOne() {
    var times = 0

    File("src/main/resources/day2.txt").forEachLine { line ->
        val levels = line.split(" ").map { it.toInt() }

        if (isReportSafe(levels)) {
            times++
        }
    }
    println(times)
}

private fun isReportSafe(levels: List<Int>): Boolean {
    val isIncreasing = levels[0] < levels[1]

    for (i in 0 until levels.size - 1) {
        val difference = abs(levels[i] - levels[i + 1])

        if (isIncreasing) {
            if (levels[i] >= levels[i + 1] || difference !in 1..3) {
                return false
            }
        } else {
            if (levels[i] <= levels[i + 1] || difference !in 1..3) {
                return false
            }
        }
    }
    return true
}

private fun partTwo() {
    var times = 0

    File("src/main/resources/day2.txt").forEachLine { line ->
        val levels = line.split(" ").map { it.toInt() }

        if (isReportSafe(levels)) {
            times++
        } else {
            val singleBadLevel = tryAgain(levels)
            if (singleBadLevel) {
                times++
            }
        }
    }
    println(times)
}


private fun tryAgain(levels: List<Int>): Boolean {
    for (i in levels.indices) {
        val l = levels.take(i) + levels.drop(i + 1)
        if (isReportSafe(l)) {
            return true
        }
    }
    return false
}




