package org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ConditionalOnProperty(value = "martian.robots.persistence.implementation", havingValue = "mongo")
@ComponentScan(basePackages = "org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.mongo")
@EnableMongoRepositories(basePackages = "org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.mongo.repository")
@Import({
        org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration.class,
        org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration.class
})
@EnableAutoConfiguration
public class MongoPersistenceConfiguration {

}
