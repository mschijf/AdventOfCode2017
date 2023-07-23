package adventofcode2017.december10

import adventofcode2017.PuzzleSolverAbstract
import adventofcode2017.doOneKnotHashCycle
import adventofcode2017.knotHashEncode

fun main() {
    Day10(test=false).showResult()
}

class Day10(test: Boolean) : PuzzleSolverAbstract(test) {

    override fun resultPartOne(): Any {
        val numbers = IntArray(if (test) 5 else 256) { it }
        val lengths = inputLines.first().split(",").map{it.trim().toInt()}
        numbers.doOneKnotHashCycle(0, 0, lengths)
        return numbers[0]*numbers[1]
    }

    override fun resultPartTwo(): Any {
        return inputLines.first().knotHashEncode()
    }
}


