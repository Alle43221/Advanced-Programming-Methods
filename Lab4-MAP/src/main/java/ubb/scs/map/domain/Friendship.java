package ubb.scs.map.domain;

import java.util.Objects;

/**
 * The Friendship class represents a friendship between two users in a social network.
 * It extends the Entity class, using a Tuple of Longs as its identifier, which consists of
 * the IDs of the two users involved in the friendship.
 */
public class Friendship extends Entity<Tuple<Long, Long>>{
    private final Long userId1;
    private final Long userId2;

    /**
     * Constructs a Friendship object with the given user IDs.
     * The IDs are stored in a way that ensures the smaller ID is always first in the identifier tuple.
     *
     * @param userId1 The ID of the first user.
     * @param userId2 The ID of the second user.
     */
    public Friendship(Long userId1, Long userId2) {
        if(userId1<userId2)
            super.setId(new Tuple<>(userId1, userId2));
        else super.setId(new Tuple<>(userId2, userId1));
        this.userId1 = userId1;
        this.userId2 = userId2;

    }

    /**
     * Returns the ID of the first user in the friendship.
     *
     * @return The userId1.
     */
    public Long getUserId1() {
        return userId1;
    }

    /**
     * Returns the ID of the second user in the friendship.
     *
     * @return The userId2.
     */
    public Long getUserId2() {
        return userId2;
    }

    /**
     * Compares this Friendship object to another object for equality.
     * Two Friendship objects are considered equal if they represent the same pair of users,
     * regardless of the order of user IDs.
     *
     * @param o The object to compare with this Friendship.
     * @return true if the object is equal to this Friendship; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Friendship that)) return false;
        return (getUserId1().equals(that.getUserId1()) &&
                getUserId2().equals(that.getUserId2())) ||
                (getUserId2().equals(that.getUserId1())
                        && getUserId1().equals(that.getUserId2()));
    }

    /**
     * Returns a hash code value for this Friendship.
     * The hash code is computed based on the user IDs of the friendship.
     *
     * @return A hash code value for this Friendship.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getUserId1(), getUserId2());
    }
}
