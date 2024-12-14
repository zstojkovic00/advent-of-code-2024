package zeljko.com

import java.io.File


fun main() {

    partOne()
}


private fun partOne() {
    val input = File("src/main/resources/day5_input.txt").readText()
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
        if (isPageOrderValid(page, rulesMap)) {
            result += middle
        }
    }
    println(result)

}

fun isPageOrderValid(page: List<Int>, rulesMap: Map<Int, List<Int>>): Boolean {
    for (i in page.indices) {
        val current = page[i]

        for (j in 0 until i) {
            val previous = page[j]
            val afterAfterCurrent = rulesMap[current] ?: emptyList()
            if (previous in afterAfterCurrent) {
                return false
            }
        }
    }
    return true
}

private fun partTwo() {}