package zeljko.com

import java.io.File


fun main() {

    val input = File("src/main/resources/day4.txt").readLines()
    val times = partOne(input)
    println(times)
    val times2 = partTwo(input)
    println(times2)

}

private fun partOne(input: List<String>): Int {
    var times = 0
    for (i in input.indices) {
        for (j in input[i].indices) {
            when {

                input[i][j] == 'X' -> {

                    // horizontal right
                    if (j + 3 < input[i].length) {
                        if (input[i][j + 1] == 'M' &&
                            input[i][j + 2] == 'A' &&
                            input[i][j + 3] == 'S'
                        )
                            times++
                    }

                    // horizontal left
                    if (j >= 3) {
                        if (input[i][j - 1] == 'M' &&
                            input[i][j - 2] == 'A' &&
                            input[i][j - 3] == 'S'
                        )
                            times++
                    }

                    // vertical up
                    if (i >= 3) {
                        if (input[i - 1][j] == 'M' &&
                            input[i - 2][j] == 'A' &&
                            input[i - 3][j] == 'S'
                        ) {
                            times++
                        }
                    }

                    // vertical down
                    if (i + 3 < input.size) {
                        if (input[i + 1][j] == 'M' &&
                            input[i + 2][j] == 'A' &&
                            input[i + 3][j] == 'S'
                        ) {
                            times++
                        }
                    }

                    // diagonal right down
                    if (i + 3 < input.size && j + 3 < input[i].length) {
                        if (input[i + 1][j + 1] == 'M' &&
                            input[i + 2][j + 2] == 'A' &&
                            input[i + 3][j + 3] == 'S'
                        )
                            times++
                    }

                    // diagonal right up
                    if (i >= 3 && j >= 3) {
                        if (input[i - 1][j - 1] == 'M' &&
                            input[i - 2][j - 2] == 'A' &&
                            input[i - 3][j - 3] == 'S'
                        )
                            times++
                    }

                    // diagonal left down
                    if (i + 3 < input.size && j >= 3) {
                        if (input[i + 1][j - 1] == 'M' &&
                            input[i + 2][j - 2] == 'A' &&
                            input[i + 3][j - 3] == 'S'
                        )
                            times++
                    }


                    // diagonal left up
                    if (j + 3 < input[i].length && i >= 3) {
                        if (input[i - 1][j + 1] == 'M' &&
                            input[i - 2][j + 2] == 'A' &&
                            input[i - 3][j + 3] == 'S'

                        )
                            times++
                    }
                }
            }

        }
    }
    return times
}

private fun partTwo(input: List<String>): Int {
    var times = 0
    for (i in input.indices) {
        for (j in input[i].indices) {

            if (input[i][j] == 'A') {
                if (i >= 1 && i + 1 < input.size &&
                    j >= 1 && j + 1 < input[i].length
                ) {

                    val tl = input[i - 1][j - 1]
                    val tr = input[i - 1][j + 1]
                    val bl = input[i + 1][j - 1]
                    val br = input[i + 1][j + 1]

                    val leftDiagonal = listOf(tl, br)
                    val rightDiagonal = listOf(tr, bl)

                    val hasExpectedLettersLeftDiagonal = leftDiagonal.count { it == 'M' } == 1 && leftDiagonal.count { it == 'S' } == 1
                    val hasExpectedLettersRightDiagonal = rightDiagonal.count { it == 'M' } == 1 && rightDiagonal.count { it == 'S' } == 1

                    if (hasExpectedLettersLeftDiagonal && hasExpectedLettersRightDiagonal) times++

                }
            }
        }
    }
    return times
}

