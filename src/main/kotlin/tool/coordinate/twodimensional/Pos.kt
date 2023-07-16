package tool.coordinate.twodimensional

fun posOf(x: Int, y: Int): Point = Pos(x,y)

data class Pos(override val x: Int, override val y: Int): Point(x, y) {

    override fun above(other: Point) = this.y < other.y
    override fun leftOf(other: Point) = this.x < other.x
    override val ONEDOWN = 1

    override fun plusXY(dx: Int, dy: Int) = posOf(x+dx, y+dy)

    companion object {
        fun of(input: String) = input
            .removeSurrounding("<", ">")
            .removeSurrounding("(", ")")
            .removeSurrounding("[", "]")
            .removeSurrounding("{", "}")
            .split(",").run { posOf(this[0].trim().toInt(), this[1].trim().toInt()) }

        val origin = posOf(0,0)
    }

}

