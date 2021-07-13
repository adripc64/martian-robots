package org.adripc64.guidesmiths.challenges.martianrobots.domain.factory;

import org.adripc64.guidesmiths.challenges.martianrobots.domain.exception.PositionOutOfBoundsException;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Planet;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Position;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Robot;

public class RobotFactory {

    private final Planet planet;

    public RobotFactory(Planet planet) {
        this.planet = planet;
    }

    /**
     * Deploys a robot in the planet
     *
     * @param position The initial position
     * @param instructions The instructions for the robot
     * @return The deployed robot
     * @throws PositionOutOfBoundsException If the initial position is out of bounds
     */
    public Robot createRobot(Position position, String instructions) throws PositionOutOfBoundsException {
        if (planet.isPositionOutOfBounds(position)) {
            throw new PositionOutOfBoundsException();
        } else {
            Robot robot = new Robot(planet, position, instructions);
            planet.getRobots().add(robot);
            return robot;
        }
    }

}
