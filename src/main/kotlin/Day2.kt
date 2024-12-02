package zeljko.com

import java.io.File
import kotlin.math.abs

fun main(args: Array<String>) {

    partOne()
}


/*
    The engineers are trying to figure out which reports are safe. The Red-Nosed reactor safety systems can only tolerate levels that are either gradually increasing or gradually decreasing. So, a report only counts as safe if both of the following are true:

    The levels are either all increasing or all decreasing.
    Any two adjacent levels differ by at least one and at most three.
 */
private fun partOne() {
    var times = 0

    File("src/main/resources/day2_input.txt").forEachLine { line ->
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




