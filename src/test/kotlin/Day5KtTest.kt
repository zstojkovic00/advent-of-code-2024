import org.junit.jupiter.api.Test
import java.io.File
import org.assertj.core.api.Assertions.assertThat

private const val pathName = "src/test/resources/day5_test_input.txt"
typealias Rules = List<Pair<Int, Int>>
typealias Pages = List<List<Int>>

class Day5KtTest {
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
        val orderingMap = createRulesMap(rules)

        val checkOrderingRules = isPageOrderValid(pages, orderingMap)
        assertThat(checkOrderingRules).isTrue()
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

    private fun isPageOrderValid(pages: Pages, rulesMap: Map<Int, List<Int>>): Boolean {
        for (i in pages.first().indices) {
            val current = pages.first()[i]

            for (j in 0 until i) {
                val previous = pages.first()[j]
                val after = rulesMap[current] ?: emptyList()
                if (previous in after) {
                    return false
                }
            }
        }
        return true
    }

}

