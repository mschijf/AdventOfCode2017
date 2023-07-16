package adventofcode2017.december03

import adventofcode2017.PuzzleSolverAbstract
import tool.coordinate.spiral.spiralIndexToPoint
import tool.coordinate.twodimensional.XYCoordinate
import tool.coordinate.twodimensional.printPoint2dMapAsGrid

fun main() {
    PuzzleSolver(test=false).showResult()
}

class PuzzleSolver(test: Boolean) : PuzzleSolverAbstract(test) {

    private val puzzleInput = inputLines.first().toInt()

    override fun resultPartOne(): Any {
        val xx = (1..25).associateWith { it.spiralIndexToPoint() }
        (1..25).associateBy { it.spiralIndexToPoint() }.printPoint2dMapAsGrid { "%12s".format(xx[it]!!) }
        val origin = 1.spiralIndexToPoint()
        return puzzleInput.spiralIndexToPoint().manhattanDistance(origin)
    }

    override fun resultPartTwo(): Any {
        val set = mutableMapOf<XYCoordinate, Int>()
        var newSum = 1
        set[1.spiralIndexToPoint()] = newSum
        var i = 2
        while (newSum < puzzleInput) {
            val point = i.spiralIndexToPoint()
            val neighbors = point.allWindDirectionNeighbors()
            newSum = neighbors.sumOf { set[it]?:0 }
            set[i.spiralIndexToPoint()] = newSum
            i++
        }

        return newSum
    }

}


