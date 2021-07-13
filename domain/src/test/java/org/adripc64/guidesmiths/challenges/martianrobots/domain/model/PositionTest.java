package org.adripc64.guidesmiths.challenges.martianrobots.domain.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class PositionTest {

    private static final Position POSITION_NORTH = new Position(0, 0, Orientation.N);
    private static final Position POSITION_EAST = new Position(0, 0, Orientation.E);
    private static final Position POSITION_SOUTH = new Position(0, 0, Orientation.S);
    private static final Position POSITION_WEST = new Position(0, 0, Orientation.W);

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testTurnLeft() {
        assertEquals(POSITION_WEST, POSITION_NORTH.turnLeft());
        assertEquals(POSITION_SOUTH, POSITION_WEST.turnLeft());
        assertEquals(POSITION_EAST, POSITION_SOUTH.turnLeft());
        assertEquals(POSITION_NORTH, POSITION_EAST.turnLeft());
    }

    @Test
    public void testTurnRight() {
        assertEquals(POSITION_EAST, POSITION_NORTH.turnRight());
        assertEquals(POSITION_SOUTH, POSITION_EAST.turnRight());
        assertEquals(POSITION_WEST, POSITION_SOUTH.turnRight());
        assertEquals(POSITION_NORTH, POSITION_WEST.turnRight());
    }

    @Test
    public void testForward() {
        assertEquals(new Position(0, 1, Orientation.N), POSITION_NORTH.forward());
        assertEquals(new Position(1, 0, Orientation.E), POSITION_EAST.forward());
        assertEquals(new Position(0, -1, Orientation.S), POSITION_SOUTH.forward());
        assertEquals(new Position(-1, 0, Orientation.W), POSITION_WEST.forward());
    }

    @Test(expected = NullPointerException.class)
    public void testParseNull_throwsNullPointerException() {
        Position.parse(null);
    }

    @Test
    public void testParseEmptyString_throwsIllegalArgumentException() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Empty position string");
        Position.parse("");
    }

    @Test
    public void testParseLessItems_throwsIllegalArgumentException() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Position string must contain 3 elements");
        Position.parse("1 2");
    }

    @Test
    public void testParseMoreItems_throwsIllegalArgumentException() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Position string must contain 3 elements");
        Position.parse("1 2 3 4");
    }

    @Test
    public void testParse() {
        assertEquals(new Position(0, 0, Orientation.N), Position.parse("0 0 N"));
        assertEquals(new Position(1, 2, Orientation.S), Position.parse("1 2 S"));
        assertEquals(new Position(-1, -2, Orientation.E), Position.parse("-1 -2 E"));
    }

}
