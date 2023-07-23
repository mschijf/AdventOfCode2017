package tool.coordinate.twodimensional

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class XYCoordinateTest {

    @Test
    fun makePoint2D() {
        val x = xyCoordinateOf("<1,2>")
        assertEquals(xyCoordinateOf(1,2), x)
    }

    @Test
    fun manhattanDistance() {
        assertEquals(2, xyCoordinateOf(0,0).distanceTo(xyCoordinateOf(1,1)) )
        assertEquals(0, xyCoordinateOf(0,0).distanceTo(xyCoordinateOf(0,0)) )
        assertEquals(3, xyCoordinateOf(0,0).distanceTo(xyCoordinateOf(2,1)) )
    }

    @Test
    fun directionOrNull() {
        val Point1 = xyCoordinateOf(0,0)
        assertEquals(Direction.UP, Point1.directionToOrNull(xyCoordinateOf(0,1) ))
        assertEquals(Direction.UP, Point1.directionToOrNull(xyCoordinateOf(0,8) ))
        assertEquals(Direction.DOWN, Point1.directionToOrNull(xyCoordinateOf(0,-8) ))
        assertEquals(Direction.LEFT, Point1.directionToOrNull(xyCoordinateOf(-1,0) ))
        assertEquals(Direction.LEFT, Point1.directionToOrNull(xyCoordinateOf(-4,0) ))
        assertEquals(Direction.RIGHT, Point1.directionToOrNull(xyCoordinateOf(1,0) ))
        assertEquals(Direction.RIGHT, Point1.directionToOrNull(xyCoordinateOf(4,0) ))
        assertNull(Point1.directionToOrNull(xyCoordinateOf(3,3)))
    }

    @Test
    fun windDirectionOrNull() {
        val Point1 = xyCoordinateOf(0,0)
        assertEquals(WindDirection.NORTH, Point1.windDirectionToOrNull(xyCoordinateOf(0,1) ))
        assertEquals(WindDirection.NORTH, Point1.windDirectionToOrNull(xyCoordinateOf(0,8) ))
        assertEquals(WindDirection.SOUTH, Point1.windDirectionToOrNull(xyCoordinateOf(0,-8) ))
        assertEquals(WindDirection.WEST, Point1.windDirectionToOrNull(xyCoordinateOf(-1,0) ))
        assertEquals(WindDirection.WEST, Point1.windDirectionToOrNull(xyCoordinateOf(-4,0) ))
        assertEquals(WindDirection.EAST, Point1.windDirectionToOrNull(xyCoordinateOf(1,0) ))
        assertEquals(WindDirection.EAST, Point1.windDirectionToOrNull(xyCoordinateOf(4,0) ))

        assertEquals(WindDirection.NORTHEAST, Point1.windDirectionToOrNull(xyCoordinateOf(4,4) ))
        assertEquals(WindDirection.SOUTHWEST, Point1.windDirectionToOrNull(xyCoordinateOf(-4,-4) ))
        assertEquals(WindDirection.NORTHWEST, Point1.windDirectionToOrNull(xyCoordinateOf(-4,4) ))
        assertEquals(WindDirection.SOUTHEAST, Point1.windDirectionToOrNull(xyCoordinateOf(4,-4) ))

        assertNull(Point1.windDirectionToOrNull(xyCoordinateOf(3,4)))
    }

    @Test
    fun moveTest1() {
        val Point1 = xyCoordinateOf(0,0)
        assertEquals(xyCoordinateOf(0,1), Point1.up())
        assertEquals(xyCoordinateOf(0,-1), Point1.down())
        assertEquals(xyCoordinateOf(1,0), Point1.right())
        assertEquals(xyCoordinateOf(-1,0), Point1.left())
    }

    @Test
    fun moveTest2() {
        val Point1 = xyCoordinateOf(0,0)
        assertEquals(xyCoordinateOf(0,1), Point1.north())
        assertEquals(xyCoordinateOf(0,-1), Point1.south())
        assertEquals(xyCoordinateOf(1,0), Point1.east())
        assertEquals(xyCoordinateOf(-1,0), Point1.west())

        assertEquals(xyCoordinateOf(1,1), Point1.northeast())
        assertEquals(xyCoordinateOf(1,-1), Point1.southeast())
        assertEquals(xyCoordinateOf(-1,1), Point1.northwest())
        assertEquals(xyCoordinateOf(-1,-1), Point1.southwest())
    }
}