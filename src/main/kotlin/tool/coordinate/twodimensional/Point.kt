package tool.coordinate.twodimensional

import kotlin.math.absoluteValue

abstract class Point(open val x: Int, open val y: Int) {

    abstract fun above(other: Point): Boolean
    fun leftOf(other: Point) = this.x < other.x

    protected abstract val ONEDOWN: Int
    abstract fun plusXY(dx: Int, dy: Int): Point

    private fun Direction.dXY() =
        when(this) {
            Direction.DOWN -> XYPair(0,ONEDOWN)
            Direction.UP -> XYPair(0,-ONEDOWN)
            Direction.LEFT -> XYPair(-1,0)
            Direction.RIGHT -> XYPair(1,0)
        }

    private fun WindDirection.dXY() =
        when(this) {
            WindDirection.NORTH -> XYPair(0,-ONEDOWN)
            WindDirection.SOUTH -> XYPair(0,ONEDOWN)
            WindDirection.EAST -> XYPair(1,0)
            WindDirection.WEST -> XYPair(-1,0)
            WindDirection.NORTHEAST -> XYPair(1,-ONEDOWN)
            WindDirection.NORTHWEST -> XYPair(-1,-ONEDOWN)
            WindDirection.SOUTHEAST -> XYPair(1,ONEDOWN)
            WindDirection.SOUTHWEST -> XYPair(-1,ONEDOWN)
        }

    override fun toString() = "($x, $y)"

    fun plusX(dx: Int) = plusXY(dx, 0)
    fun plusY(dy: Int) = plusXY(0, dy)

    fun moveOneStep(dir: Direction) = plusXY(dir.dXY().x, dir.dXY().y)
    fun moveOneStep(dir: WindDirection) = plusXY(dir.dXY().x, dir.dXY().y)

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

    fun distanceTo(other: Point) = (other.x - x).absoluteValue + (other.y - y).absoluteValue

    fun directionToOrNull(other: Point) =
        if (this.x == other.x) {
            if (this.above(other)) Direction.DOWN else Direction.UP
        } else if (this.y == other.y) {
            if (this.leftOf(other)) Direction.RIGHT else Direction.LEFT
        } else {
            null
        }

    fun windDirectionToOrNull(other: Point) =
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

data class XYPair(val x: Int, val y: Int) {
    companion object {
        fun fromString(input: String) = input
            .removeSurrounding("<", ">")
            .removeSurrounding("(", ")")
            .removeSurrounding("[", "]")
            .removeSurrounding("{", "}")
            .split(",").run { XYPair(this[0].trim().toInt(), this[1].trim().toInt()) }
    }
}

