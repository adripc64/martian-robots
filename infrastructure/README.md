# Martian Robots Infrastructure

The Martian Robots Infrastructure is a Spring Boot library providing implementations for accessing external components (eg. database).

## Overview

The infrastructure module must be imported like:

```
@SpringBootApplication
@Import(InfrastructureConfig.class)
public class MartianRobotsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MartianRobotsApplication.class, args);
    }

}
```

It provides a Bean of `MartianRobotsService` with the desired `PlanetRepository` implementation injected.

The persistence implementation is selected conditionally.

## Persistence

3 persistence implementations are supported:

* memory: data is stored in-memory (**discouraged for production**)
* jpa: data is stored in a relational database using JPA
* mongo: data is stored in a MongoDB instance

By default, the `memory` implementation is used:

```
# Persistence implementation: memory, jpa, mongo
martian.robots.persistence.implementation=memory
```

Another implementation can be selected using the following environment variable:

```
MARTIAN_ROBOTS_PERSISTENCE_IMPLEMENTATION=jpa
```

### JPA

When using the `jpa` implementation you may need extra configuration.

By default, an embedded H2 database is used:

```
# JPA config
spring.datasource.url=jdbc:h2:mem:db
``` 

But you can override it to use a MySQL database, see:

https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.sql.datasource.configuration

You can inspect the H2 database at:

```
http://localhost:8080/h2-console
```

### Mongo

When using the `mongo` implementation you may need extra configuration.

By default, the following configuration is used:

```
# Mongo config
spring.data.mongodb.uri=mongodb://user:secret@localhost:27017/test?authSource=admin
``` 

But you can override it, see:

https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.nosql.mongodb.connecting
