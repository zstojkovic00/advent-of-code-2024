package zeljko.com

import java.io.File

private const val pathName = "src/main/resources/day9.txt"

fun main() {

    diskFragmenter()
}

private fun diskFragmenter() {
    val input = File(pathName).readText().map { it.digitToInt() }
    val blockRepresentation = transform(input)

    val fileCompacting = fileCompacting(blockRepresentation)
    println(calculateCheckSum(fileCompacting))

}

private fun fileCompacting(blockRepresentation: String): String {
    var currentRepresentation = blockRepresentation

    while (hasDotsInBetweenNumbers(currentRepresentation)) {
        currentRepresentation = swap(currentRepresentation)
    }

    return currentRepresentation
}


private fun swap(currentRepresentation: String): String {

    var indexOfFirst = currentRepresentation.indexOfFirst { it == '.' }
    var indexOfLast = currentRepresentation.indexOfLast { it.isDigit() }

    val chars = currentRepresentation.toCharArray()
    val temp = chars[indexOfFirst]
    chars[indexOfFirst] = chars[indexOfLast]
    chars[indexOfLast] = temp

    return String(chars)
}

private fun hasDotsInBetweenNumbers(blockRepresentation: String): Boolean {
    for (i in 0 until blockRepresentation.length - 1) {
        if (blockRepresentation[i] == '.' && blockRepresentation[i + 1].isDigit()) {
            return true
        }
    }
    return false
}

private fun transform(numbers: List<Int>): String {
    val sb = StringBuilder()
    var blockNumber = 0

    for (i in numbers.indices) {
        if (i % 2 == 0) {
            sb.append(blockNumber.toString().repeat(numbers[i]))
            blockNumber++
        } else {
            sb.append(".".repeat(numbers[i]))
        }
    }

    return sb.toString()
}

private fun calculateCheckSum(input: String): Int {
    var result = 0
    for (i in input.indices) {
        if (input[i].isDigit()) {
            result += (i * input[i].digitToInt())
        }
    }
    return result
}

