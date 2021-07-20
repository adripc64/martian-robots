# Martian Robots

This repository is my solution to the [Martian Robots Java Code Challenge](https://github.com/guidesmiths/interview-code-challenges/blob/master/java/martian-robots/instructions.md)

## Status
![example workflow](https://github.com/adripc64/martian-robots/actions/workflows/build.yaml/badge.svg)

## Overview

The root project is a multi-project Gradle build with the following modules:

- [Domain](domain/README.md)
- [Simulator](simulator/README.md)
- [Infrastructure](infrastructure/README.md)
- [Application](application/README.md)

Please, see each module README for more details.
 
## FEATURES

The following list highlights some key features of the solution:

* The solution architecture is designed following Domain-Driven Design concepts
* Unit tests with Junit and Mockito
* Support for different persistence implementations: in-memory, JPA (H2, MySQL) and MongoDB
* REST API documented with OpenApi and served with SwaggerUI
* Docker image created with buildpacks (integrated in Spring Boot Gradle Plugin)
* Docker Compose files are provided for running the application + database
* GitHub Actions has been used for CI and CD
* GitHub Packages has been used to publish artifacts and Docker images
