package org.adripc64.guidesmiths.challenges.martianrobots.domain.model;

import org.adripc64.guidesmiths.challenges.martianrobots.domain.exception.RobotLostException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RobotTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Mock
    private Planet planet;

    @Test
    public void testTurnLeft() {

        Robot robot = new Robot(planet, new Position(2, 2, Orientation.N), "L");
        robot.execute();

        assertEquals(new Position(2, 2, Orientation.W), robot.getPosition());
        assertFalse(robot.isLost());

    }

    @Test
    public void testTurnRight() {

        Robot robot = new Robot(planet, new Position(2, 2, Orientation.N), "R");
        robot.execute();

        assertEquals(new Position(2, 2, Orientation.E), robot.getPosition());
        assertFalse(robot.isLost());

    }

    @Test
    public void testForward() {

        when(planet.isPositionOutOfBounds(any())).thenReturn(false);

        Robot robot = new Robot(planet, new Position(2, 2, Orientation.N), "F");
        robot.execute();

        assertEquals(new Position(2, 3, Orientation.N), robot.getPosition());
        assertFalse(robot.isLost());

    }

    @Test
    public void testForwardOutOfBounds_throwsRobotLostException() {

        when(planet.isPositionOutOfBounds(any())).thenReturn(true);
        when(planet.hasScent(any())).thenReturn(false);
        exceptionRule.expect(RobotLostException.class);

        Robot robot = new Robot(planet, new Position(2, 2, Orientation.N), "F");
        robot.execute();

        assertEquals(new Position(2, 2, Orientation.N), robot.getPosition());
        assertTrue(robot.isLost());
        verify(planet, times(1)).addScent(robot.getPosition());

    }

    @Test
    public void testForwardOutOfBoundsWithScent_keepsPosition() {

        when(planet.isPositionOutOfBounds(any())).thenReturn(true);
        when(planet.hasScent(any())).thenReturn(true);

        Robot robot = new Robot(planet, new Position(2, 2, Orientation.N), "F");
        robot.execute();

        assertEquals(new Position(2, 2, Orientation.N), robot.getPosition());
        assertFalse(robot.isLost());
        verify(planet, times(0)).addScent(robot.getPosition());

    }



}
