package ubb.scs.map.domain;

import java.util.Objects;

/**
 * The User class represents a user in the system.
 * It extends the Entity class with a Long identifier and encapsulates
 * the user's personal information, including their first and last names.
 */
public class User extends Entity<Long>{
    private String firstName;
    private String lastName;

    /**
     * Constructs a User object with the specified first and last names.
     *
     * @param firstName The first name of the user.
     * @param lastName  The last name of the user.
     */
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    /**
     * Returns the first name of the user.
     *
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     *
     * @param firstName The new first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the user.
     *
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     *
     * @param lastName The new last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    /**
     * Returns a string representation of the User object.
     * The string includes the first and last names of the user.
     *
     * @return A string representation of the user.
     */
    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' ;}

    /**
     * Compares this User object to another object for equality.
     * Two User objects are considered equal if they have the same first and last names.
     *
     * @param o The object to compare with this User.
     * @return true if the object is equal to this User; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User that)) return false;
        return firstName.equals(that.getFirstName()) &&
                lastName.equals(that.getLastName());
    }

    /**
     * Returns a hash code value for this User.
     * The hash code is computed based on the first and last names.
     *
     * @return A hash code value for this User.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName());
    }
}