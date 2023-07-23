package tool.coordinate.twodimensional

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class GridPosTest {

    @Test
    fun makePos() {
        val x = pos("<1,2>")
        assertEquals(pos(1,2), x)
    }

    @Test
    fun manhattanDistance() {
        assertEquals(2, pos(0,0).distanceTo(pos(1,1)) )
        assertEquals(0, pos(0,0).distanceTo(pos(0,0)) )
        assertEquals(3, pos(0,0).distanceTo(pos(2,1)) )
    }

    @Test
    fun directionOrNull() {
        val pos1 = pos(0,0)
        assertEquals(Direction.DOWN, pos1.directionToOrNull(pos(0,1) ))
        assertEquals(Direction.DOWN, pos1.directionToOrNull(pos(0,8) ))
        assertEquals(Direction.UP, pos1.directionToOrNull(pos(0,-8) ))
        assertEquals(Direction.LEFT, pos1.directionToOrNull(pos(-1,0) ))
        assertEquals(Direction.LEFT, pos1.directionToOrNull(pos(-4,0) ))
        assertEquals(Direction.RIGHT, pos1.directionToOrNull(pos(1,0) ))
        assertEquals(Direction.RIGHT, pos1.directionToOrNull(pos(4,0) ))
        assertNull(pos1.directionToOrNull(pos(3,3)))
    }

    @Test
    fun windDirectionOrNull() {
        val pos1 = pos(0,0)
        assertEquals(WindDirection.SOUTH, pos1.windDirectionToOrNull(pos(0,1) ))
        assertEquals(WindDirection.SOUTH, pos1.windDirectionToOrNull(pos(0,8) ))
        assertEquals(WindDirection.NORTH, pos1.windDirectionToOrNull(pos(0,-8) ))
        assertEquals(WindDirection.WEST, pos1.windDirectionToOrNull(pos(-1,0) ))
        assertEquals(WindDirection.WEST, pos1.windDirectionToOrNull(pos(-4,0) ))
        assertEquals(WindDirection.EAST, pos1.windDirectionToOrNull(pos(1,0) ))
        assertEquals(WindDirection.EAST, pos1.windDirectionToOrNull(pos(4,0) ))

        assertEquals(WindDirection.SOUTHEAST, pos1.windDirectionToOrNull(pos(4,4) ))
        assertEquals(WindDirection.NORTHWEST, pos1.windDirectionToOrNull(pos(-4,-4) ))
        assertEquals(WindDirection.SOUTHWEST, pos1.windDirectionToOrNull(pos(-4,4) ))
        assertEquals(WindDirection.NORTHEAST, pos1.windDirectionToOrNull(pos(4,-4) ))

        assertNull(pos1.directionToOrNull(pos(3,4)))
    }

    @Test
    fun moveTest1() {
        val pos1 = pos(0,0)
        assertEquals(pos(0,-1), pos1.up())
        assertEquals(pos(0,1), pos1.down())
        assertEquals(pos(1,0), pos1.right())
        assertEquals(pos(-1,0), pos1.left())
    }

    @Test
    fun moveTest2() {
        val pos1 = pos(0,0)
        assertEquals(pos(0,-1), pos1.north())
        assertEquals(pos(0,1), pos1.south())
        assertEquals(pos(1,0), pos1.east())
        assertEquals(pos(-1,0), pos1.west())

        assertEquals(pos(1,-1), pos1.northeast())
        assertEquals(pos(1,1), pos1.southeast())
        assertEquals(pos(-1,-1), pos1.northwest())
        assertEquals(pos(-1,1), pos1.southwest())
    }
}