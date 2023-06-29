package adventofcode2017.december09

import adventofcode2017.PuzzleSolverAbstract

fun main() {
    PuzzleSolver(test=false).showResult()
}

class PuzzleSolver(test: Boolean) : PuzzleSolverAbstract(test) {

    private val stream = inputLines.first()

    override fun resultPartOne(): Any {
        var totalScore = 0
        var groupLevel = 0
        var i=0
        while (i < stream.length) {
            when(stream[i]) {
                '<' -> i = endOfGarbage(stream, i).first
                '{' -> groupLevel++
                '}' -> {
                    totalScore += groupLevel
                    groupLevel--
                }
                else -> {}
            }
            i++
        }
        return totalScore
    }

    override fun resultPartTwo(): Any {
        var totalGarbageRemoved = 0
        var groupLevel = 0
        var i=0
        while (i < stream.length) {
            when(stream[i]) {
                '<' -> {
                    val (newIndex, garbageRemovedCount) = endOfGarbage(stream, i)
                    totalGarbageRemoved += garbageRemovedCount
                    i = newIndex
                }
                '{' -> groupLevel++
                '}' -> {
                    groupLevel--
                }
                else -> {}
            }
            i++
        }
        return totalGarbageRemoved
    }

    private fun endOfGarbage(stream: String, startIndex: Int): Pair<Int, Int> {
        var count = 0
        var i=startIndex+1
        while (stream[i] != '>') {
            if (stream[i] == '!') {
                i+=2
            } else {
                count++
                i++
            }
        }
        return Pair(i, count)
    }
}


