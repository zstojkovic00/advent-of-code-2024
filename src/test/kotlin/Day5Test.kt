import org.junit.jupiter.api.Test
import java.io.File
import org.assertj.core.api.Assertions.assertThat

private const val pathName = "src/test/resources/day5.txt"
typealias Rules = List<Pair<Int, Int>>
typealias Pages = List<List<Int>>
typealias Validation = Pair<Boolean, Pair<Int, Int>?>

class Day5Test {
    @Test
    fun `should parse input into rules and pages`() {
        val input = File(pathName).readText()
        val (rules, pages) = parse(input)

        assertThat(rules)
            .element(0)
            .isEqualTo(47 to 53)

        assertThat(pages)
            .element(0)
            .isEqualTo(listOf(75, 47, 61, 53, 29))
    }

    @Test
    fun `should create rules map with correct elements`() {
        val input = File(pathName).readText()
        val (rules, _) = parse(input)

        val rulesMap = createRulesMap(rules)

        assertThat(rulesMap)
            .containsEntry(47, mutableListOf(53, 13, 61, 29))
    }


    @Test
    fun `should validate page order according to rules`() {
        val input = File(pathName).readText()
        val (rules, pages) = parse(input)
        val rulesMap = createRulesMap(rules)
        var times = 0

        pages.forEach { page ->
            val validation = isPageOrderValid(page, rulesMap)
            if (validation.first) {
                times++
            }
        }

        assertThat(times).isEqualTo(3)
    }


    @Test
    fun `should fix incorrectly ordered pages`() {
        val input = File(pathName).readText()
        val (rules, pages) = parse(input)
        val rulesMap = createRulesMap(rules)
        var times = 0

        pages.forEach { page ->
            val validation = isPageOrderValid(page, rulesMap)
            if (!validation.first) {

                var currentPage = page.toMutableList()

                while (true) {

                    val currentValidation = isPageOrderValid(currentPage, rulesMap)
                    if (currentValidation.first) {
                        times++
                        break
                    }

                    val (i, j) = currentValidation.second!!
                    val temp = currentPage[i]
                    currentPage[i] = currentPage[j]
                    currentPage[j] = temp
                }
            }
        }
        assertThat(times).isEqualTo(3)
    }

    private fun parse(input: String): Pair<Rules, Pages> {
        val (rules, pages) = input.split("\r\n\r\n")

        val parsedRules = rules.lines().map { line ->
            val (from, to) = line.split("|").map { it.toInt() }
            from to to
        }

        val parsedPages = pages.lines().map { line ->
            line.split(",").map { it.toInt() }
        }

        return parsedRules to parsedPages
    }

    private fun createRulesMap(rules: Rules): MutableMap<Int, MutableList<Int>> {
        val orderingMap = mutableMapOf<Int, MutableList<Int>>()

        rules.forEach { (from, to) ->
            orderingMap.computeIfAbsent(from) { mutableListOf() }.add(to)
        }
        return orderingMap
    }


    private fun isPageOrderValid(page: List<Int>, rulesMap: Map<Int, List<Int>>): Validation {
        for (i in page.indices) {
            val current = page[i]
            println("Current number: $current")
            for (j in 0 until i) {
                val previous = page[j]
                println("Previous number: $previous")
                val afterAfterCurrent = rulesMap[current] ?: emptyList()
                println("Numbers which must come before current number: $afterAfterCurrent")
                if (previous in afterAfterCurrent) {
                    println("False")
                    return Validation(false, j to i)
                }
            }
        }
        return Validation(true, null)
    }
}

