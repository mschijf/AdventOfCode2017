package tool.coordinate.twodimensional

fun posOf(x: Int, y: Int): Point = GridPos.of(x,y)

fun posRange(minPos: Point, maxPos: Point) = sequence<Point> {
    (minPos.x .. maxPos.x).forEach { x ->
        (minPos.y .. maxPos.y).forEach { y ->
            yield(posOf(x,y))
        }
    }
}

data class GridPos private constructor(override val x: Int, override val y: Int): Point(x, y) {

    override fun above(other: Point) = this.y < other.y
    override val ONEDOWN = 1

    override fun plusXY(dx: Int, dy: Int) = posOf(x+dx, y+dy)

    override fun toString() = "($x, $y)"

    companion object {
        fun of(x: Int, y: Int): Point = GridPos(x,y)
        fun of(input: String): Point = XYPair.fromString(input).run { GridPos(this.x, this.y) }
        val origin = posOf(0,0)
    }
}
