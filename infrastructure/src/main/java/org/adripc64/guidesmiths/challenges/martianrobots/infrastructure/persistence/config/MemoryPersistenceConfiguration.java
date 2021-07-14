package org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "martian.robots.persistence.implementation", havingValue = "memory")
@ComponentScan(basePackages = "org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.memory")
public class MemoryPersistenceConfiguration {

}
