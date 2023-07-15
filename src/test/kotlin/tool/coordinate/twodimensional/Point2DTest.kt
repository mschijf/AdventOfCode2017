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
        val Point2D1 = Point2D(0,0)
        assertEquals(Direction.UP, Point2D1.directionOrNull(Point2D(0,1) ))
        assertEquals(Direction.UP, Point2D1.directionOrNull(Point2D(0,8) ))
        assertEquals(Direction.DOWN, Point2D1.directionOrNull(Point2D(0,-8) ))
        assertEquals(Direction.LEFT, Point2D1.directionOrNull(Point2D(-1,0) ))
        assertEquals(Direction.LEFT, Point2D1.directionOrNull(Point2D(-4,0) ))
        assertEquals(Direction.RIGHT, Point2D1.directionOrNull(Point2D(1,0) ))
        assertEquals(Direction.RIGHT, Point2D1.directionOrNull(Point2D(4,0) ))
        assertNull(Point2D1.directionOrNull(Point2D(3,3)))
    }

    @Test
    fun windDirectionOrNull() {
        val Point2D1 = Point2D(0,0)
        assertEquals(WindDirection.NORTH, Point2D1.windDirectionOrNull(Point2D(0,1) ))
        assertEquals(WindDirection.NORTH, Point2D1.windDirectionOrNull(Point2D(0,8) ))
        assertEquals(WindDirection.SOUTH, Point2D1.windDirectionOrNull(Point2D(0,-8) ))
        assertEquals(WindDirection.WEST, Point2D1.windDirectionOrNull(Point2D(-1,0) ))
        assertEquals(WindDirection.WEST, Point2D1.windDirectionOrNull(Point2D(-4,0) ))
        assertEquals(WindDirection.EAST, Point2D1.windDirectionOrNull(Point2D(1,0) ))
        assertEquals(WindDirection.EAST, Point2D1.windDirectionOrNull(Point2D(4,0) ))

        assertEquals(WindDirection.NORTHEAST, Point2D1.windDirectionOrNull(Point2D(4,4) ))
        assertEquals(WindDirection.SOUTHWEST, Point2D1.windDirectionOrNull(Point2D(-4,-4) ))
        assertEquals(WindDirection.NORTHWEST, Point2D1.windDirectionOrNull(Point2D(-4,4) ))
        assertEquals(WindDirection.SOUTHEAST, Point2D1.windDirectionOrNull(Point2D(4,-4) ))

        assertNull(Point2D1.directionOrNull(Point2D(3,4)))
    }
}