package adventofcode2017.december14

import adventofcode2017.PuzzleSolverAbstract
import adventofcode2017.knotHashEncode
import tool.coordinate.twodimensional.Pos
import tool.coordinate.twodimensional.printGrid
import java.math.BigInteger

fun main() {
    PuzzleSolver(test=false).showResult()
}

class PuzzleSolver(test: Boolean) : PuzzleSolverAbstract(test) {

    private val keyString = if (test) "flqrgnkx" else "hxtvlmkl"

    private val grid = (0..127)
        .map{row->"$keyString-$row".knotHashEncode().hexToBin().map{ch -> ch.digitToInt()}}

    override fun resultPartOne(): Any {
        return grid.sumOf{row -> row.sum()}
    }

    override fun resultPartTwo(): Any {
        var regions = listOf(emptySet<Pos>())

        val usedSquares = grid
            .flatMapIndexed { rowIndex, row ->
                row.mapIndexed { colIndex, item ->
                    if (item == 1) Pos(colIndex, rowIndex) else null
                }.filterNotNull()
            }

// Onderstaande uitgesterde code gebruikt intersect om te kijken of er overlap is. Deze code is 1,3 seconde
// Terwijl de code daaronder (niet uitgesterd) gebruik maakt van een snellere check op oerlap. Deze duurt 0,3 seconde
//
//        usedSquares.forEach { square ->
//            val neighbors = square.neighbors().toSet()
//            regions = regions
//                .partition { region -> region.intersect(neighbors).isNotEmpty() }
//                .run { listOf(this.first.flatten().toSet() + square) + this.second }

        usedSquares.forEach { square ->
            val neighbors = square.neighbors().toSet()
            regions = regions
                .partition { region -> region.containsOneOf(neighbors) }
                .run { listOf(this.first.flatten().toSet() + square) + this.second }
        }
        return regions.filterNot { it.isEmpty() }.size
    }

    /**
     * On te kijken of er overlap is tussen twee sets, kun je kijken de doosnede van de twee sets leeg is, maar sneller is het
     * om te kijken of er een element van de ene set, in de andere set zit.
     */
    private fun Set<Pos>.containsOneOf(other: Set<Pos>): Boolean {
        return other.any{this.contains(it)}
    }

    /**
     * Little trick to create voorloop nullen:
     *  add a '1' in front of the string, before transforming it to a bit string.
     *  this one will lead to a binary '1' as first char in the whole bit string.
     *  and when we remove the first char from the beginning, we have the right bit string, including voorloop nullen
     */
    private fun String.hexToBin(): String {
        return BigInteger("1$this", 16).toString(2).format().drop(1)
    }
}


