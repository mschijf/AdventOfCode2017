package tool.coordinate.twodimensional

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class PosTest {

    @Test
    fun manhattanDistance() {
        assertEquals(2, Pos(0,0).manhattanDistance(Pos(1,1)) )
        assertEquals(0, Pos(0,0).manhattanDistance(Pos(0,0)) )
        assertEquals(3, Pos(0,0).manhattanDistance(Pos(2,1)) )
    }

    @Test
    fun directionOrNull() {
        val pos1 = Pos(0,0)
        assertEquals(Direction.DOWN, pos1.directionToOrNull(Pos(0,1) ))
        assertEquals(Direction.DOWN, pos1.directionToOrNull(Pos(0,8) ))
        assertEquals(Direction.UP, pos1.directionToOrNull(Pos(0,-8) ))
        assertEquals(Direction.LEFT, pos1.directionToOrNull(Pos(-1,0) ))
        assertEquals(Direction.LEFT, pos1.directionToOrNull(Pos(-4,0) ))
        assertEquals(Direction.RIGHT, pos1.directionToOrNull(Pos(1,0) ))
        assertEquals(Direction.RIGHT, pos1.directionToOrNull(Pos(4,0) ))
        assertNull(pos1.directionToOrNull(Pos(3,3)))
    }

    @Test
    fun windDirectionOrNull() {
        val pos1 = Pos(0,0)
        assertEquals(WindDirection.SOUTH, pos1.windDirectionToOrNull(Pos(0,1) ))
        assertEquals(WindDirection.SOUTH, pos1.windDirectionToOrNull(Pos(0,8) ))
        assertEquals(WindDirection.NORTH, pos1.windDirectionToOrNull(Pos(0,-8) ))
        assertEquals(WindDirection.WEST, pos1.windDirectionToOrNull(Pos(-1,0) ))
        assertEquals(WindDirection.WEST, pos1.windDirectionToOrNull(Pos(-4,0) ))
        assertEquals(WindDirection.EAST, pos1.windDirectionToOrNull(Pos(1,0) ))
        assertEquals(WindDirection.EAST, pos1.windDirectionToOrNull(Pos(4,0) ))

        assertEquals(WindDirection.SOUTHEAST, pos1.windDirectionToOrNull(Pos(4,4) ))
        assertEquals(WindDirection.NORTHWEST, pos1.windDirectionToOrNull(Pos(-4,-4) ))
        assertEquals(WindDirection.SOUTHWEST, pos1.windDirectionToOrNull(Pos(-4,4) ))
        assertEquals(WindDirection.NORTHEAST, pos1.windDirectionToOrNull(Pos(4,-4) ))

        assertNull(pos1.directionToOrNull(Pos(3,4)))
    }

    @Test
    fun moveTest1() {
        val pos1 = Pos(0,0)
        assertEquals(Pos(0,-1), pos1.up())
        assertEquals(Pos(0,1), pos1.down())
        assertEquals(Pos(1,0), pos1.right())
        assertEquals(Pos(-1,0), pos1.left())
    }

    @Test
    fun moveTest2() {
        val pos1 = Pos(0,0)
        assertEquals(Pos(0,-1), pos1.north())
        assertEquals(Pos(0,1), pos1.south())
        assertEquals(Pos(1,0), pos1.east())
        assertEquals(Pos(-1,0), pos1.west())

        assertEquals(Pos(1,-1), pos1.northeast())
        assertEquals(Pos(1,1), pos1.southeast())
        assertEquals(Pos(-1,-1), pos1.northwest())
        assertEquals(Pos(-1,1), pos1.southwest())
    }
}