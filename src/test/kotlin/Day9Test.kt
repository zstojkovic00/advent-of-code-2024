import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day9Test {
    @Test
    fun `should transform input to  number-block file representation`() {
        val blockRepresentation: String = transform( """12345""".map { it.digitToInt() })
        val blockRepresentation2: String = transform( """2333133121414131402""".map { it.digitToInt() })
        assertThat(blockRepresentation).isEqualTo(
            """0..111....22222""".trimIndent()
        )
        assertThat(blockRepresentation2).isEqualTo(
            """00...111...2...333.44.5555.6666.777.888899"""
        )
    }

    @Test
    fun `should swap digits from right side with dots from left side`() {
        val input = """00...111...2...333.44.5555.6666.777.888899"""
        val expected = """0099811188827773336446555566.............."""
        assertThat(fileCompacting(input)).isEqualTo(expected)
    }

    @Test
    fun `should return true if input has dots between numbers`() {
        val input = """00...111...2...333.44.5555.6666.777.888899"""
        val expected = """0099811188827773336446555566.............."""
        assertThat(hasDotsInBetweenNumbers(input)).isTrue()
        assertThat(hasDotsInBetweenNumbers(expected)).isFalse()
    }

    @Test
    fun `should swap first dot with last right number`() {
        val input = """00...111...2...333.44.5555.6666.777.888899"""
        val expected = "009..111...2...333.44.5555.6666.777.88889."
        assertThat(swap(input)).isEqualTo(expected)
    }

    @Test
    fun `should calculate filesystem checksum`() {
        val input = """0099811188827773336446555566.............."""
        val expected = 1928

        assertThat(calculateCheckSum(input)).isEqualTo(expected)
    }

    private fun calculateCheckSum(input: String): Int {
        var result = 0
        for (i in 0 until input.length - 1) {
            if (input[i].isDigit()) {
                result += (input[i].digitToInt() * i)
            }
        }
        return result
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
}