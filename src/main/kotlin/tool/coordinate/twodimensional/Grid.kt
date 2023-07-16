package tool.coordinate.twodimensional

fun <T> Map<GridPos, T>.printAsGrid(default: String=".", itemAsString: (T)->String) {
    val maxX = this.keys.maxByOrNull { it.x }?.x ?: -1
    val maxY = this.keys.maxByOrNull { it.y }?.y ?: -1

    (0..maxY).forEach { y ->
        (0..maxX).forEach { x ->
            val field = GridPos(x,y)
            if (this.contains(field)) {
                print(itemAsString(this[field]!!))
            } else {
                print(default)
            }
        }
        println()
    }
}

fun Collection<GridPos>.printAsGrid(itemAsString: (GridPos)->String) {
    val minX = this.minByOrNull { it.x }?.x ?: -1
    val minY = this.minByOrNull { it.y }?.y ?: -1
    val maxX = this.maxByOrNull { it.x }?.x ?: -1
    val maxY = this.maxByOrNull { it.y }?.y ?: -1

    (minY..maxY).forEach { y ->
        (minX..maxX).forEach { x ->
            val field = GridPos(x,y)
            print(itemAsString(field))
        }
        println()
    }
}

fun Pair<GridPos, GridPos>.printGrid(itemAsString: (GridPos)->String) {
    val minX = this.first.x
    val minY = this.first.y
    val maxX = this.second.x
    val maxY = this.second.y

    (minY..maxY).forEach { y ->
        (minX..maxX).forEach { x ->
            val field = GridPos(x,y)
            print(itemAsString(field))
        }
        println()
    }
}



fun Set<GridPos>.printAsGrid(defaultEmpty: String=".", defaultAvailable: String="#") {
    val minX = this.minByOrNull { it.x }?.x ?: -1
    val minY = this.minByOrNull { it.y }?.y ?: -1
    val maxX = this.maxByOrNull { it.x }?.x ?: -1
    val maxY = this.maxByOrNull { it.y }?.y ?: -1

    (minY..maxY).forEach { y ->
        (minX..maxX).forEach { x ->
            val field = GridPos(x,y)
            if (this.contains(field)) {
                print(defaultAvailable)
            } else {
                print(defaultEmpty)
            }
        }
        println()
    }
}

fun <T> Map<XYCoordinate, T>.printPoint2dMapAsGrid(default: String=".", itemAsString: (T)->String) {
    val minX = this.keys.minByOrNull { it.x }?.x ?: -1
    val minY = this.keys.minByOrNull { it.y }?.y ?: -1
    val maxX = this.keys.maxByOrNull { it.x }?.x ?: -1
    val maxY = this.keys.maxByOrNull { it.y }?.y ?: -1

    (maxY downTo minY).forEach { y ->
        (minX..maxX).forEach { x ->
            val field = XYCoordinate(x,y)
            if (this.contains(field)) {
                print(itemAsString(this[field]!!))
            } else {
                print(default)
            }
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