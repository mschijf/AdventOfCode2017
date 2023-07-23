package adventofcode2017.december17

import adventofcode2017.PuzzleSolverAbstract
import tool.collectionspecials.CircularLinkedList

fun main() {
    Day17(test=false).showResult()
}

/**
 * We kunnen het met de circular list doen, maar dat gaat in deel 2 te lang duren en misschien ook nog te weinig geheugen
 * Je kan, bij de tweede, ook bijhouden wanneer er iets op positie 1 wordt toegevoegd
 */
class Day17(test: Boolean) : PuzzleSolverAbstract(test) {

    private val stepSize = if (test) 3 else 369

    override fun resultPartOne(): Any {
        val circularList = CircularLinkedList<Int>()
        var current = circularList.add(0)
        (1..2017).forEach {
            current = current.plus(stepSize)
            current = circularList.add(current.plus(1), it)
        }
        return current.plus(1).data
    }

    override fun resultPartTwo(): Any {
        var current = 0
        var after0 = -1
        (1..50_000_000).forEach { newNumber ->
            current = 1 + ((current+stepSize) % newNumber)
            if (current == 1) {
                after0 = newNumber
            }
        }
        return after0
    }

}


