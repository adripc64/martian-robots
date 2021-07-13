package org.adripc64.guidesmiths.challenges.martianrobots.domain.model;

import lombok.Data;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.exception.PositionOutOfBoundsException;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.factory.RobotFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Represents an existent planet.
 */
@Data
public class Planet {

    /**
     * The planet identifier
     */
    private final String id;

    /**
     * The planet width
     */
    private final int width;

    /**
     * The planet height
     */
    private final int height;

    /**
     * The scents left in the planet
     */
    private final boolean[][] scents;

    /**
     * The robots deployed in the planet
     */
    private final List<Robot> robots = new LinkedList<>();

    /**
     * The robot factory, used to deploy robots in the planet
     */
    private final RobotFactory robotFactory = new RobotFactory(this);

    public Planet(String id, int width, int height) {
        this.id = Objects.requireNonNull(id);
        this.width = width;
        this.height = height;
        this.scents = new boolean[height][width];
    }

    /**
     * Check if the given position is out of bounds.
     *
     * @param x Position on the X axis
     * @param y Position on the Y axis
     * @return true if position is out of bounds, false otherwise
     */
    public boolean isPositionOutOfBounds(int x, int y) {
        return (x < 0 || x >= width || y < 0 || y >= height);
    }

    /**
     * Check if the given position is out of bounds.
     *
     * @param position The position
     * @return true if position is out of bounds, false otherwise
     */
    public boolean isPositionOutOfBounds(Position position) {
        Coords coords = position.getCoords();
        return isPositionOutOfBounds(coords.getX(), coords.getY());
    }

    /**
     * Check if there is a scent in the given position.
     *
     * @param x Position on the X axis
     * @param y Position on the Y axis
     * @return true if there is a scent in the given position, false otherwise
     */
    public boolean hasScent(int x, int y) {
        if (isPositionOutOfBounds(x, y)) {
            return false;
        } else {
            return scents[y][x];
        }
    }

    /**
     * Check if there is a scent in the given position.
     *
     * @param position The position
     * @return true if there is a scent in the given position, false otherwise
     */
    public boolean hasScent(Position position) {
        Coords coord = position.getCoords();
        return hasScent(coord.getX(), coord.getY());
    }

    /**
     * Add a scent in the given position.
     *
     * @param x Position on the X axis
     * @param y Position on the Y axis
     * @throws PositionOutOfBoundsException if the given position is out of bounds
     */
    public void addScent(int x, int y) {
        if (isPositionOutOfBounds(x, y)) {
            throw new PositionOutOfBoundsException();
        } else {
            scents[x][y] = true;
        }
    }

    /**
     * Add a scent in the given position.
     *
     * @param position The position
     * @throws PositionOutOfBoundsException if the given position is out of bounds
     */
    public void addScent(Position position) {
        Coords coord = position.getCoords();
        addScent(coord.getX(), coord.getY());
    }

    /**
     * Get the scents in the planet as a list of coordinates.
     *
     * @return A list of coordinates
     */
    public List<Coords> getScentsAsList() {
        LinkedList<Coords> coords = new LinkedList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (hasScent(x, y)) {
                    coords.add(new Coords(x, y));
                }
            }
        }
        return coords;
    }

}
