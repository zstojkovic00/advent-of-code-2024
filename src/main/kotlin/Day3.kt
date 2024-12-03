package zeljko.com

import java.io.File

fun main() {

    val input = File("src/main/resources/day3_input.txt").readLines()
    partOne(input)
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