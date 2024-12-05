package zeljko.com

import java.io.File


fun main() {

    val input = File("src/main/resources/day4_input.txt").readLines()
    val times = partOne(input)
    println(times)
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