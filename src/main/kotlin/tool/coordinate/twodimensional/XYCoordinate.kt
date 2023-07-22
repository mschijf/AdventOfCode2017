package tool.coordinate.twodimensional

fun xyCoordinateOf(x: Int, y: Int): Point = XYCoordinate.of(x,y)

data class XYCoordinate private constructor(override val x: Int, override val y: Int): Point(x, y) {

    override fun above(other: Point) = this.y > other.y
    override val ONEDOWN = -1

    override fun plusXY(dx: Int, dy: Int) = xyCoordinateOf(x+dx, y+dy)

    companion object {
        fun of(x: Int, y: Int): Point = XYCoordinate(x,y)
        fun of(input: String): Point  = XYPair.fromString(input).run { XYCoordinate(this.x, this.y) }
        val origin = xyCoordinateOf(0,0)
    }
}