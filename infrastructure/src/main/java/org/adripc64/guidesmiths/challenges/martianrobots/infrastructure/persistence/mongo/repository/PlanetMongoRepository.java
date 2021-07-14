package org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.mongo.repository;

import org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.mongo.entity.MongoPlanet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetMongoRepository extends MongoRepository<MongoPlanet, String> {



}
