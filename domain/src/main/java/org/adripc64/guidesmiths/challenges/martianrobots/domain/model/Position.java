package org.adripc64.guidesmiths.challenges.martianrobots.domain.model;

import lombok.Data;

import java.util.Objects;

/**
 * An immutable representation of position (coordinates + orientation).
 */
@Data
public class Position {

    private static final String DELIMITER = " ";

    /**
     * The position coordinates
     */
    private final Coords coords;

    /**
     * The position orientation
     */
    private final Orientation orientation;

    public Position(Coords coords, Orientation orientation) {
        this.coords = Objects.requireNonNull(coords);
        this.orientation = Objects.requireNonNull(orientation);
    }

    public Position(int x, int y, Orientation orientation) {
        this.coords = new Coords(x, y);
        this.orientation = Objects.requireNonNull(orientation);
    }

    /**
     * Get the position after turning left.
     *
     * @return The resulting position
     */
    public Position turnLeft() {
        return new Position(coords, orientation.left());
    }

    /**
     * Get the position after turning right.
     *
     * @return The resulting position
     */
    public Position turnRight() {
        return new Position(coords, orientation.right());
    }

    /**
     * Get the position after moving forward.
     *
     * @return The resulting position
     */
    public Position forward() {
        return new Position(coords.add(orientation.getDirection()), orientation);
    }

    /**
     * Parse a position from a String.
     *
     * The position must be in the format: "X Y Orientation" (eg: 1 2 N)
     *
     * @param position A string representing the position
     * @return The position
     * @throws NullPointerException if the provided value is null
     * @throws IllegalArgumentException if the provided value is invalid
     */
    public static Position parse(String position) {

        Objects.requireNonNull(position);

        if (position.isEmpty()) {
            throw new IllegalArgumentException("Empty position string");
        }

        String[] items = position.split(DELIMITER);

        if (items.length != 3) {
            throw new IllegalArgumentException("Position string must contain 3 elements");
        }

        Coords coords = Coords.parse(items[0] + " " + items[1]);
        Orientation orientation = Orientation.parse(items[2]);

        return new Position(coords, orientation);
    }

}
