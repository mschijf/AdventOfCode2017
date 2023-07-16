package tool.coordinate.twodimensional

import kotlin.math.absoluteValue

data class Pos(val x: Int, val y: Int) {

    private fun above(other: Pos) = this.y < other.y
    private fun leftOf(other: Pos) = this.x < other.x
    private val ONEDOWN = 1

    private fun Direction.dXY() =
        when(this) {
            Direction.DOWN -> Pair(0,ONEDOWN)
            Direction.UP -> Pair(0,-ONEDOWN)
            Direction.LEFT -> Pair(-1,0)
            Direction.RIGHT -> Pair(1,0)
        }

    private fun WindDirection.dXY() =
        when(this) {
            WindDirection.NORTH -> Pair(0,-ONEDOWN)
            WindDirection.SOUTH -> Pair(0,ONEDOWN)
            WindDirection.EAST -> Pair(1,0)
            WindDirection.WEST -> Pair(-1,0)
            WindDirection.NORTHEAST -> Pair(1,-ONEDOWN)
            WindDirection.NORTHWEST -> Pair(-1,-ONEDOWN)
            WindDirection.SOUTHEAST -> Pair(1,ONEDOWN)
            WindDirection.SOUTHWEST -> Pair(-1,ONEDOWN)
        }

    companion object {
        fun of(input: String): Pos = input
            .removeSurrounding("<", ">")
            .removeSurrounding("(", ")")
            .removeSurrounding("[", "]")
            .removeSurrounding("{", "}")
            .split(",").run { Pos(this[0].trim().toInt(), this[1].trim().toInt()) }

        val origin = Pos(0,0)
    }

    override fun toString() = "($x, $y)"

    fun plusXY(dx: Int, dy: Int) = Pos(x+dx, y+dy)

    fun plusX(dx: Int) = plusXY(dx, 0)
    fun plusY(dy: Int) = plusXY(0, dy)

    fun moveOneStep(dir: Direction) = plusXY(dir.dXY().first, dir.dXY().second)
    fun moveOneStep(dir: WindDirection) = plusXY(dir.dXY().first, dir.dXY().second)

    fun up() = moveOneStep(Direction.UP)
    fun down() = moveOneStep(Direction.DOWN)
    fun left() = moveOneStep(Direction.LEFT)
    fun right() = moveOneStep(Direction.RIGHT)

    fun north() = moveOneStep(WindDirection.NORTH)
    fun south() = moveOneStep(WindDirection.SOUTH)
    fun east() = moveOneStep(WindDirection.EAST)
    fun west() = moveOneStep(WindDirection.WEST)
    fun northeast() = moveOneStep(WindDirection.NORTHEAST)
    fun southeast() = moveOneStep(WindDirection.SOUTHEAST)
    fun northwest() = moveOneStep(WindDirection.NORTHWEST)
    fun southwest() = moveOneStep(WindDirection.SOUTHWEST)

    fun neighbors() = listOf(up(), down(), left(), right())
    fun allWindDirectionNeighbors() = listOf(north(), northeast(), east(), southeast(), south(), southwest(), west(), northwest())

    fun manhattanDistance(otherPos: Pos) = (otherPos.x - x).absoluteValue + (otherPos.y - y).absoluteValue

    fun directionToOrNull(other: Pos) =
        if (this.x == other.x) {
            if (this.above(other)) Direction.DOWN else Direction.UP
        } else if (this.y == other.y) {
            if (this.leftOf(other)) Direction.RIGHT else Direction.LEFT
        } else {
            null
        }

    fun windDirectionToOrNull(other: Pos) =
        if (this.x == other.x) {
            if (this.above(other)) WindDirection.SOUTH else WindDirection.NORTH
        } else if (this.y == other.y) {
            if (this.leftOf(other)) WindDirection.EAST else WindDirection.WEST
        } else if ((this.y - other.y).absoluteValue == (this.x - other.x).absoluteValue) {
            if (this.above(other)) {
                if (this.leftOf(other)) WindDirection.SOUTHEAST else WindDirection.SOUTHWEST
            } else {
                if (this.leftOf(other)) WindDirection.NORTHEAST else WindDirection.NORTHWEST
            }
        } else {
            null
        }
}

