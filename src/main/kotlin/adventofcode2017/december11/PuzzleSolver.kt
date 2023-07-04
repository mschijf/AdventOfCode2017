package adventofcode2017.december11

import adventofcode2017.PuzzleSolverAbstract
import tool.coordinate.hexagon.Hexagon
import tool.coordinate.hexagon.HexagonDirection

fun main() {
    PuzzleSolver(test=false).showResult()
}

class PuzzleSolver(test: Boolean) : PuzzleSolverAbstract(test) {

    override fun resultPartOne(): Any {
        return inputLines.first()
            .split(",")
            .fold(Hexagon.origin){ acc, s -> acc.moveOneStep(HexagonDirection.of(s))}
            .distanceTo(Hexagon.origin)
    }

    override fun resultPartTwo(): Any {
        return inputLines.first()
            .split(",")
            .scan(Hexagon.origin) {acc, s -> acc.moveOneStep(HexagonDirection.of(s))}
            .map{Hexagon.origin.distanceTo(it)}
            .max()
    }
}


