# Martian Robots Domain

The Martian Robots Domain is a Java library that encapsulates the business logic so it can be reused anywhere.

## Overview

The domain model includes:

* **Orientation**: An enum that represents towards which cardinal point something is facing: North, East, South or West. 
* **Coords**: An immutable representation of a point in a 2D space.
* **Position**: An immutable representation of position (coordinates + orientation).
* **Planet**: Represents an existent planet.
* **Robot**: Represents a robot deployed in a planet.

The domain also includes:

* Factories like `PlanetFactory` and `RobotFactory`
* Some domain exceptions like `PlanetNotFoundException`, `PositionOutOfBoundsException` and `RobotLostException`
* A domain repository interface `PlanetRepository`
* A domain service `MartianRobotsService`

The library includes more than 40 unit tests.

## Build

Build the library with Gradle:

```
./gradlew :domain:build
```

The built library is located in:

```
./domain/build/libs/
```

## Download

You can download the released artifacts from GitHub Packages:

https://github.com/adripc64/martian-robots/packages/898535
