package ubb.scs.map.domain;

import java.util.Objects;

/**
 * The Tuple class represents a generic container for a pair of values (X and Y).
 * It provides a way to group two related objects together, enabling easy handling of pairs
 * in a type-safe manner.
 *
 * @param <X> The type of the first element in the tuple.
 * @param <Y> The type of the second element in the tuple.
 */
public class Tuple<X, Y> {
    private final X x;
    private final Y y;

    /**
     * Constructs a Tuple object with the specified values.
     *
     * @param x The first element of the tuple.
     * @param y The second element of the tuple.
     */
    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the first element of the tuple.
     *
     * @return The first element (x) of the tuple.
     */
    public X getX() {
        return x;
    }

    /**
     * Returns the second element of the tuple.
     *
     * @return The second element (y) of the tuple.
     */
    public Y getY() {
        return y;
    }

    /**
     * Compares this Tuple object to another object for equality.
     * Two Tuple objects are considered equal if they contain the same pair of values,
     * regardless of the order of the elements.
     *
     * @param o The object to compare with this Tuple.
     * @return true if the object is equal to this Tuple; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple that)) return false;
        return (x.equals(that.getX()) &&
                y.equals(that.getY())) ||
                (y.equals(that.getX()) &&
                        x.equals(that.getY()));
    }

    /**
     * Returns a hash code value for this Tuple.
     * The hash code is computed based on the elements of the tuple.
     *
     * @return A hash code value for this Tuple.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
