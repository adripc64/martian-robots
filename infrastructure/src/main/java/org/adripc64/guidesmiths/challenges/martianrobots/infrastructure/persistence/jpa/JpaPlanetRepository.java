package org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.jpa;

import org.adripc64.guidesmiths.challenges.martianrobots.domain.exception.PlanetNotFoundException;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Coords;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Planet;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Position;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Robot;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.repository.PlanetRepository;
import org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.jpa.entity.JpaCoords;
import org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.jpa.entity.JpaPlanet;
import org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.jpa.entity.JpaRobot;
import org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.jpa.repository.PlanetJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaPlanetRepository implements PlanetRepository {

    private final PlanetJpaRepository jpaRepository;

    public JpaPlanetRepository(PlanetJpaRepository repository) {
        this.jpaRepository = repository;
    }

    @Override
    public Page<Planet> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable).map(this::map);
    }

    @Override
    public Optional<Planet> findById(String id) {
        Optional<JpaPlanet> optionalJpaPlanet = jpaRepository.findById(id);
        return optionalJpaPlanet.map(this::map);
    }

    @Override
    public Planet save(Planet planet) {
        return map(jpaRepository.save(map(planet)));
    }

    @Override
    public void deleteById(String id) throws PlanetNotFoundException {
        if (jpaRepository.existsById(id)) {
            jpaRepository.deleteById(id);
        } else {
            throw new PlanetNotFoundException();
        }
    }

    private JpaPlanet map(Planet planet) {
        // Get existent instance or create new one
        Optional<JpaPlanet> optionalJpaPlanet = jpaRepository.findById(planet.getId());
        JpaPlanet jpaPlanet = optionalJpaPlanet.orElse(new JpaPlanet());

        // Update all fields
        jpaPlanet.setId(planet.getId());
        jpaPlanet.setWidth(planet.getWidth());
        jpaPlanet.setHeight(planet.getHeight());
        jpaPlanet.setScents(planet.getScentsAsList().stream().map(JpaCoords::from).collect(Collectors.toList()));
        jpaPlanet.setRobots(planet.getRobots().stream().map(JpaRobot::from).collect(Collectors.toList()));

        return jpaPlanet;
    }

    private Planet map(JpaPlanet jpaPlanet) {

        Planet planet = new Planet(jpaPlanet.getId(), jpaPlanet.getWidth(), jpaPlanet.getHeight());

        for (JpaCoords scent : jpaPlanet.getScents()) {
            planet.addScent(scent.getX(), scent.getY());
        }

        for (JpaRobot jpaRobot : jpaPlanet.getRobots()) {
            planet.getRobots().add(map(jpaRobot, planet));
        }

        return planet;
    }

    private Robot map(JpaRobot jpaRobot, Planet planet) {
        Position initPosition = new Position(new Coords(jpaRobot.getInitPosition().getCoords().getX(), jpaRobot.getInitPosition().getCoords().getY()), jpaRobot.getInitPosition().getOrientation());
        Position position = new Position(new Coords(jpaRobot.getPosition().getCoords().getX(), jpaRobot.getPosition().getCoords().getY()), jpaRobot.getPosition().getOrientation());
        Robot robot = new Robot(planet, initPosition, jpaRobot.getInstructions());
        robot.setPosition(position);
        robot.setLost(jpaRobot.isLost());
        return robot;

    }

}
