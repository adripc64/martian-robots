package org.adripc64.guidesmiths.challenges.martianrobots.domain.model;

import lombok.Data;
import lombok.ToString;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.exception.PositionOutOfBoundsException;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.exception.RobotLostException;

import java.util.Objects;

/**
 * Represents a robot deployed in a planet.
 */
@Data
public class Robot {

    /**
     * An instruction represents an action that a Robot can perform (eg. turn, move, etc)
     */
    private enum Instruction {

        /**
         * Turns left 90 degrees and remains on the current grid point
         */
        L,

        /**
         * Turns right 90 degrees and remains on the current grid point
         */
        R,

        /**
         * Moves forward one grid point in the direction of the current orientation and maintains the same orientation
         */
        F

    }

    private static final String INSTRUCTIONS_DELIMITER = "";

    /**
     * The planet where the robot is deployed
     */
    @ToString.Exclude
    private final Planet planet;

    /**
     * The initial position where the robot was deployed
     */
    private final Position initPosition;

    /**
     * The current position of the robot
     */
    private Position position;

    /**
     * The instructions given to the robot
     */
    private String instructions;

    /**
     * If the robot was lost moving off an edge of the planet
     */
    private boolean lost;

    public Robot(Planet planet, Position position, String instructions) {
        this.planet = Objects.requireNonNull(planet);
        this.initPosition = Objects.requireNonNull(position);
        this.position = Objects.requireNonNull(position);
        this.instructions = Objects.requireNonNull(instructions);
    }

    /**
     * Execute the robot instructions.
     *
     * It will skip unknown instructions.
     *
     * @throws RobotLostException If the robot move off an edge of the planet
     */
    public void execute() throws RobotLostException {

        if (instructions.isEmpty()) {
            // nothing to do
            return;
        }

        for (String character : instructions.split(INSTRUCTIONS_DELIMITER)) {
            try {
                Instruction instruction = Instruction.valueOf(character);
                switch (instruction) {
                    case L:
                        turnLeft();
                        break;
                    case R:
                        turnRight();
                        break;
                    case F:
                        forward();
                        break;
                    default:
                        // known instruction but not yet implemented, just skip it
                }
            } catch (IllegalArgumentException e) {
                // unknown instruction, just skip it
            } catch (PositionOutOfBoundsException e) {
                lost = true;
                throw new RobotLostException(this);
            }
        }

    }

    /**
     * Turn to the left
     */
    private void turnLeft() {
        position = position.turnLeft();
    }

    /**
     * Turn to the right
     */
    private void turnRight() {
        position = position.turnRight();
    }

    /**
     * Move forward in the direction of the current orientation
     *
     * @throws PositionOutOfBoundsException If the robot is lost moving off an edge of the planet
     */
    private void forward() throws PositionOutOfBoundsException {
        Position nextPosition = position.forward();
        if (planet.isPositionOutOfBounds(nextPosition)) {
            if (planet.hasScent(position)) {
                // just ignore the instruction
            } else {
                planet.addScent(position);
                throw new PositionOutOfBoundsException();
            }
        } else {
            position = nextPosition;
        }
    }

}
