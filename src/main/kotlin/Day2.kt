package zeljko.com

import java.io.File
import kotlin.math.abs

fun main(args: Array<String>) {

    partOne()
    partTwo()
}


/*
    The engineers are trying to figure out which reports are safe.
    The Red-Nosed reactor safety systems can only tolerate levels that are either
    gradually increasing or gradually decreasing.
    So, a report only counts as safe if both of the following are true:

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

/*
The engineers are surprised by the low number of safe reports
until they realize they forgot to tell you about the Problem Dampener.
The Problem Dampener is a reactor-mounted module that lets the reactor safety systems tolerate a
single bad level in what would otherwise be a safe report. It's like the bad level never happened!

More of the above example's reports are now safe:

7 6 4 2 1: Safe without removing any level.
1 2 7 8 9: Unsafe regardless of which level is removed.
9 7 6 2 1: Unsafe regardless of which level is removed.
1 3 2 4 5: Safe by removing the second level, 3.
8 6 4 4 1: Safe by removing the third level, 4.
1 3 6 7 9: Safe without removing any level.
 */


private fun partTwo() {
    var times = 0

    File("src/main/resources/day2_input.txt").forEachLine { line ->
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




