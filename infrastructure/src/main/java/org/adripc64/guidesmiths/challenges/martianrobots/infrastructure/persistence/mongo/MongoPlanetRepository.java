package org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.mongo;

import org.adripc64.guidesmiths.challenges.martianrobots.domain.exception.PlanetNotFoundException;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Coords;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Planet;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Position;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Robot;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.repository.PlanetRepository;
import org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.mongo.entity.MongoCoords;
import org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.mongo.entity.MongoPlanet;
import org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.mongo.entity.MongoRobot;
import org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.mongo.repository.PlanetMongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MongoPlanetRepository implements PlanetRepository {

    private final PlanetMongoRepository mongoRepository;

    public MongoPlanetRepository(PlanetMongoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    @Override
    public Page<Planet> findAll(Pageable pageable) {
        return mongoRepository.findAll(pageable).map(this::map);
    }

    @Override
    public Optional<Planet> findById(String id) {
        Optional<MongoPlanet> optionalMongoPlanet = mongoRepository.findById(id);
        return optionalMongoPlanet.map(this::map);
    }

    @Override
    public Planet save(Planet planet) {
        return map(mongoRepository.save(map(planet)));
    }

    @Override
    public void deleteById(String id) throws PlanetNotFoundException {
        if (mongoRepository.existsById(id)) {
            mongoRepository.deleteById(id);
        } else {
            throw new PlanetNotFoundException();
        }
    }

    private MongoPlanet map(Planet planet) {
        // Get existent instance or create new one
        Optional<MongoPlanet> optionalMongoPlanet = mongoRepository.findById(planet.getId());
        MongoPlanet mongoPlanet = optionalMongoPlanet.orElse(new MongoPlanet());

        // Update all fields
        mongoPlanet.setId(planet.getId());
        mongoPlanet.setWidth(planet.getWidth());
        mongoPlanet.setHeight(planet.getHeight());
        mongoPlanet.setScents(planet.getScentsAsList().stream().map(MongoCoords::from).collect(Collectors.toList()));
        mongoPlanet.setRobots(planet.getRobots().stream().map(MongoRobot::from).collect(Collectors.toList()));

        return mongoPlanet;
    }

    private Planet map(MongoPlanet mongoPlanet) {

        Planet planet = new Planet(mongoPlanet.getId(), mongoPlanet.getWidth(), mongoPlanet.getHeight());

        for (MongoCoords scent : mongoPlanet.getScents()) {
            planet.addScent(scent.getX(), scent.getY());
        }

        for (MongoRobot mongoRobot : mongoPlanet.getRobots()) {
            planet.getRobots().add(map(mongoRobot, planet));
        }

        return planet;
    }

    private Robot map(MongoRobot mongoRobot, Planet planet) {
        Position initPosition = new Position(new Coords(mongoRobot.getInitPosition().getCoords().getX(), mongoRobot.getInitPosition().getCoords().getY()), mongoRobot.getInitPosition().getOrientation());
        Position position = new Position(new Coords(mongoRobot.getPosition().getCoords().getX(), mongoRobot.getPosition().getCoords().getY()), mongoRobot.getPosition().getOrientation());
        Robot robot = new Robot(planet, initPosition, mongoRobot.getInstructions());
        robot.setPosition(position);
        robot.setLost(mongoRobot.isLost());
        return robot;

    }

}
