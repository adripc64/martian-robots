package org.adripc64.guidesmiths.challenges.martianrobots.domain.factory;

import org.adripc64.guidesmiths.challenges.martianrobots.domain.exception.PositionOutOfBoundsException;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Orientation;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Planet;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Position;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Robot;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RobotFactoryTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Mock
    private Planet planet;

    @InjectMocks
    private RobotFactory robotFactory;

    @Test
    public void testCreateRobot() {

        RobotFactory robotFactory = new RobotFactory(planet);
        when(planet.isPositionOutOfBounds(any())).thenReturn(false);

        Robot robot = robotFactory.createRobot(new Position(2, 2, Orientation.N), "");

        assertEquals(planet, robot.getPlanet());
        assertEquals(new Position(2, 2, Orientation.N), robot.getPosition());
        assertEquals("", robot.getInstructions());

    }

    @Test
    public void testCreateRobotOutOfBounds_throwsPositionOutOfBoundsException() {

        RobotFactory robotFactory = new RobotFactory(planet);
        when(planet.isPositionOutOfBounds(any())).thenReturn(true);
        exceptionRule.expect(PositionOutOfBoundsException.class);

        robotFactory.createRobot(new Position(2, 2, Orientation.N), "");

    }

}
