package adventofcode2017.december04

import adventofcode2017.PuzzleSolverAbstract
import tool.mylambdas.collectioncombination.*

fun main() {
    PuzzleSolver(test=false).showResult()
}

class PuzzleSolver(test: Boolean) : PuzzleSolverAbstract(test) {

    private val passPhraseList = inputLines.map{it.split("\\s".toRegex())}

    override fun resultPartOne(): Any {
        return passPhraseList.count { passPhrase -> passPhrase.noneCombinedItems { word1, word2 -> word1 == word2 } }
    }

    override fun resultPartTwo(): Any {
        return passPhraseList.count { passPhrase -> passPhrase.noneCombinedItems { word1, word2 -> word1.isAnagramOf(word2) } }
    }

    private fun String.isAnagramOf(other: String) =
        this.toCharArray().sorted() == other.toCharArray().sorted()

}


