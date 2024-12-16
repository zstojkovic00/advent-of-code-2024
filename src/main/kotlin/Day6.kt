package zeljko.com

import java.io.File

private const val pathName = "src/main/resources/day6.txt"

fun main() {
    val lines = File(pathName).readLines()
    guardGallivant(lines)
}

private fun guardGallivant(lines: List<String>) {
    val obstructions = mutableSetOf<Vec2>()
    var guard = Vec2(0, 0)

    val height = lines.size
    val width = lines[0].length

    lines.forEachIndexed { y, line ->
        line.forEachIndexed { x, char ->
            if (char == '#') {
                obstructions.add(Vec2(x, y))
            }
            if (char == '^') {
                guard = Vec2(x, y)
            }
        }
    }

    var dir = Direction.Up
    val steps = mutableSetOf(guard)

    while (true) {
        val next = guard + dir

        if (next.x !in 0 until width || next.y !in 0 until height) {
            break
        }

        if (next in obstructions) {
            dir = dir.turnRight()
        } else {
            guard = next
            steps.add(guard)
        }
    }

    println(steps.size)
}

data class Vec2(val x: Int, val y: Int)

operator fun Vec2.plus(direction: Direction) = Vec2(x + direction.x, y + direction.y)

enum class Direction(val x: Int, val y: Int) {
    Up(0, -1),
    Right(1, 0),
    Down(0, 1),
    Left(-1, 0);

    fun turnRight(): Direction {
        return entries[(ordinal + 1) % entries.size]
    }
}

