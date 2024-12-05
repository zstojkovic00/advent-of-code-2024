package zeljko.com

import java.io.File


fun main() {

    val input = File("src/main/resources/day4_input.txt").readLines()
    val times = partOne(input)
    println(times)
    val times2 = partTwo(input)
    println(times2)

}


/*
As the search for the Chief continues, a small Elf who lives on the station tugs on your shirt;
she'd like to know if you could help her with her word search (your puzzle input). She only has to find one word: XMAS.

This word search allows words to be horizontal, vertical, diagonal, written backwards, or even overlapping other words.
It's a little unusual, though, as you don't merely need to find one instance of XMAS -
you need to find all of them. Here are a few ways XMAS might appear, where irrelevant characters have been replaced with .:

..X...
.SAMX.
.A..A.
XMAS.S
.X....

 */

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

/*
Looking for the instructions, you flip over the word search to find that this isn't actually an XMAS puzzle;
it's an X-MAS puzzle in which you're supposed to find two MAS in the shape of an X. One way to achieve that is like this:

        M.S
        .A.
        M.S

Irrelevant characters have again been replaced with .
in the above diagram. Within the X, each MAS can be written forwards or backwards.
*/
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

