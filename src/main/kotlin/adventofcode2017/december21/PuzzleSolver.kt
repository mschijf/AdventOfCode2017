package adventofcode2017.december21

import adventofcode2017.PuzzleSolverAbstract
import tool.coordinate.twodimensional.Point
import tool.coordinate.twodimensional.posOf
import tool.coordinate.twodimensional.printAsGrid

fun main() {
    PuzzleSolver(test=false).showResult()
}


class PuzzleSolver(test: Boolean) : PuzzleSolverAbstract(test) {

    private val ruleMap = inputLines.flatMap{it.toListOfRulesForAllVariants()}.associate { rule -> rule.first to rule.second }
    private val start = Grid(Square.of(".#./..#/###").points, 3)

    override fun resultPartOne(): Any {
        var current = start
        current.print()

        repeat(5) {
            current = current.enhance(ruleMap)
            current.print()
        }

        return current.points.size
    }

    override fun resultPartTwo(): Any {
        var current = start
        repeat(18) {
            current = current.enhance(ruleMap)
        }
        return current.points.size
    }

    private fun Grid.print() {
        this.points.printAsGrid(rowRange = 0 until this.size, colRange = 0 until this.size, ".", "#")
        println()
    }

    private fun String.toListOfRulesForAllVariants(): List<Pair<Square, Square>> {
        val left = Square.of(this.substringBefore(" => "))
        val right = Square.of(this.substringAfter(" => "))
        return left.allVariants().map{Pair(it, right)}
    }

}


data class Square(val points: List<Point>, val size: Int) {

    private val hashValue = (1 shl (16+size)) +  points.sumOf {p -> 1 shl (size*p.y + p.x) }

    private fun rotateRight() = Square(points.map { posOf(-it.y, it.x).plusXY(size-1,0) }, size)
    private fun flip() = Square(points.map{ posOf(size - 1 - it.x, it.y) }, size)

    fun allVariants() =
        listOf(
            this,
            this.rotateRight(), this.rotateRight().rotateRight(), this.rotateRight().rotateRight().rotateRight(),
            this.flip(),
            this.flip().rotateRight(), this.flip().rotateRight().rotateRight(), this.flip().rotateRight().rotateRight().rotateRight()
        )

    override fun equals(other: Any?): Boolean {
        return if (other is Square)
            (hashValue == other.hashValue)
        else
            super.equals(other)
    }

    override fun hashCode() = hashValue

    companion object {
        fun of(rawInput: String): Square {
            val rows = rawInput.split("/")
            return Square(
                rows.flatMapIndexed { y, s -> s.mapIndexed { x, c -> if (c == '#') posOf(x, y) else null }.filterNotNull() },
                rows.size)
        }
    }
}

data class Grid(val points: List<Point>, val size: Int) {

    fun enhance(rules: Map<Square, Square>): Grid {
        val squareSize = if (size % 2 == 0) 2 else 3

        val pointGroups = points.groupBy { posOf(it.x/squareSize, it.y/squareSize) }
        val pointGroupsExt =
            (0 until size/squareSize).flatMap{bigX -> (0 until size/squareSize)
                    .map{bigY -> posOf(bigX, bigY) to pointGroups.getOrDefault(posOf(bigX, bigY), emptyList())}}.toMap()
        val squares = pointGroupsExt.mapValues { v -> Square(v.value.transpose(-squareSize * v.key.x, -squareSize * v.key.y), squareSize) }

        val enhanced = squares.mapValues { v -> rules[v.value]!! }

        val transposeBack = enhanced.mapValues { v -> v.value.points.transpose(v.value.size*v.key.x, v.value.size*v.key.y)}

        return Grid(transposeBack.values.flatten(), if (size % 2 == 0) 3*size/2 else 4*size/3 )
    }

    private fun List<Point>.transpose(dx: Int, dy: Int) =
        this.map { point -> point.plusXY(dx, dy) }

}


//        println(Part(listOf(posOf(0,0)),2).rotateRight())
//        println(Part(listOf(posOf(1,0)),2).rotateRight())
//        println(Part(listOf(posOf(1,1)),2).rotateRight())
//        println(Part(listOf(posOf(0,1)),2).rotateRight())
//        println()
//        println(Part(listOf(posOf(0,0)), 3).rotateRight())
//        println(Part(listOf(posOf(1,0)), 3).rotateRight())
//        println(Part(listOf(posOf(2,0)), 3).rotateRight())
//        println(Part(listOf(posOf(2,1)), 3).rotateRight())
//        println(Part(listOf(posOf(2,2)), 3).rotateRight())
//        println(Part(listOf(posOf(1,2)), 3).rotateRight())
//        println(Part(listOf(posOf(0,2)), 3).rotateRight())
//        println(Part(listOf(posOf(0,1)), 3).rotateRight())
//        println(Part(listOf(posOf(1,1)), 3).rotateRight())
//
