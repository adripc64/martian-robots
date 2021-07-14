package org.adripc64.guidesmiths.challenges.martianrobots.application.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.adripc64.guidesmiths.challenges.martianrobots.application.api.dto.CreatePlanetCommand;
import org.adripc64.guidesmiths.challenges.martianrobots.application.api.dto.CreateRobotCommand;
import org.adripc64.guidesmiths.challenges.martianrobots.application.api.dto.PlanetDetails;
import org.adripc64.guidesmiths.challenges.martianrobots.application.api.dto.RobotDetails;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.exception.PlanetNotFoundException;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.exception.PositionOutOfBoundsException;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.exception.RobotLostException;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Coords;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Planet;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Position;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Robot;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.service.MartianRobotsService;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.util.Optional;

@Tag(name = "Planets")
@RestController
public class PlanetsController {

    private static final String PLANETS_ENDPOINT = "/planets";
    private static final String PLANET_ENDPOINT = "/planets/{planetId}";
    private static final String PLANET_ROBOTS_ENDPOINT = "/planets/{planetId}/robots";

    private final MartianRobotsService planetsService;

    public PlanetsController(MartianRobotsService planetsService) {
        this.planetsService = planetsService;
    }

    @Operation(summary = "List planets", description = "List planets using pagination.")
    @PageableAsQueryParam
    @GetMapping(value = PLANETS_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<PlanetDetails> listPlanets(@Parameter(hidden = true)Pageable pageable) {
        return planetsService.listPlanets(pageable).map(PlanetDetails::from);
    }

    @Operation(summary = "Create a planet", description = "Create a planet of the specified size.")
    @ApiResponse(responseCode = "201", description = "Planet created", content = @Content(schema = @Schema(implementation = PlanetDetails.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(
            mediaType = MediaType.TEXT_PLAIN_VALUE, examples = {
                @ExampleObject(name = "Invalid planet size", value = "Invalid planet size... Width and height must be > 0.")
            }
    ))
    @PostMapping(value = PLANETS_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createPlanet(@RequestBody CreatePlanetCommand command) {

        int width = command.getWidth();
        int height = command.getHeight();

        try {
            Planet planet = planetsService.createPlanet(width, height);
            PlanetDetails planetDetails = PlanetDetails.from(planet);

            URI location = new UriTemplate(PLANET_ENDPOINT).expand(planetDetails.getId());
            return ResponseEntity.created(location).body(planetDetails);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @Operation(summary = "Get a planet", description = "Get a planet by id.")
    @ApiResponse(responseCode = "200", description = "The planet details", content = @Content(schema = @Schema(implementation = PlanetDetails.class)))
    @ApiResponse(responseCode = "404", description = "Planet not found", content = @Content)
    @GetMapping(value = PLANET_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlanetDetails> getPlanet(@PathVariable String planetId) {
        Optional<Planet> optionalPlanet = planetsService.findById(planetId);
        return ResponseEntity.of(optionalPlanet.map(PlanetDetails::from));
    }

    @Operation(summary = "Delete a planet", description = "Delete a planet by id.")
    @ApiResponse(responseCode = "204", description = "The planet has been deleted", content = @Content)
    @ApiResponse(responseCode = "404", description = "Planet not found", content = @Content)
    @DeleteMapping(value = PLANET_ENDPOINT)
    public ResponseEntity deletePlanet(@PathVariable String planetId) {
        try {
            planetsService.deletePlanet(planetId);
            return ResponseEntity.noContent().build();
        } catch (PlanetNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Deploy a robot in a planet", description = "Deploys a robot in a planet and executes the instructions.")
    @ApiResponse(responseCode = "201", description = "Robot created and instructions executed.", content = @Content(schema = @Schema(implementation = RobotDetails.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(
            mediaType = MediaType.TEXT_PLAIN_VALUE, examples = {
                    @ExampleObject(name = "Position out of bounds", value = "The given position is out of the planet bounds."),
                    @ExampleObject(name = "Invalid orientation", value = "Cannot deserialize value ...")
            }
    ))
    @ApiResponse(responseCode = "404", description = "Planet not found", content = @Content)
    @PostMapping(value = PLANET_ROBOTS_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createRobot(@PathVariable String planetId, @RequestBody CreateRobotCommand command) {
        Position position = new Position(new Coords(command.getX(), command.getY()), command.getOrientation());
        Robot robot;
        try {
            robot = planetsService.deployAndExecuteRobotInPlanetById(planetId, position, command.getInstructions());
        } catch (RobotLostException e) {
            robot = e.getRobot();
        } catch (PositionOutOfBoundsException e) {
            return ResponseEntity.badRequest().body("The given position is out of the planet bounds.");
        } catch (PlanetNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        RobotDetails robotDetails = RobotDetails.from(robot);
        return ResponseEntity.status(HttpStatus.CREATED).body(robotDetails);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity handleHttpMessageNotReadableException(final HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body(e.getMostSpecificCause().getMessage());
    }

}
