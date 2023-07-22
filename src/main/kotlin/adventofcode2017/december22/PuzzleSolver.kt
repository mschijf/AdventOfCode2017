package adventofcode2017.december22

import adventofcode2017.PuzzleSolverAbstract
import tool.coordinate.twodimensional.Direction
import tool.coordinate.twodimensional.Point
import tool.coordinate.twodimensional.posOf
import tool.coordinate.twodimensional.printGrid

fun main() {
    PuzzleSolver(test=false).showResult()
}

class PuzzleSolver(test: Boolean) : PuzzleSolverAbstract(test) {

    private val infectedSet = mutableSetOf<Point>()
    private val weakenedSet = mutableSetOf<Point>()
    private val flaggedSet = mutableSetOf<Point>()

    private var currentPos = posOf(inputLines.size/2, inputLines.first().length/2)
    private var currentDir = Direction.UP

    private fun initialState() {
        infectedSet += inputLines
            .flatMapIndexed{y, row -> row.mapIndexed { x, cell -> if (cell == '#') posOf(x,y) else null }}
            .filterNotNull()

        currentPos = posOf(inputLines.size/2, inputLines.first().length/2)
        currentDir = Direction.UP
    }

    override fun resultPartOne(): Any {
        initialState()
        return (1..10_000).sumOf { burstPartOne() }
    }

    override fun resultPartTwo(): Any {
        initialState()
        return (1..10_000_000).sumOf { burstPartTwo() }
    }

    private fun printGrid() {
        Pair(posOf(-3,-3), posOf(5,5)).printGrid { (if (it == currentPos) "[" else " ") + (if (it in infectedSet) "#" else ".") + (if (it == currentPos) "]" else " ")}
        println()
    }

    private fun burstPartOne(): Int {
        currentDir = if (currentPos in infectedSet) currentDir.rotateRight() else currentDir.rotateLeft()
        val infected = (currentPos in infectedSet)
        if (infected)  {
            infectedSet -= currentPos
        } else
            infectedSet += currentPos
        currentPos = currentPos.moveOneStep(currentDir)
        return if (!infected) 1 else 0
    }

    private fun burstPartTwo(): Int {
        var infectionsCaused = 0
        when (currentPos) {
            in weakenedSet -> {currentDir = currentDir; weakenedSet -= currentPos; infectedSet += currentPos; infectionsCaused = 1}
            in infectedSet -> {currentDir = currentDir.rotateRight(); infectedSet -= currentPos; flaggedSet += currentPos }
            in flaggedSet -> {currentDir = currentDir.opposite(); flaggedSet -= currentPos /* cleanedSet++; */ }
            else /* cleaned */ -> {currentDir = currentDir.rotateLeft(); /* cleanedSet--; */ weakenedSet += currentPos }
        }
        currentPos = currentPos.moveOneStep(currentDir)
        return infectionsCaused
    }
}
