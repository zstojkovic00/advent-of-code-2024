package zeljko.com

import java.io.File
import kotlin.math.abs


fun main(args: Array<String>) {

    partOne()
    partTwo()
}

private fun partTwo() {
    var similarityScore = 0;
    var leftNumbers = mutableListOf<Int>()
    var rightNumbers = mutableListOf<Int>()

    File("src/main/resources/day1.txt").forEachLine { line ->
        val parts = line.split("   ")
        leftNumbers.add(parts[0].toInt())
        rightNumbers.add(parts[1].toInt())
    }

    for (i in leftNumbers) {
        var times = 0
        for (j in rightNumbers) {
            if (i == j) {
                times++
            }
        }
        similarityScore += (i * times)
    }

    println(similarityScore)
}

private fun partOne() {
    var distance = 0;
    var leftNumbers = mutableListOf<Int>()
    var rightNumbers = mutableListOf<Int>()

    File("src/main/resources/day1.txt").forEachLine { line ->
        val parts = line.split("   ")
        leftNumbers.add(parts[0].toInt())
        rightNumbers.add(parts[1].toInt())
    }

    leftNumbers.sort()
    rightNumbers.sort()

    leftNumbers.zip(rightNumbers).forEach { pair ->
        distance += abs(pair.first - pair.second)
    }

    println(distance)
}


