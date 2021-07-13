package org.adripc64.guidesmiths.challenges.martianrobots.domain.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class CoordsTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testAdd() {

        Coords a = new Coords(0, 0);
        Coords b = new Coords(1, 2);

        assertEquals(new Coords(1, 2), a.add(new Coords(1, 2)));
        assertEquals(new Coords(-1, -2), a.add(new Coords(-1, -2)));
        assertEquals(new Coords(0, 0), b.add(new Coords(-1, -2)));
        assertEquals(new Coords(8, -3), b.add(new Coords(7, -5)));

    }

    @Test(expected = NullPointerException.class)
    public void testParseNull_throwsNullPointerException() {
        Coords.parse(null);
    }

    @Test
    public void testParseEmptyString_throwsIllegalArgumentException() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Empty coordinates string");
        Coords.parse("");
    }

    @Test
    public void testParseLessItems_throwsIllegalArgumentException() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Coordinates string must contain 2 elements");
        Coords.parse("1");
    }

    @Test
    public void testParseMoreItems_throwsIllegalArgumentException() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Coordinates string must contain 2 elements");
        Coords.parse("1 2 3");
    }

    @Test
    public void testParseXNotNumber_throwsIllegalArgumentException() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("The x-coordinate must be a number but was: \"A\"");
        Coords.parse("A 1");
    }

    @Test
    public void testParseYNotNumber_throwsIllegalArgumentException() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("The y-coordinate must be a number but was: \"A\"");
        Coords.parse("1 A");
    }

    @Test
    public void testParse() {
        assertEquals(new Coords(0, 0), Coords.parse("0 0"));
        assertEquals(new Coords(1, 2), Coords.parse("1 2"));
        assertEquals(new Coords(-1, -2), Coords.parse("-1 -2"));
    }

}
