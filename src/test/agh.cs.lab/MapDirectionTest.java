package agh.cs.lab;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {
    @Test
    public void testNext(){
        assertEquals(MapDirection.NORTH.next(), MapDirection.EAST);
        assertEquals(MapDirection.SOUTH.next(), MapDirection.WEST);
        assertEquals(MapDirection.WEST.next(), MapDirection.NORTH);
        assertEquals(MapDirection.EAST.next(), MapDirection.SOUTH);
        assertEquals(MapDirection.NORTHEAST.next(), MapDirection.SOUTHEAST);
        assertEquals(MapDirection.NORTHWEST.next(), MapDirection.NORTHEAST);
        assertEquals(MapDirection.SOUTHEAST.next(), MapDirection.SOUTHWEST);
        assertEquals(MapDirection.SOUTHWEST.next(), MapDirection.NORTHWEST);
    }

    @Test
    public void testPrevious(){
        assertEquals(MapDirection.NORTH.previous(), MapDirection.WEST);
        assertEquals(MapDirection.SOUTH.previous(), MapDirection.EAST);
        assertEquals(MapDirection.WEST.previous(), MapDirection.SOUTH);
        assertEquals(MapDirection.EAST.previous(), MapDirection.NORTH);
        assertEquals(MapDirection.NORTHEAST.previous(), MapDirection.NORTHWEST);
        assertEquals(MapDirection.NORTHWEST.previous(), MapDirection.SOUTHWEST);
        assertEquals(MapDirection.SOUTHWEST.previous(), MapDirection.SOUTHEAST);
        assertEquals(MapDirection.SOUTHEAST.previous(), MapDirection.NORTHEAST);
    }
}