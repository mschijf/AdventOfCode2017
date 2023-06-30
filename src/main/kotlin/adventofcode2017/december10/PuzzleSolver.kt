package adventofcode2017.december10

import adventofcode2017.PuzzleSolverAbstract

fun main() {
    PuzzleSolver(test=false).showResult()
}

class PuzzleSolver(test: Boolean) : PuzzleSolverAbstract(test) {


    override fun resultPartOne(): Any {
        val numbers = IntArray(if (test) 5 else 256) { it }
        val lengths = inputLines.first().split(",").map{it.trim().toInt()}
        numbers.round(0, 0, lengths)
        return numbers[0]*numbers[1]
    }

    override fun resultPartTwo(): Any {
        val numbers = IntArray(256) { it }
        val lengths = inputLines.first().map { it.code }.toList()+listOf(17, 31, 73, 47, 23)
        var current = 0
        var skipSize = 0
        repeat(64) {
            val (newCurrent, newSkipSize) = numbers.round(current, skipSize, lengths)
            current = newCurrent
            skipSize = newSkipSize
        }
        return numbers.toDenseHash().toHex()

    }

    private fun IntArray.toDenseHash():List<Int> {
        return this.toList().chunked(16).map{it.reduce { acc, i -> acc xor i }}
    }

    private fun List<Int>.toHex():String {
        return this.joinToString (""){ "%02x".format(it) }
    }


    private fun IntArray.round(startCurrent: Int=0, startSkipSize: Int=0, lengths: List<Int>): Pair<Int, Int> {
        var current = startCurrent
        var skipSize = startSkipSize

        lengths.forEach {length ->
            this.reverseOrder(current, length)
            current = (current + length + skipSize) % this.size
            skipSize++
        }
        return Pair(current, skipSize)
    }


    private fun IntArray.reverseOrder(current: Int, length:Int) {
        var i = 0
        while (i < length/2) {
            this.swap((current+i)%this.size, (current+length-i-1)%this.size)
            i++
        }
    }

    private fun IntArray.swap(pos1: Int, pos2:Int) {
        val tmp = this[pos1]
        this[pos1] = this[pos2]
        this[pos2] = tmp
    }

}


