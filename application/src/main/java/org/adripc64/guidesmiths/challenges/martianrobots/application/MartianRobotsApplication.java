package org.adripc64.guidesmiths.challenges.martianrobots.application;

import org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.InfrastructureConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(InfrastructureConfig.class)
public class MartianRobotsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MartianRobotsApplication.class, args);
    }

}
