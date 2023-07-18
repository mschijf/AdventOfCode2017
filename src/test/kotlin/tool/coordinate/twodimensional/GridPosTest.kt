package tool.coordinate.twodimensional

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class GridPosTest {

    @Test
    fun makePos() {
        val x = GridPos.of("<1,2>")
        assertEquals(GridPos(1,2), x )
        assertEquals(gridPosOf(1,2), x)
    }

    @Test
    fun manhattanDistance() {
        assertEquals(2, GridPos(0,0).distanceTo(GridPos(1,1)) )
        assertEquals(0, GridPos(0,0).distanceTo(GridPos(0,0)) )
        assertEquals(3, GridPos(0,0).distanceTo(GridPos(2,1)) )
    }

    @Test
    fun directionOrNull() {
        val pos1 = GridPos(0,0)
        assertEquals(Direction.DOWN, pos1.directionToOrNull(GridPos(0,1) ))
        assertEquals(Direction.DOWN, pos1.directionToOrNull(GridPos(0,8) ))
        assertEquals(Direction.UP, pos1.directionToOrNull(GridPos(0,-8) ))
        assertEquals(Direction.LEFT, pos1.directionToOrNull(GridPos(-1,0) ))
        assertEquals(Direction.LEFT, pos1.directionToOrNull(GridPos(-4,0) ))
        assertEquals(Direction.RIGHT, pos1.directionToOrNull(GridPos(1,0) ))
        assertEquals(Direction.RIGHT, pos1.directionToOrNull(GridPos(4,0) ))
        assertNull(pos1.directionToOrNull(GridPos(3,3)))
    }

    @Test
    fun windDirectionOrNull() {
        val pos1 = GridPos(0,0)
        assertEquals(WindDirection.SOUTH, pos1.windDirectionToOrNull(GridPos(0,1) ))
        assertEquals(WindDirection.SOUTH, pos1.windDirectionToOrNull(GridPos(0,8) ))
        assertEquals(WindDirection.NORTH, pos1.windDirectionToOrNull(GridPos(0,-8) ))
        assertEquals(WindDirection.WEST, pos1.windDirectionToOrNull(GridPos(-1,0) ))
        assertEquals(WindDirection.WEST, pos1.windDirectionToOrNull(GridPos(-4,0) ))
        assertEquals(WindDirection.EAST, pos1.windDirectionToOrNull(GridPos(1,0) ))
        assertEquals(WindDirection.EAST, pos1.windDirectionToOrNull(GridPos(4,0) ))

        assertEquals(WindDirection.SOUTHEAST, pos1.windDirectionToOrNull(GridPos(4,4) ))
        assertEquals(WindDirection.NORTHWEST, pos1.windDirectionToOrNull(GridPos(-4,-4) ))
        assertEquals(WindDirection.SOUTHWEST, pos1.windDirectionToOrNull(GridPos(-4,4) ))
        assertEquals(WindDirection.NORTHEAST, pos1.windDirectionToOrNull(GridPos(4,-4) ))

        assertNull(pos1.directionToOrNull(GridPos(3,4)))
    }

    @Test
    fun moveTest1() {
        val pos1 = GridPos(0,0)
        assertEquals(GridPos(0,-1), pos1.up())
        assertEquals(GridPos(0,1), pos1.down())
        assertEquals(GridPos(1,0), pos1.right())
        assertEquals(GridPos(-1,0), pos1.left())
    }

    @Test
    fun moveTest2() {
        val pos1 = GridPos(0,0)
        assertEquals(GridPos(0,-1), pos1.north())
        assertEquals(GridPos(0,1), pos1.south())
        assertEquals(GridPos(1,0), pos1.east())
        assertEquals(GridPos(-1,0), pos1.west())

        assertEquals(GridPos(1,-1), pos1.northeast())
        assertEquals(GridPos(1,1), pos1.southeast())
        assertEquals(GridPos(-1,-1), pos1.northwest())
        assertEquals(GridPos(-1,1), pos1.southwest())
    }
}