package tool.coordinate.hexagon

// See: https://math.stackexchange.com/questions/2254655/hexagon-grid-coordinate-system

data class Hexagon (val x: Int, val y: Int, val z: Int){
    fun moveOneStep(direction: HexagonDirection) = Hexagon(x + direction.dX(), y + direction.dY(), z + direction.dZ())
    fun getNeighbours(): List<Hexagon> =  listOf(east(), west(), northeast(), southeast(), northwest(), southwest())

    fun east() = moveOneStep( HexagonDirection.EAST)
    fun west() = moveOneStep( HexagonDirection.WEST)
    fun northeast() = moveOneStep( HexagonDirection.NORTHEAST)
    fun southeast() = moveOneStep( HexagonDirection.SOUTHEAST)
    fun northwest() = moveOneStep( HexagonDirection.NORTHWEST)
    fun southwest() = moveOneStep( HexagonDirection.SOUTHWEST)

    private fun HexagonDirection.dX(): Int =
        when(this) {
             HexagonDirection.EAST -> -1
             HexagonDirection.WEST -> 1
             HexagonDirection.NORTHEAST ->   0
             HexagonDirection.NORTHWEST ->   1
             HexagonDirection.SOUTHEAST ->  -1
             HexagonDirection.SOUTHWEST ->   0
        }

    private fun HexagonDirection.dY(): Int =
        when (this) {
             HexagonDirection.EAST -> 1
             HexagonDirection.WEST -> -1
             HexagonDirection.NORTHEAST ->  1
             HexagonDirection.NORTHWEST ->  0
             HexagonDirection.SOUTHEAST ->  0
             HexagonDirection.SOUTHWEST ->  -1
        }

    private fun HexagonDirection.dZ(): Int =
        when (this) {
            HexagonDirection.EAST -> 0
            HexagonDirection.WEST -> 0
            HexagonDirection.NORTHEAST ->  -1
            HexagonDirection.NORTHWEST ->  -1
            HexagonDirection.SOUTHEAST ->  1
            HexagonDirection.SOUTHWEST ->  1
        }

}

