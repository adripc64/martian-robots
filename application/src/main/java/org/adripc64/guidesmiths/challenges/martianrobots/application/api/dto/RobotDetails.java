package org.adripc64.guidesmiths.challenges.martianrobots.application.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Position;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Robot;

@Data
@AllArgsConstructor
public class RobotDetails {

    @Schema(description = "The initial position")
    private Position initPosition;

    @Schema(description = "The current position")
    private Position position;

    @Schema(description = "The given instructions")
    private String instructions;

    @Schema(description = "If the robot was lost or not")
    private boolean lost;

    public static RobotDetails from(Robot robot) {
        return new RobotDetails(robot.getInitPosition(), robot.getPosition(), robot.getInstructions(), robot.isLost());
    }

}
