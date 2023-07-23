package adventofcode2017.december24

import adventofcode2017.PuzzleSolverAbstract

fun main() {
    Day24(test = false).showResult()
}

class Day24(test: Boolean) : PuzzleSolverAbstract(test) {
    private val componentSet =
        inputLines.mapIndexed { idx, line -> line.split("/").let { Component(idx, it[0].toInt(), it[1].toInt()) } }.toSet()

    override fun resultPartOne(): Any {
        return componentSet.solveStrongest(0)
    }

    override fun resultPartTwo(): Any {
        return componentSet.solveLongest(0).second
    }

    private fun Set<Component>.solveStrongest(openConnection: Int): Int {
        val candidates = this.findCandidates(openConnection)
        return if (candidates.isEmpty())
            0
        else
            candidates.maxOf { candidate ->  candidate.value() + (this-candidate).solveStrongest(candidate.otherPortType(openConnection))}
    }

    private fun Set<Component>.solveLongest(openConnection: Int, bridgeSet: Set<Component> = emptySet()): Pair<Int, Int> {
        val candidates = this.findCandidates(openConnection)
        return if (candidates.isEmpty())
            Pair(bridgeSet.size, bridgeSet.sumOf { it.value() })
        else {
            candidates.maxOfWith(pairComparator) { candidate -> (this - candidate).solveLongest(candidate.otherPortType(openConnection), bridgeSet + candidate) }

//            var best = Pair(-1, -1)
//            candidates.forEach { candidate ->
//                val tmp = (this - candidate).solveLongest(candidate.otherPortType(openConnection), bridgeSet+candidate)
//                if ( tmp.isBetter(best) ) {
//                    best = tmp
//                }
//            }
//            return best
        }
    }

    private val pairComparator =  Comparator<Pair<Int, Int>> { a, b ->
        when {
            (a == b) -> 0
            (a.first > b.first || a.first == b.first && a.second > b.second) -> 1
            else -> -1
        }
    }

    private fun Set<Component>.findCandidates(openConnection: Int): List<Component> {
        return this.filter{it.hasPortType(openConnection)}
    }
}

data class Component(private val id: Int, val port1: Int, val port2: Int) {

    fun hasPortType(aPort: Int) =
        (port1 == aPort || port2 == aPort)

    fun otherPortType(aPort: Int) =
        when (aPort) {
            port1 -> port2
            port2 -> port1
            else -> throw Exception("Oh oh")
        }

    fun value() = port1 + port2
}

