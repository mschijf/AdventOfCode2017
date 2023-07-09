package adventofcode2017.december15

import adventofcode2017.PuzzleSolverAbstract

fun main() {
    PuzzleSolver(test=false).showResult()
}

class PuzzleSolver(test: Boolean) : PuzzleSolverAbstract(test) {

    private val startA = if (test) 65L else 722L
    private val startB = if (test) 8921L else 354L

    private val factorGeneratorA = 16807
    private val factorGeneratorB = 48271

    private val divider = 2147483647L

    private val lowerBitsMask = (1L shl 16) - 1


    override fun resultPartOne(): Any {
        val generatePairs = generateSequence(Pair(startA, startB)) {
            Pair(it.first.nextNumA1(), it.second.nextNumB1())
        }
        return generatePairs.drop(1).take(40_000_000).count{(it.first and lowerBitsMask) == (it.second and lowerBitsMask)}
    }

    override fun resultPartTwo(): Any {
        val generatePairs = generateSequence(Pair(startA, startB)) {
            Pair(it.first.nextNumA2(), it.second.nextNumB2())
        }
        return generatePairs.drop(1).take(5_000_000).count{(it.first and lowerBitsMask) == (it.second and lowerBitsMask)}
    }

    private fun Long.nextNumA1() = this * factorGeneratorA % divider
    private fun Long.nextNumB1() = this * factorGeneratorB % divider

    private fun Long.nextNumA2(): Long {
        var result = this * factorGeneratorA % divider
        while (result % 4L != 0L) {
            result = result * factorGeneratorA % divider
        }
        return result
    }

    private fun Long.nextNumB2(): Long {
        var result = this * factorGeneratorB % divider
        while (result % 8L != 0L) {
            result = result * factorGeneratorB % divider
        }
        return result
    }

}


