package zeljko.com

import java.io.File
import kotlin.math.abs


fun main(args: Array<String>) {

    partOne()
    partTwo()
}

/*
       Part two:

       This time, you'll need to figure out exactly how often each number from the left list
       appears in the right list. Calculate a total similarity score by adding up each number in the left list
       after multiplying it by the number fo times that number appears in the right list.
 */

private fun partTwo() {
    var similarityScore = 0;
    var leftNumbers = mutableListOf<Int>()
    var rightNumbers = mutableListOf<Int>()

    File("src/main/resources/day1_input.txt").forEachLine { line ->
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


/*
    Part one:

    Pair up the smallest number in the left list with the smallest number in the right list,
    then the second-smallest left number with the second-smallest right number, and so on.

    Within each pair, figure out how far apart the two numbers are; you'll need to add up all of those distances.
    For example:  if you pair up a 3 from the left list with a 7 from right list, the distance apart is 4;
    if you pair up a 9 with a 3, the distance apart is 6.
 */
private fun partOne() {
    var distance = 0;
    var leftNumbers = mutableListOf<Int>()
    var rightNumbers = mutableListOf<Int>()

    File("src/main/resources/day1_input.txt").forEachLine { line ->
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


