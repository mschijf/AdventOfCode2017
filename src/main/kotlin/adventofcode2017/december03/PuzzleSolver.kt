package adventofcode2017.december03

import adventofcode2017.PuzzleSolverAbstract
import tool.coordinate.spiral.spiralIndexToPoint2D
import tool.coordinate.twodimensional.Point2D
import tool.coordinate.twodimensional.printPoint2dMapAsGrid

fun main() {
    PuzzleSolver(test=false).showResult()
}

class PuzzleSolver(test: Boolean) : PuzzleSolverAbstract(test) {

    private val puzzleInput = inputLines.first().toInt()

    override fun resultPartOne(): Any {
        val xx = (1..25).associateWith { it.spiralIndexToPoint2D() }
        (1..25).associateBy { it.spiralIndexToPoint2D() }.printPoint2dMapAsGrid { "%12s".format(xx[it]!!) }
        val origin = 1.spiralIndexToPoint2D()
        return puzzleInput.spiralIndexToPoint2D().manhattanDistance(origin)
    }

    override fun resultPartTwo(): Any {
        val set = mutableMapOf<Point2D, Int>()
        var newSum = 1
        set[1.spiralIndexToPoint2D()] = newSum
        var i = 2
        while (newSum < puzzleInput) {
            val point = i.spiralIndexToPoint2D()
            val neighbors = point.allWindDirectionNeighbors()
            newSum = neighbors.sumOf { set[it]?:0 }
            set[i.spiralIndexToPoint2D()] = newSum
            i++
        }

        return newSum
    }

}


