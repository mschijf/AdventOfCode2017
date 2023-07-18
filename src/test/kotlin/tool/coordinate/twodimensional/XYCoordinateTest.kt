package tool.coordinate.twodimensional

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class XYCoordinateTest {

    @Test
    fun makePoint2D() {
        val x = XYCoordinate.of("<1,2>")
        assertEquals(XYCoordinate(1,2), x )
        assertEquals(xyCoordinateOf(1,2), x)
    }

    @Test
    fun manhattanDistance() {
        assertEquals(2, XYCoordinate(0,0).distanceTo(XYCoordinate(1,1)) )
        assertEquals(0, XYCoordinate(0,0).distanceTo(XYCoordinate(0,0)) )
        assertEquals(3, XYCoordinate(0,0).distanceTo(XYCoordinate(2,1)) )
    }

    @Test
    fun directionOrNull() {
        val Point1 = XYCoordinate(0,0)
        assertEquals(Direction.UP, Point1.directionToOrNull(XYCoordinate(0,1) ))
        assertEquals(Direction.UP, Point1.directionToOrNull(XYCoordinate(0,8) ))
        assertEquals(Direction.DOWN, Point1.directionToOrNull(XYCoordinate(0,-8) ))
        assertEquals(Direction.LEFT, Point1.directionToOrNull(XYCoordinate(-1,0) ))
        assertEquals(Direction.LEFT, Point1.directionToOrNull(XYCoordinate(-4,0) ))
        assertEquals(Direction.RIGHT, Point1.directionToOrNull(XYCoordinate(1,0) ))
        assertEquals(Direction.RIGHT, Point1.directionToOrNull(XYCoordinate(4,0) ))
        assertNull(Point1.directionToOrNull(XYCoordinate(3,3)))
    }

    @Test
    fun windDirectionOrNull() {
        val Point1 = XYCoordinate(0,0)
        assertEquals(WindDirection.NORTH, Point1.windDirectionToOrNull(XYCoordinate(0,1) ))
        assertEquals(WindDirection.NORTH, Point1.windDirectionToOrNull(XYCoordinate(0,8) ))
        assertEquals(WindDirection.SOUTH, Point1.windDirectionToOrNull(XYCoordinate(0,-8) ))
        assertEquals(WindDirection.WEST, Point1.windDirectionToOrNull(XYCoordinate(-1,0) ))
        assertEquals(WindDirection.WEST, Point1.windDirectionToOrNull(XYCoordinate(-4,0) ))
        assertEquals(WindDirection.EAST, Point1.windDirectionToOrNull(XYCoordinate(1,0) ))
        assertEquals(WindDirection.EAST, Point1.windDirectionToOrNull(XYCoordinate(4,0) ))

        assertEquals(WindDirection.NORTHEAST, Point1.windDirectionToOrNull(XYCoordinate(4,4) ))
        assertEquals(WindDirection.SOUTHWEST, Point1.windDirectionToOrNull(XYCoordinate(-4,-4) ))
        assertEquals(WindDirection.NORTHWEST, Point1.windDirectionToOrNull(XYCoordinate(-4,4) ))
        assertEquals(WindDirection.SOUTHEAST, Point1.windDirectionToOrNull(XYCoordinate(4,-4) ))

        assertNull(Point1.windDirectionToOrNull(XYCoordinate(3,4)))
    }

    @Test
    fun moveTest1() {
        val Point1 = XYCoordinate(0,0)
        assertEquals(XYCoordinate(0,1), Point1.up())
        assertEquals(XYCoordinate(0,-1), Point1.down())
        assertEquals(XYCoordinate(1,0), Point1.right())
        assertEquals(XYCoordinate(-1,0), Point1.left())
    }

    @Test
    fun moveTest2() {
        val Point1 = XYCoordinate(0,0)
        assertEquals(XYCoordinate(0,1), Point1.north())
        assertEquals(XYCoordinate(0,-1), Point1.south())
        assertEquals(XYCoordinate(1,0), Point1.east())
        assertEquals(XYCoordinate(-1,0), Point1.west())

        assertEquals(XYCoordinate(1,1), Point1.northeast())
        assertEquals(XYCoordinate(1,-1), Point1.southeast())
        assertEquals(XYCoordinate(-1,1), Point1.northwest())
        assertEquals(XYCoordinate(-1,-1), Point1.southwest())
    }
}