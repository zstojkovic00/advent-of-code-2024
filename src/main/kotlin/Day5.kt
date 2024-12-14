package zeljko.com

import java.io.File

typealias Validation = Pair<Boolean, Pair<Int, Int>?>

fun main() {
    val input = File("src/main/resources/day5_input.txt").readText()
    partOne(input)
    partTwo(input)
}


private fun partOne(input: String) {
    val (rulesString, pagesString) = input.split("\r\n\r\n")

    val rulesMap = mutableMapOf<Int, MutableList<Int>>()
    var result = 0

    rulesString.lines().forEach { line ->
        val (from, to) = line.split("|").map { it.toInt() }
        rulesMap.computeIfAbsent(from) { mutableListOf() }.add(to)
    }

    val pages = pagesString.lines().map { line ->
        line.split(",").map { it.toInt() }
    }

    pages.forEach { page ->
        val middle = page[page.size / 2]
        val validation = isPageOrderValid(page, rulesMap)
        if (validation.first) {
            result += middle
        }
    }
    println(result)
}


fun isPageOrderValid(page: List<Int>, rulesMap: Map<Int, List<Int>>): Validation {
    for (i in page.indices) {
        val current = page[i]

        for (j in 0 until i) {
            val previous = page[j]
            val afterAfterCurrent = rulesMap[current] ?: emptyList()
            if (previous in afterAfterCurrent) {
                return Validation(false, j to i)
            }
        }
    }
    return Validation(true, null)
}

private fun partTwo(input: String) {
    val (rulesString, pagesString) = input.split("\r\n\r\n")

    val rulesMap = mutableMapOf<Int, MutableList<Int>>()
    var result = 0

    rulesString.lines().forEach { line ->
        val (from, to) = line.split("|").map { it.toInt() }
        rulesMap.computeIfAbsent(from) { mutableListOf() }.add(to)
    }

    val pages = pagesString.lines().map { line ->
        line.split(",").map { it.toInt() }
    }

    pages.forEach { page ->

        val validation = isPageOrderValid(page, rulesMap)
        if (!validation.first) {
            val currentPage = page.toMutableList()
            while (true) {
                val currentValidation = isPageOrderValid(currentPage, rulesMap)
                if (currentValidation.first) {
                    val middle = currentPage[currentPage.size / 2]
                    result += middle
                    break
                }

                val (i, j) = currentValidation.second!!
                val temp = currentPage[i]
                currentPage[i] = currentPage[j]
                currentPage[j] = temp
            }
        }
    }
    println(result)
}