package tool.coordinate.twodimensional

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Point2DTest {

    @Test
    fun manhattanDistance() {
        assertEquals(2, Point2D(0,0).manhattanDistance(Point2D(1,1)) )
        assertEquals(0, Point2D(0,0).manhattanDistance(Point2D(0,0)) )
        assertEquals(3, Point2D(0,0).manhattanDistance(Point2D(2,1)) )
    }

    @Test
    fun directionOrNull() {
        val Point1 = Point2D(0,0)
        assertEquals(Direction.UP, Point1.directionToOrNull(Point2D(0,1) ))
        assertEquals(Direction.UP, Point1.directionToOrNull(Point2D(0,8) ))
        assertEquals(Direction.DOWN, Point1.directionToOrNull(Point2D(0,-8) ))
        assertEquals(Direction.LEFT, Point1.directionToOrNull(Point2D(-1,0) ))
        assertEquals(Direction.LEFT, Point1.directionToOrNull(Point2D(-4,0) ))
        assertEquals(Direction.RIGHT, Point1.directionToOrNull(Point2D(1,0) ))
        assertEquals(Direction.RIGHT, Point1.directionToOrNull(Point2D(4,0) ))
        assertNull(Point1.directionToOrNull(Point2D(3,3)))
    }

    @Test
    fun windDirectionOrNull() {
        val Point1 = Point2D(0,0)
        assertEquals(WindDirection.NORTH, Point1.windDirectionToOrNull(Point2D(0,1) ))
        assertEquals(WindDirection.NORTH, Point1.windDirectionToOrNull(Point2D(0,8) ))
        assertEquals(WindDirection.SOUTH, Point1.windDirectionToOrNull(Point2D(0,-8) ))
        assertEquals(WindDirection.WEST, Point1.windDirectionToOrNull(Point2D(-1,0) ))
        assertEquals(WindDirection.WEST, Point1.windDirectionToOrNull(Point2D(-4,0) ))
        assertEquals(WindDirection.EAST, Point1.windDirectionToOrNull(Point2D(1,0) ))
        assertEquals(WindDirection.EAST, Point1.windDirectionToOrNull(Point2D(4,0) ))

        assertEquals(WindDirection.NORTHEAST, Point1.windDirectionToOrNull(Point2D(4,4) ))
        assertEquals(WindDirection.SOUTHWEST, Point1.windDirectionToOrNull(Point2D(-4,-4) ))
        assertEquals(WindDirection.NORTHWEST, Point1.windDirectionToOrNull(Point2D(-4,4) ))
        assertEquals(WindDirection.SOUTHEAST, Point1.windDirectionToOrNull(Point2D(4,-4) ))

        assertNull(Point1.windDirectionToOrNull(Point2D(3,4)))
    }

    @Test
    fun moveTest1() {
        val Point1 = Point2D(0,0)
        assertEquals(Point2D(0,1), Point1.up())
        assertEquals(Point2D(0,-1), Point1.down())
        assertEquals(Point2D(1,0), Point1.right())
        assertEquals(Point2D(-1,0), Point1.left())
    }

    @Test
    fun moveTest2() {
        val Point1 = Point2D(0,0)
        assertEquals(Point2D(0,1), Point1.north())
        assertEquals(Point2D(0,-1), Point1.south())
        assertEquals(Point2D(1,0), Point1.east())
        assertEquals(Point2D(-1,0), Point1.west())

        assertEquals(Point2D(1,1), Point1.northeast())
        assertEquals(Point2D(1,-1), Point1.southeast())
        assertEquals(Point2D(-1,1), Point1.northwest())
        assertEquals(Point2D(-1,-1), Point1.southwest())
    }
}