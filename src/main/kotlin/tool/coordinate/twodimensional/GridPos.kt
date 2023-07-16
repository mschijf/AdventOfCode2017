package tool.coordinate.twodimensional

fun gridPosOf(x: Int, y: Int): Point = GridPos(x,y)

data class GridPos(override val x: Int, override val y: Int): Point(x, y) {

    override fun above(other: Point) = this.y < other.y
    override val ONEDOWN = 1

    override fun plusXY(dx: Int, dy: Int) = gridPosOf(x+dx, y+dy)

    companion object {
        fun of(input: String) = XYPair.fromString(input).run { gridPosOf(this.x, this.y) }
        val origin = gridPosOf(0,0)
    }

}

