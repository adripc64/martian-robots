package org.adripc64.guidesmiths.challenges.martianrobots.domain.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class OrientationTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testOrientationIteration() {

        assertEquals(Orientation.W, Orientation.N.left());
        assertEquals(Orientation.E, Orientation.N.right());

        assertEquals(Orientation.N, Orientation.E.left());
        assertEquals(Orientation.S, Orientation.E.right());

        assertEquals(Orientation.E, Orientation.S.left());
        assertEquals(Orientation.W, Orientation.S.right());

        assertEquals(Orientation.S, Orientation.W.left());
        assertEquals(Orientation.N, Orientation.W.right());

    }

    @Test(expected = NullPointerException.class)
    public void testParseNull_throwsNullPointerException() {
        Orientation.parse(null);
    }

    @Test
    public void testParseEmptyString_throwsIllegalArgumentException() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("The orientation must be one of [N, E, S, W] but was: \"\"");
        Orientation.parse("");
    }

    @Test
    public void testParseInvalidString_throwsIllegalArgumentException() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("The orientation must be one of [N, E, S, W] but was: \"A\"");
        Orientation.parse("A");
    }

    @Test
    public void testParse() {
        assertEquals(Orientation.N, Orientation.parse("N"));
        assertEquals(Orientation.E, Orientation.parse("E"));
        assertEquals(Orientation.S, Orientation.parse("S"));
        assertEquals(Orientation.W, Orientation.parse("W"));
    }

}
