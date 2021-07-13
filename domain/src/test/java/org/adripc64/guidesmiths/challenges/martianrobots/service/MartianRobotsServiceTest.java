package org.adripc64.guidesmiths.challenges.martianrobots.service;

import org.adripc64.guidesmiths.challenges.martianrobots.domain.exception.PlanetNotFoundException;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.exception.PositionOutOfBoundsException;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.exception.RobotLostException;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.factory.PlanetFactory;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Orientation;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Planet;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Position;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Robot;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.repository.PlanetRepository;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.service.MartianRobotsService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MartianRobotsServiceTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Mock
    private PlanetRepository planetRepository;

    @InjectMocks
    private MartianRobotsService martianRobotsService;

    @Test
    public void testCreatePlanet() {
        when(planetRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        Planet planet = martianRobotsService.createPlanet(2, 2);
        verify(planetRepository, times(1)).save(planet);
    }

    @Test
    public void testListPlanets() {
        PageRequest pageable = PageRequest.of(0, 10);
        PageImpl<Planet> page = new PageImpl<>(new ArrayList<>());
        when(planetRepository.findAll(pageable)).thenReturn(page);

        Page<Planet> returnedPage = martianRobotsService.listPlanets(pageable);
        assertEquals(page, returnedPage);
        verify(planetRepository, times(1)).findAll(pageable);
    }

    @Test
    public void testDeployRobot() {
        Planet planet = PlanetFactory.createPlanetOfSize(2, 2);
        when(planetRepository.findById("id")).thenReturn(Optional.of(planet));

        Robot robot = martianRobotsService.deployAndExecuteRobotInPlanetById("id", new Position(0, 0, Orientation.N), "FRF");
        assertEquals(new Position(1, 1, Orientation.E), robot.getPosition());
        assertFalse(robot.isLost());
        verify(planetRepository, times(1)).save(planet);
    }

    @Test
    public void testDeployRobotUnknownPlanetId_throwsPlanetNotFoundException() {

        Planet planet = PlanetFactory.createPlanetOfSize(2, 2);
        exceptionRule.expect(PlanetNotFoundException.class);

        martianRobotsService.deployAndExecuteRobotInPlanetById("id2", new Position(0, 0, Orientation.N), "FRF");
        verify(planetRepository, times(1)).save(planet);
    }

    @Test
    public void testDeployRobotPositionOutOfBounds_throwsPositionOutOfBoundsException() {

        Planet planet = PlanetFactory.createPlanetOfSize(2, 2);
        when(planetRepository.findById("id")).thenReturn(Optional.of(planet));
        exceptionRule.expect(PositionOutOfBoundsException.class);

        martianRobotsService.deployAndExecuteRobotInPlanetById("id", new Position(5, 5, Orientation.N), "FRF");
        verify(planetRepository, times(1)).save(planet);
    }

    @Test
    public void testDeployRobotToBeLost_throwsRobotLostException() {

        Planet planet = PlanetFactory.createPlanetOfSize(2, 2);
        when(planetRepository.findById("id")).thenReturn(Optional.of(planet));
        exceptionRule.expect(RobotLostException.class);

        martianRobotsService.deployAndExecuteRobotInPlanetById("id", new Position(1, 1, Orientation.N), "F");
        verify(planetRepository, times(1)).save(planet);
    }


    private void assertRobot(Robot robot, Position expectedPosition, boolean expectedLost) {
        try {
            robot.execute();
//            System.out.println(robot.toString());
            if (expectedLost) {
                fail("The robot was expected to be lost");
            }
        } catch (RobotLostException e) {
            System.out.println(robot.toString());
            if (!expectedLost) {
                fail("The robot wasn't expected to be lost");
            }
        } finally {
            assertEquals(expectedPosition, robot.getPosition());
            assertEquals(expectedLost, robot.isLost());
        }
    }

    @Test
    public void testSample() {

        Planet planet = PlanetFactory.createPlanetWithMaxCoordinates(5, 3);
        Robot robot1 = planet.getRobotFactory().createRobot(new Position(1, 1, Orientation.E), "RFRFRFRF");
        Robot robot2 = planet.getRobotFactory().createRobot(new Position(3, 2, Orientation.N), "FRRFLLFFRRFLL");
        Robot robot3 = planet.getRobotFactory().createRobot(new Position(0, 3, Orientation.W), "LLFFFLFLFL");

        assertRobot(robot1, new Position(1, 1, Orientation.E), false);
        assertRobot(robot2, new Position(3, 3, Orientation.N), true);
        assertRobot(robot3, new Position(2, 3, Orientation.S), false);

    }

}
