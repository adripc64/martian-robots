package org.adripc64.guidesmiths.challenges.martianrobots.application.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Orientation;

@Data
public class CreateRobotCommand {

    @Schema(description = "The initial X coordinate", required = true, minimum = "0", example = "2")
    private int x;

    @Schema(description = "The initial Y coordinate", required = true, minimum = "0", example = "2")
    private int y;

    @Schema(description = "The initial orientation", required = true, example = "N")
    private Orientation orientation;

    @Schema(description = "The instructions for the robot. (L = Turn Left, R = Turn Right, F = Move Forward)", required = true, allowableValues = { "L", "R", "F" }, example = "FFRFLLFF")
    private String instructions;

}
