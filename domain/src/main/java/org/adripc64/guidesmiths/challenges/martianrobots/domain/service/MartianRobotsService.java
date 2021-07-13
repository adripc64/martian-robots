package org.adripc64.guidesmiths.challenges.martianrobots.domain.service;

import org.adripc64.guidesmiths.challenges.martianrobots.domain.exception.PlanetNotFoundException;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.exception.PositionOutOfBoundsException;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.exception.RobotLostException;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.factory.PlanetFactory;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Planet;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Position;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Robot;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.repository.PlanetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class MartianRobotsService {

    private final PlanetRepository planetRepository;

    public MartianRobotsService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    /**
     * Retrieve planets using pagination.
     *
     * @param pageable Pagination request
     * @return Page of planets
     */
    public Page<Planet> listPlanets(Pageable pageable) {
        return planetRepository.findAll(pageable);
    }

    /**
     * Create a planet of the specified size.
     *
     * Persists the created planet.
     *
     * @param width Planet width
     * @param height Planet height
     * @return The planet
     * @throws IllegalArgumentException if width or height is <= 0
     */
    public Planet createPlanet(int width, int height) throws IllegalArgumentException {
        Planet planet = PlanetFactory.createPlanetOfSize(width, height);
        planet = planetRepository.save(planet);
        return planet;
    }

    /**
     * Get a planet by id.
     *
     * @param planetId The planet identifier
     * @return The optional planet
     */
    public Optional<Planet> findById(String planetId) {
        return planetRepository.findById(planetId);
    }

    /**
     * Delete a planet by id.
     *
     * @param planetId The planet identifier
     * @throws PlanetNotFoundException if there is no planet with the given id
     */
    public void deletePlanet(String planetId) throws PlanetNotFoundException {
        planetRepository.deleteById(planetId);
    }

    /**
     * Deploys a robot in a planet by id. Then executes the robot instructions.
     *
     * @param planetId The planet identifier
     * @param position The robot position
     * @param instructions The robot instructions
     * @return The deployed robot
     * @throws PlanetNotFoundException if there is no planet with the given id
     * @throws PositionOutOfBoundsException if initial position is out of bounds
     * @throws RobotLostException if the robot is lost when executing instructions
     */
    public Robot deployAndExecuteRobotInPlanetById(String planetId, Position position, String instructions) throws PlanetNotFoundException, PositionOutOfBoundsException, RobotLostException {

        Optional<Planet> optionalPlanet = planetRepository.findById(planetId);
        Planet planet = optionalPlanet.orElseThrow(() -> new PlanetNotFoundException("There is no planet with id " + planetId));

        Robot robot = planet.getRobotFactory().createRobot(position, instructions);

        try {
            robot.execute();
            return robot;
        } catch (RobotLostException e) {
            throw e;
        } finally {
            planetRepository.save(planet);
        }

    }

}
