package tool.coordinate.twodimensional

fun <T> Map<Point, T>.printAsGrid(default: String=".", itemAsString: (T)->String) {
    val minX = this.keys.minByOrNull { it.x }?.x ?: -1
    val minY = this.keys.minByOrNull { it.y }?.y ?: -1
    val maxX = this.keys.maxByOrNull { it.x }?.x ?: -1
    val maxY = this.keys.maxByOrNull { it.y }?.y ?: -1

    (maxY downTo minY).forEach { y ->
        (minX..maxX).forEach { x ->
            val field = posOf(x,y)
            if (this.contains(field)) {
                print(itemAsString(this[field]!!))
            } else {
                print(default)
            }
        }
        println()
    }
}

fun <T> Map<Point, T>.printPoint2dMapAsGrid(default: String=".", itemAsString: (T)->String) {
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


fun Collection<Point>.printAsGrid(itemAsString: (Point)->String) {
    val minX = this.minByOrNull { it.x }?.x ?: -1
    val minY = this.minByOrNull { it.y }?.y ?: -1
    val maxX = this.maxByOrNull { it.x }?.x ?: -1
    val maxY = this.maxByOrNull { it.y }?.y ?: -1

    (minY..maxY).forEach { y ->
        (minX..maxX).forEach { x ->
            val field = posOf(x,y)
            print(itemAsString(field))
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



fun Set<Point>.printAsGrid(defaultEmpty: String=".", defaultAvailable: String="#") {
    val minX = this.minByOrNull { it.x }?.x ?: -1
    val minY = this.minByOrNull { it.y }?.y ?: -1
    val maxX = this.maxByOrNull { it.x }?.x ?: -1
    val maxY = this.maxByOrNull { it.y }?.y ?: -1

    (minY..maxY).forEach { y ->
        (minX..maxX).forEach { x ->
            val field = posOf(x,y)
            if (this.contains(field)) {
                print(defaultAvailable)
            } else {
                print(defaultEmpty)
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


fun Collection<Point>.printAsGrid(rowRange: IntRange, colRange: IntRange, defaultEmpty: String=".", defaultAvailable: String="#") {
    (rowRange).forEach { y ->
        (colRange).forEach { x ->
            val field = posOf(x,y)
            print(if (field in this) defaultAvailable else defaultEmpty)
        }
        println()
    }
}
