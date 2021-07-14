package org.adripc64.guidesmiths.challenges.martianrobots.application.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreatePlanetCommand {

    @Schema(description = "The planet width", required = true, minimum = "1", example = "5")
    private int width;

    @Schema(description = "The planet height", required = true, minimum = "1", example = "5")
    private int height;

}
