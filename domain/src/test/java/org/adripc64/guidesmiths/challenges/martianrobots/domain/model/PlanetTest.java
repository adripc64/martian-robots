package org.adripc64.guidesmiths.challenges.martianrobots.domain.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PlanetTest {

    @Test
    public void testPositionOutOfBounds() {

        Planet planet = new Planet("", 2, 5);

        assertFalse(planet.isPositionOutOfBounds(0, 0));
        assertTrue(planet.isPositionOutOfBounds(-1, 0));
        assertTrue(planet.isPositionOutOfBounds(0, -1));
        assertTrue(planet.isPositionOutOfBounds(-1, -1));

        assertFalse(planet.isPositionOutOfBounds(1, 4));
        assertTrue(planet.isPositionOutOfBounds(2, 4));
        assertTrue(planet.isPositionOutOfBounds(1, 5));
        assertTrue(planet.isPositionOutOfBounds(2, 5));

    }

    @Test
    public void testScents() {

        Planet planet = new Planet("", 2, 5);

        assertFalse(planet.hasScent(0, 0));
        planet.addScent(0, 0);
        assertTrue(planet.hasScent(0, 0));

    }

}
