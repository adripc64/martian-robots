package org.adripc64.guidesmiths.challenges.martianrobots.domain.model;

import java.util.Arrays;
import java.util.Objects;

/**
 * The orientation represents towards which cardinal point something is facing: North, East, South or West.
 */
public enum Orientation {

    // The order of the items is very important as they represent a closed circle we can iterate

    /**
     * North (+y)
     */
    N(0, 1),

    /**
     * East (+x)
     */
    E(1, 0),

    /**
     * South (-y)
     */
    S(0, -1),

    /**
     * West (-x)
     */
    W(-1, 0);

    private static final Orientation[] values = values();

    /**
     * The direction represented as a coordinates offset
     */
    private Coords direction;

    Orientation(int dx, int dy) {
        this.direction = new Coords(dx, dy);
    }

    public Coords getDirection() {
        return direction;
    }

    /**
     * Get the resulting orientation if we turn 90 degrees to the left.
     *
     * @return The orientation to the left
     */
    public Orientation left() {
        return values[(values.length + this.ordinal() - 1) % values.length];
    }

    /**
     * Get the resulting orientation if we turn 90 degrees to the right.
     *
     * @return The orientation to the right
     */
    public Orientation right() {
        return values[(values.length + this.ordinal() + 1) % values.length];
    }

    /**
     * Parse an orientation from a String.
     *
     * @param orientation A string representing the orientation
     * @return The orientation
     * @throws NullPointerException if the provided value is null
     * @throws IllegalArgumentException if the provided value is invalid
     */
    public static Orientation parse(String orientation) throws NullPointerException, IllegalArgumentException {

        Objects.requireNonNull(orientation);

        try {
            return Orientation.valueOf(orientation);
        } catch (IllegalArgumentException e) {
            String msg = String.format("The orientation must be one of %s but was: \"%s\"", Arrays.toString(values()), orientation);
            throw new IllegalArgumentException(msg, e);
        }
    }

}
