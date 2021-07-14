package org.adripc64.guidesmiths.challenges.martianrobots.application.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Coords;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Planet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PlanetDetails {

    @Schema(description = "The planet identifier")
    public String id;

    @Schema(description = "The planet width")
    public int width;

    @Schema(description = "The planet height")
    public int height;

    @Schema(description = "The remaining scents in the planet")
    private List<Coords> scents = new ArrayList<>();

    @Schema(description = "The robots sent to the planet")
    private List<RobotDetails> robots = new ArrayList<>();

    public static PlanetDetails from(Planet planet) {

        PlanetDetails planetDetails = new PlanetDetails();
        planetDetails.setId(planet.getId());
        planetDetails.setWidth(planet.getWidth());
        planetDetails.setHeight(planet.getHeight());

        boolean[][] scents = planet.getScents();
        for (int y = 0; y < scents.length; y++) {
            boolean[] row = scents[y];
            for (int x = 0; x < row.length; x++) {
                if (scents[y][x]) {
                    planetDetails.getScents().add(new Coords(x, y));
                }
            }
        }

        planetDetails.setRobots(planet.getRobots().stream().map(RobotDetails::from).collect(Collectors.toList()));

        return planetDetails;
    }

}
