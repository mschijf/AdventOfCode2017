package tool.coordinate.twodimensional

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class GridPosTest {

    @Test
    fun makePos() {
        val x = posOf("<1,2>")
        assertEquals(posOf(1,2), x)
    }

    @Test
    fun manhattanDistance() {
        assertEquals(2, posOf(0,0).distanceTo(posOf(1,1)) )
        assertEquals(0, posOf(0,0).distanceTo(posOf(0,0)) )
        assertEquals(3, posOf(0,0).distanceTo(posOf(2,1)) )
    }

    @Test
    fun directionOrNull() {
        val pos1 = posOf(0,0)
        assertEquals(Direction.DOWN, pos1.directionToOrNull(posOf(0,1) ))
        assertEquals(Direction.DOWN, pos1.directionToOrNull(posOf(0,8) ))
        assertEquals(Direction.UP, pos1.directionToOrNull(posOf(0,-8) ))
        assertEquals(Direction.LEFT, pos1.directionToOrNull(posOf(-1,0) ))
        assertEquals(Direction.LEFT, pos1.directionToOrNull(posOf(-4,0) ))
        assertEquals(Direction.RIGHT, pos1.directionToOrNull(posOf(1,0) ))
        assertEquals(Direction.RIGHT, pos1.directionToOrNull(posOf(4,0) ))
        assertNull(pos1.directionToOrNull(posOf(3,3)))
    }

    @Test
    fun windDirectionOrNull() {
        val pos1 = posOf(0,0)
        assertEquals(WindDirection.SOUTH, pos1.windDirectionToOrNull(posOf(0,1) ))
        assertEquals(WindDirection.SOUTH, pos1.windDirectionToOrNull(posOf(0,8) ))
        assertEquals(WindDirection.NORTH, pos1.windDirectionToOrNull(posOf(0,-8) ))
        assertEquals(WindDirection.WEST, pos1.windDirectionToOrNull(posOf(-1,0) ))
        assertEquals(WindDirection.WEST, pos1.windDirectionToOrNull(posOf(-4,0) ))
        assertEquals(WindDirection.EAST, pos1.windDirectionToOrNull(posOf(1,0) ))
        assertEquals(WindDirection.EAST, pos1.windDirectionToOrNull(posOf(4,0) ))

        assertEquals(WindDirection.SOUTHEAST, pos1.windDirectionToOrNull(posOf(4,4) ))
        assertEquals(WindDirection.NORTHWEST, pos1.windDirectionToOrNull(posOf(-4,-4) ))
        assertEquals(WindDirection.SOUTHWEST, pos1.windDirectionToOrNull(posOf(-4,4) ))
        assertEquals(WindDirection.NORTHEAST, pos1.windDirectionToOrNull(posOf(4,-4) ))

        assertNull(pos1.directionToOrNull(posOf(3,4)))
    }

    @Test
    fun moveTest1() {
        val pos1 = posOf(0,0)
        assertEquals(posOf(0,-1), pos1.up())
        assertEquals(posOf(0,1), pos1.down())
        assertEquals(posOf(1,0), pos1.right())
        assertEquals(posOf(-1,0), pos1.left())
    }

    @Test
    fun moveTest2() {
        val pos1 = posOf(0,0)
        assertEquals(posOf(0,-1), pos1.north())
        assertEquals(posOf(0,1), pos1.south())
        assertEquals(posOf(1,0), pos1.east())
        assertEquals(posOf(-1,0), pos1.west())

        assertEquals(posOf(1,-1), pos1.northeast())
        assertEquals(posOf(1,1), pos1.southeast())
        assertEquals(posOf(-1,-1), pos1.northwest())
        assertEquals(posOf(-1,1), pos1.southwest())
    }
}