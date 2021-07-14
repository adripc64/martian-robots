package org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.jpa.repository;

import org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.jpa.entity.JpaPlanet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetJpaRepository extends JpaRepository<JpaPlanet, String> {



}
