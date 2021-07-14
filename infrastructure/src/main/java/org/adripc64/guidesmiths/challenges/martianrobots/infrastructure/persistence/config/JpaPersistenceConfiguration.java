package org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ConditionalOnProperty(value = "martian.robots.persistence.implementation", havingValue = "jpa")
@ComponentScan(basePackages = "org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.jpa")
@EnableJpaRepositories(basePackages = "org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.jpa.repository")
@EntityScan(basePackages = "org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.jpa.entity")
@Import({
        org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class,
        org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration.class,
        org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration.class
})
@EnableAutoConfiguration
public class JpaPersistenceConfiguration {

}
