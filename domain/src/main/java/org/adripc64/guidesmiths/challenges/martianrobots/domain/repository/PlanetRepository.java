package org.adripc64.guidesmiths.challenges.martianrobots.domain.repository;

import org.adripc64.guidesmiths.challenges.martianrobots.domain.exception.PlanetNotFoundException;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Planet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PlanetRepository {

    Page<Planet> findAll(Pageable pageable);

    Optional<Planet> findById(String id);

    Planet save(Planet planet);

    void deleteById(String id) throws PlanetNotFoundException;

}
