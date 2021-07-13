package org.adripc64.guidesmiths.challenges.martianrobots.domain.exception;

import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Robot;

public class RobotLostException extends RuntimeException {

    private final Robot robot;

    public RobotLostException(Robot robot) {
        super();
        this.robot = robot;
    }

    public Robot getRobot() {
        return robot;
    }

}
