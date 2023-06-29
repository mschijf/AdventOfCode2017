package tool.coordinate.hexagon

import tool.coordinate.twodimensional.WindDirection

enum class HexagonDirection(val directionSymbol: String) {
    EAST("E"),
    WEST("W"),
    NORTHEAST("NE"),
    NORTHWEST("NW"),
    SOUTHEAST("SE"),
    SOUTHWEST("SW");

    override fun toString() = directionSymbol
}


