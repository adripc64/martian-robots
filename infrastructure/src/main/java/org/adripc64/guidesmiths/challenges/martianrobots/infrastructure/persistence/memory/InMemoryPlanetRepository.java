package org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.memory;

import org.adripc64.guidesmiths.challenges.martianrobots.domain.exception.PlanetNotFoundException;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Planet;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.repository.PlanetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class InMemoryPlanetRepository implements PlanetRepository {

    private static final Map<String, Planet> planets = new HashMap<>();

    @Override
    public Page<Planet> findAll(Pageable pageable) {
        List<Planet> items = planets.values().stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(items);
    }

    @Override
    public Optional<Planet> findById(String id) {
        return Optional.ofNullable(planets.get(id));
    }

    @Override
    public Planet save(Planet planet) {
        planets.put(planet.getId(), planet);
        return planet;
    }

    @Override
    public void deleteById(String id) throws PlanetNotFoundException {
        if (planets.containsKey(id)) {
            planets.remove(id);
        } else {
            throw new PlanetNotFoundException();
        }
    }

}
