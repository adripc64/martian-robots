package org.adripc64.guidesmiths.challenges.martianrobots.domain.factory;

import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Planet;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class PlanetFactoryTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testCreateWithSize() {
        Planet planet = PlanetFactory.createPlanetOfSize(2, 5);
        assertEquals(2, planet.getWidth());
        assertEquals(5, planet.getHeight());
    }

    @Test
    public void testCreateWithMaxCoordinates() {
        Planet planet = PlanetFactory.createPlanetWithMaxCoordinates(2, 5);
        assertEquals(3, planet.getWidth());
        assertEquals(6, planet.getHeight());
    }

    @Test
    public void testCreateWithSizeZero_throwsIllegalArgumentException() {
        exceptionRule.expect(IllegalArgumentException.class);
        PlanetFactory.createPlanetOfSize(0, 0);
    }

    @Test
    public void testCreateWithSizeNegative_throwsIllegalArgumentException() {
        exceptionRule.expect(IllegalArgumentException.class);
        PlanetFactory.createPlanetOfSize(-1, -1);
    }

    @Test
    public void testCreateWithMaxCoordsZero_ok() {
        Planet planet = PlanetFactory.createPlanetWithMaxCoordinates(0, 0);
        assertEquals(1, planet.getWidth());
        assertEquals(1, planet.getHeight());
    }

    @Test
    public void testCreateWithMaxCoordsNegative_throwsIllegalArgumentException() {
        exceptionRule.expect(IllegalArgumentException.class);
        PlanetFactory.createPlanetWithMaxCoordinates(-1, -1);
    }

}
