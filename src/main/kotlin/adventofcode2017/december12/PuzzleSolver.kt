package adventofcode2017.december12

import adventofcode2017.PuzzleSolverAbstract

fun main() {
    PuzzleSolver(test=true).showResult()
}

class PuzzleSolver(test: Boolean) : PuzzleSolverAbstract(test) {

    private val pipes = inputLines.associate { it.toPair() }
    private val pipeGroups = pipes.connectPipeGroups()

    override fun resultPartOne(): Any {
        return pipeGroups.first{0 in it}.size
    }

    override fun resultPartTwo(): Any {
        return pipeGroups.size
    }

    private fun Map<Int, Set<Int>>.connectPipeGroups(): List<Set<Int>> {
        var result = listOf(emptySet<Int>())
        pipes.map{it.value + it.key}.forEach { pipesConnected ->
            result = result
                .partition { it.intersect(pipesConnected).isNotEmpty() }
                .let{listOf(it.first.flatten().toSet()+pipesConnected) + it.second}
        }
        return result.filterNot { it.isEmpty() }
    }

    private fun String.toPair(): Pair<Int, Set<Int>> =
        this
            .split(" <-> ")
            .let{ Pair(it[0].toInt(), it[1].split(", ").map{ nr -> nr.toInt()}.toSet()) }
}


