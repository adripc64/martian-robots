package org.adripc64.guidesmiths.challenges.martianrobots.domain.model;

import lombok.Data;

import java.util.Objects;

/**
 * An immutable representation of a point in a 2D space.
 */
@Data
public class Coords {

    private static final String DELIMITER = " ";

    /**
     * Position on the X axis
     */
    private final int x;

    /**
     * Position on the Y axis
     */
    private final int y;

    /**
     * Returns the resulting coords of adding the given coords to this coords.
     *
     * @param offset The coords to add
     * @return The resulting coords
     */
    public Coords add(Coords offset) {
        return new Coords(this.x + offset.x, this.y + offset.y);
    }

    /**
     * Parse the coordinates from a String.
     *
     * @param coords A string representing the coordinates
     * @return The coordinates
     * @throws NullPointerException if the provided value is null
     * @throws IllegalArgumentException if the provided value is invalid
     */
    public static Coords parse(String coords) {

        Objects.requireNonNull(coords);

        if (coords.isEmpty()) {
            throw new IllegalArgumentException("Empty coordinates string");
        }

        String[] items = coords.split(DELIMITER);

        if (items.length != 2) {
            throw new IllegalArgumentException("Coordinates string must contain 2 elements");
        }

        int intX, intY;

        try {
            intX = Integer.parseInt(items[0]);
        } catch (NumberFormatException e) {
            String msg = String.format("The x-coordinate must be a number but was: \"%s\"", items[0]);
            throw new IllegalArgumentException(msg, e);
        }

        try {
            intY = Integer.parseInt(items[1]);
        } catch (NumberFormatException e) {
            String msg = String.format("The y-coordinate must be a number but was: \"%s\"", items[1]);
            throw new IllegalArgumentException(msg, e);
        }

        return new Coords(intX, intY);
    }

}
