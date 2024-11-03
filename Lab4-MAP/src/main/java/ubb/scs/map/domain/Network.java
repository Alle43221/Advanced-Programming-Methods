package ubb.scs.map.domain;

import ubb.scs.map.repository.Repository;

/**
 * The Network class represents a social network that manages users and their friendships.
 * It provides access to user and friendship repositories, which store and manage the data
 * related to users and their connections.
 */
public class Network {
    private Repository<Long, User> userRepo;
    private Repository<Tuple<Long, Long>, Friendship> friendshipRepo;

    /**
     * Returns the repository for User entities.
     *
     * @return The user repository.
     */
    public Repository<Long, User> getUserRepo() {
        return userRepo;
    }

    /**
     * Sets the repository for User entities.
     *
     * @param userRepo The repository to be set for managing users.
     */
    public void setUserRepo(Repository<Long, User> userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Returns the repository for Friendship entities.
     *
     * @return The friendship repository.
     */
    public Repository<Tuple<Long, Long>, Friendship> getFriendshipRepo() {
        return friendshipRepo;
    }

    /**
     * Sets the repository for Friendship entities.
     *
     * @param friendshipRepo The repository to be set for managing friendships.
     */
    public void setFriendshipRepo(Repository<Tuple<Long, Long>, Friendship> friendshipRepo) {
        this.friendshipRepo = friendshipRepo;
    }

    /**
     * Constructs a Network object with the specified user and friendship repositories.
     *
     * @param userRepo        The repository for managing User entities.
     * @param friendshipRepo  The repository for managing Friendship entities.
     */
    public Network(Repository<Long, User> userRepo, Repository<Tuple<Long, Long>, Friendship> friendshipRepo) {
        this.userRepo = userRepo;
        this.friendshipRepo = friendshipRepo;
    }
}
