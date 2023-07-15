package adventofcode2017.december19

import adventofcode2017.PuzzleSolverAbstract
import tool.coordinate.twodimensional.Pos

fun main() {
    PuzzleSolver(test=false).showResult()
}

class PuzzleSolver(test: Boolean) : PuzzleSolverAbstract(test) {

    private val maze = inputLines
        .flatMapIndexed { y: Int, line: String -> line.mapIndexed { x, c -> Pos(x, y) to c } }
        .toMap()
        .filterValues{it != ' '}
    private val mazeCoordinates = maze.keys
    private val start = maze.keys.first{it.y == 0}

    override fun resultPartOne(): Any {
        return walkingOrder()
            .map{maze[it]!!}
            .filter { it in 'A'..'Z' }
            .joinToString("")
    }

    override fun resultPartTwo(): Any {
        return walkingOrder().size
    }

    private fun walkingOrder(): List<Pos> {
        var current = start
        val walkingOrder = mutableListOf<Pos>()
        var previous = start
        while (true) {
            walkingOrder.add(current)
            val nextCandidates = current.neighbors().minus(previous).intersect(mazeCoordinates)
            val next = when (nextCandidates.size) {
                0 -> break
                1 -> nextCandidates.first()
                else -> nextCandidates.first { it.direction(current) == current.direction(previous) }
            }
            previous = current
            current = next
        }
        return walkingOrder
    }

    private fun Pos.direction(other: Pos) =
        if (this.x == other.x) HorVer.VERTICAL else HorVer.HORIZONTAL

    enum class HorVer{
        HORIZONTAL, VERTICAL
    }
}


