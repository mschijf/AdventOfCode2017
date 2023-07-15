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
        assertEquals(Direction.DOWN, pos1.directionOrNull(Pos(0,1) ))
        assertEquals(Direction.DOWN, pos1.directionOrNull(Pos(0,8) ))
        assertEquals(Direction.UP, pos1.directionOrNull(Pos(0,-8) ))
        assertEquals(Direction.LEFT, pos1.directionOrNull(Pos(-1,0) ))
        assertEquals(Direction.LEFT, pos1.directionOrNull(Pos(-4,0) ))
        assertEquals(Direction.RIGHT, pos1.directionOrNull(Pos(1,0) ))
        assertEquals(Direction.RIGHT, pos1.directionOrNull(Pos(4,0) ))
        assertNull(pos1.directionOrNull(Pos(3,3)))
    }

    @Test
    fun windDirectionOrNull() {
        val pos1 = Pos(0,0)
        assertEquals(WindDirection.SOUTH, pos1.windDirectionOrNull(Pos(0,1) ))
        assertEquals(WindDirection.SOUTH, pos1.windDirectionOrNull(Pos(0,8) ))
        assertEquals(WindDirection.NORTH, pos1.windDirectionOrNull(Pos(0,-8) ))
        assertEquals(WindDirection.WEST, pos1.windDirectionOrNull(Pos(-1,0) ))
        assertEquals(WindDirection.WEST, pos1.windDirectionOrNull(Pos(-4,0) ))
        assertEquals(WindDirection.EAST, pos1.windDirectionOrNull(Pos(1,0) ))
        assertEquals(WindDirection.EAST, pos1.windDirectionOrNull(Pos(4,0) ))

        assertEquals(WindDirection.SOUTHEAST, pos1.windDirectionOrNull(Pos(4,4) ))
        assertEquals(WindDirection.NORTHWEST, pos1.windDirectionOrNull(Pos(-4,-4) ))
        assertEquals(WindDirection.SOUTHWEST, pos1.windDirectionOrNull(Pos(-4,4) ))
        assertEquals(WindDirection.NORTHEAST, pos1.windDirectionOrNull(Pos(4,-4) ))

        assertNull(pos1.directionOrNull(Pos(3,4)))
    }
}