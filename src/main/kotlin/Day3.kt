package zeljko.com

import java.io.File

fun main() {

    val input = File("src/main/resources/day3_input.txt").readLines()
    partOne(input)
    partTwo(input)
}


/*
    It seems like the goal of the program is just to multiply some numbers.
    It does that with instructions like mul(X,Y), where X and Y are each 1-3 digit numbers.
    For instance, mul(44,46) multiplies 44 by 46 to get a result of 2024. Similarly, mul(123,4) would multiply 123 by 4.

    However, because the program's memory has been corrupted,
    there are also many invalid characters that should be ignored,
    even if they look like part of a mul instruction. Sequences like mul(4*, mul(6,9!, ?(12,34), or mul ( 2 , 4 ) do nothing.

    For example, consider the following section of corrupted memory:

    xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
    Only the four highlighted sections are real mul instructions.
    Adding up the result of each instruction produces 161 (2*4 + 5*5 + 11*8 + 8*5).
 */

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

/*
    As you scan through the corrupted memory, you notice that some of the conditional statements are also still intact.
    If you handle some of the uncorrupted conditional statements in the program,
    you might be able to get an even more accurate result.

    There are two new instructions you'll need to handle:

    The do() instruction enables future mul instructions.
    The don't() instruction disables future mul instructions.
    Only the most recent do() or don't() instruction applies.
    At the beginning of the program, mul instructions are enabled.

    For example:

    xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
    This corrupted memory is similar to the example from before, but this time the mul(5,5) and mul(11,8) instructions are disabled because there is a don't() instruction before them. The other mul instructions function normally, including the one at the end that gets re-enabled by a do() instruction.

    This time, the sum of the results is 48 (2*4 + 8*5).

    Handle the new instructions; what do you get if you add up all of the results of just the enabled multiplications?
 */
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

