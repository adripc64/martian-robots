package org.adripc64.guidesmiths.challenges.martianrobots.domain.factory;

import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Coords;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Planet;

import java.util.UUID;

public class PlanetFactory {

    /**
     * Create a planet of the specified size.
     *
     * @param width Planet width
     * @param height Planet height
     * @return The planet
     * @throws IllegalArgumentException if width or height is <= 0
     */
    public static Planet createPlanetOfSize(int width, int height) {

        if (width <= 0 || height <= 0) {
            String msg = String.format("Invalid planet size: width=%d, height=%d. Width and height must be > 0.", width, height);
            throw new IllegalArgumentException(msg);
        }

        String id = UUID.randomUUID().toString();
        return new Planet(id, width, height);
    }

    /**
     * Create a planet with the specified max coordinates.
     *
     * @param maxX The max x-coordinate
     * @param maxY The max y-coordinate
     * @return The planet
     * @throws IllegalArgumentException if any of the max coordinates is < 0
     */
    public static Planet createPlanetWithMaxCoordinates(int maxX, int maxY) {

        if (maxX < 0 || maxY < 0) {
            String msg = String.format("Invalid planet max coordinates: maxX=%d, maxY=%d. Max coordinates must be >= 0.", maxX, maxY);
            throw new IllegalArgumentException(msg);
        }

        return createPlanetOfSize(maxX+1, maxY+1);
    }

    /**
     * Create a planet with the specified max coordinates.
     *
     * @param maxCoords The max coordinates
     * @return The planet
     * @throws IllegalArgumentException if any of the max coordinates is < 0
     */
    public static Planet createPlanetWithMaxCoordinates(Coords maxCoords) {
        return createPlanetWithMaxCoordinates(maxCoords.getX(), maxCoords.getY());
    }

}
