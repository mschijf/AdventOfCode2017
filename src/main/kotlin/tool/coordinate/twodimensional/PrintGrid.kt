package tool.coordinate.twodimensional

private fun Collection<Point>.yRange(): IntProgression {
    val minY = this.minByOrNull { it.y }?.y ?: -1
    val maxY = this.maxByOrNull { it.y }?.y ?: -1
    return if (this.first() is XYCoordinate) (maxY downTo minY) else (minY .. maxY)
}

private fun Collection<Point>.xRange(): IntProgression {
    val minX = this.minByOrNull { it.x }?.x ?: -1
    val maxX = this.maxByOrNull { it.x }?.x ?: -1
    return (minX .. maxX)
}

fun <T> Map<Point, T>.printAsGrid(default: String=".", itemAsString: (T)->String) {
    val posType = (this.keys.first() is GridPos)
    this.keys.yRange().forEach { y ->
        this.keys.xRange().forEach { x ->
            val field = if (posType) posOf(x,y) else xyCoordinateOf(x,y)
            if (this.contains(field)) {
                print(itemAsString(this[field]!!))
            } else {
                print(default)
            }
        }
        println()
    }
}

fun Collection<Point>.printAsGrid(itemAsString: (Point)->String) {
    val posType = (this.first() is GridPos)
    this.yRange().forEach { y ->
        this.xRange().forEach { x ->
            val field = if (posType) posOf(x,y) else xyCoordinateOf(x,y)
            print(itemAsString(field))
        }
        println()
    }
}

fun Collection<Point>.printAsGrid(defaultEmpty: String=".", defaultAvailable: String="#") {
    val posType = (this.first() is GridPos)
    this.yRange().forEach { y ->
        this.xRange().forEach { x ->
            val field = if (posType) posOf(x,y) else xyCoordinateOf(x,y)
            if (this.contains(field)) {
                print(defaultAvailable)
            } else {
                print(defaultEmpty)
            }
        }
        println()
    }
}

fun Pair<Point, Point>.printGrid(itemAsString: (Point)->String) {
    val minX = this.first.x
    val minY = this.first.y
    val maxX = this.second.x
    val maxY = this.second.y

    (minY..maxY).forEach { y ->
        (minX..maxX).forEach { x ->
            val field = posOf(x,y)
            print(itemAsString(field))
        }
        println()
    }
}


fun <T> List<List<T>>.printGrid(itemAsString: (gridValue: T)->String) {
    this.printGridIndexed{_, _, gridValue -> itemAsString(gridValue)}
}

fun <T> List<List<T>>.printGridIndexed(itemAsString: (row:Int, col:Int, gridValue: T)->String) {
    for (row in this.indices) {
        for (col in this[row].indices) {
            print(itemAsString(row, col, this[row][col]))
        }
        println()
    }
}