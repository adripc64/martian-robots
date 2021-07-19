# Martian Robots Application

The Martian Robots Application is a Spring Boot application exposing a REST API to experiment with different simulations.

## Overview

The REST API is documented using OpenApi. The documentation is served by SwaggerUI when the application is running:

```
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
```

## Usage

### Gradle

The application can be started with Gradle:

```
./gradlew :application:bootRun
```

### Docker

Docker images are published in GitHub Packages:

https://github.com/adripc64/martian-robots/pkgs/container/martian-robots-application

You can run the application with Docker like:

```
docker run -d -p 8080:8080 ghcr.io/adripc64/martian-robots-application:1.0.0
```

### Docker Compose

A couple of docker-compose files are provided to ease running the application with MySQL or Mongo.

**MySQL**

```
docker-compose -f docker-compose-mysql.yaml up -d
```

You can inspect the MySQL database with Adminer:

```
http://localhost:8081/?server=mysql&username=user&db=martian-robots
# Pass: secret
```

**Mongo**

```
docker-compose -f docker-compose-mongo.yaml up -d
```

You can inspect the MongoDB instance with Mongo Express:

```
http://localhost:8081/
# Note that the martian-robots database is created on first write
```

## Download

You can download the released artifacts from GitHub Packages:

https://github.com/adripc64/martian-robots/packages/898572
