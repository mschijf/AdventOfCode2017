package adventofcode2017.december02

import adventofcode2017.PuzzleSolverAbstract

fun main() {
    PuzzleSolver(test=false).showResult()
}

class PuzzleSolver(test: Boolean) : PuzzleSolverAbstract(test) {

    private val grid = inputLines.map{it.split("\\s".toRegex()).map { it.trim().toInt() }}

    override fun resultPartOne(): Any {
        return grid.sumOf { it.max() - it.min() }
    }

    override fun resultPartTwo(): Any {
        return grid.sumOf { it.findEvenlyDividePair().let { pair -> pair.first / pair.second } }
    }

    private fun List<Int>.findEvenlyDividePair(): Pair<Int, Int> {
        return this.flatMap { i1 -> this.mapNotNull { i2 -> if (i1 != i2 && i1 % i2 == 0) Pair(i1, i2) else null } }.first()
    }
}


