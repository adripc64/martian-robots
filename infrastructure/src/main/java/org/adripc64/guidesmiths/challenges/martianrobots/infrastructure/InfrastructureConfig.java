package org.adripc64.guidesmiths.challenges.martianrobots.infrastructure;

import org.adripc64.guidesmiths.challenges.martianrobots.domain.repository.PlanetRepository;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.service.MartianRobotsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.config")
public class InfrastructureConfig {

    @Bean
    public MartianRobotsService planetsService(PlanetRepository planetRepository) {
        return new MartianRobotsService(planetRepository);
    }

}
