import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

private const val pathName = "src/test/resources/day6.txt"

/**
 * current position of the guard with ^
 * any obstructions - crates, desks, alchemical reactors, etc. - are shown as #
 *
 * Lab guards in 1518 follow a very strict patrol protocol which involves repeatedly following these steps:
 * - If there is something directly in front of you, turn right 90 degrees.
 * - Otherwise take a step forward
 * */
class Day6Test {
    companion object {
        val input = File(pathName).readText()
    }

    data class Vec2(val x: Int, val y: Int)

    enum class Direction(val x: Int, val y: Int) {
        Up(0, -1),
        Down(0, 1),
        Left(-1, 0),
        Right(1, 0);
    }

    operator fun Vec2.plus(direction: Direction) = Vec2(x + direction.x, y + direction.y)

    @Test
    fun `should return guard positions`() {
        val guardPosition = findGuardPosition()
        assertThat(guardPosition).isEqualTo(Vec2(4, 6))
    }

    @Test
    fun `should move guard up until any obstructions`() {
        val obstructions = mutableListOf<Vec2>()
        var guardPosition = Vec2(0, 0)

        input.lines().forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                if (char == '#') {
                    obstructions.add(Vec2(x, y))
                }
                if (char == '^') {
                    guardPosition = Vec2(x, y)
                }
            }
        }

        var currentPosition = guardPosition
        var nextPosition = currentPosition + Direction.Up

        while (!obstructions.contains(nextPosition)) {
            currentPosition = nextPosition
            nextPosition = currentPosition + Direction.Up

            if (nextPosition.y < 0) break
        }

        assertThat(currentPosition).isEqualTo(Vec2(4, 1))
    }

    private fun findGuardPosition(): Vec2 {
        var guardPosition = Vec2(0, 0)

        input.lines().forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                if (char == '^') {
                    guardPosition = Vec2(x, y)
                }
            }
        }
        return guardPosition
    }
}