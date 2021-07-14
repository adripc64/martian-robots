package org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.config;

import org.adripc64.guidesmiths.challenges.martianrobots.domain.repository.PlanetRepository;
import org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.InfrastructureConfig;
import org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.mongo.MongoPlanetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = InfrastructureConfig.class, properties = "martian.robots.persistence.implementation=mongo")
class MongoPersistenceConfigurationTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void testCorrectPersistenceImplementationLoaded() {
        MongoPersistenceConfiguration persistenceConfiguration = context.getBean(MongoPersistenceConfiguration.class);
        PlanetRepository planetRepository = context.getBean(PlanetRepository.class);
        assertEquals(MongoPlanetRepository.class, planetRepository.getClass());
    }

}
