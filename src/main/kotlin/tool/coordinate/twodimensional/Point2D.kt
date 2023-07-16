package tool.coordinate.twodimensional

fun point2DOf(x: Int, y: Int): Point = Point2D(x,y)

data class Point2D(override val x: Int, override val y: Int): Point(x, y) {

    override fun above(other: Point) = this.y > other.y
    override fun leftOf(other: Point) = this.x < other.x
    override val ONEDOWN = -1

    override fun plusXY(dx: Int, dy: Int) = point2DOf(x+dx, y+dy)

    companion object {
        fun of(input: String) = input
            .removeSurrounding("<", ">")
            .removeSurrounding("(", ")")
            .removeSurrounding("[", "]")
            .removeSurrounding("{", "}")
            .split(",").run { point2DOf(this[0].trim().toInt(), this[1].trim().toInt()) }

        val origin = point2DOf(0,0)
    }
}