package ubb.scs.map.repository.database;

import ubb.scs.map.domain.Friendship;
import ubb.scs.map.domain.Tuple;
import ubb.scs.map.domain.validators.Validator;
import ubb.scs.map.repository.RepoException;
import ubb.scs.map.repository.Repository;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * FriendshipDBRepository is a repository for managing Friendship entities in a database.
 * This class provides CRUD (Create, Read, Update, Delete) operations for Friendships.
 */
public class FriendshipDBRepository implements Repository<Tuple<Long, Long>, Friendship> {
    private final String url;
    private final String username;
    private final String password;
    private final Validator<Friendship> validator;

    /**
     * Constructs a FriendshipDBRepository with the specified database connection details and validator.
     *
     * @param url the URL for the database connection
     * @param username the username for database authentication
     * @param password the password for database authentication
     * @param validator the validator used to validate Friendship entities
     */
    public FriendshipDBRepository(String url, String username, String password, Validator<Friendship> validator) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.validator = validator;
    }

    /**
     * Finds a Friendship by the IDs of two users in the database.
     *
     * @param a a Tuple containing the IDs of the two users in the friendship
     * @return an Optional containing the Friendship if found, or an empty Optional if not found
     */
    @Override
    public Optional<Friendship> findOne(Tuple<Long, Long> a) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from friendships WHERE id1 = ? and id2 = ?")
        ) {

            statement.setLong(1, a.getX());
            statement.setLong(2, a.getY());

            ResultSet resultSet=statement.executeQuery();
            if (!resultSet.next())
            {
                return Optional.empty();
            }

            resultSet.getLong("id1");
            resultSet.getLong("id2");
            Friendship prietenie = new Friendship(a.getX(), a.getY());
            return Optional.of(prietenie);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

            throw new RepoException("Could not fetch friendship");
        }
    }


    /**
     * Retrieves all friendships from the database.
     *
     * @return an Iterable collection of all friendships in the database
     */
    @Override
    public Iterable<Friendship> findAll() {
        Set<Friendship> friendships = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from friendships");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id1 = resultSet.getLong("id1");
                Long id2 = resultSet.getLong("id2");

                Friendship friendship = new Friendship(id1, id2);
                friendships.add(friendship);
            }
            return friendships;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
//            throw new RepoException("Could not fetch friendships");
        }
        return null;
    }

    /**
     * Saves a Friendship entity to the database.
     *
     * @param entity the Friendship entity to save
     * @return an Optional containing the Friendship if the save was unsuccessful, or an empty Optional if successful
     */
    @Override
    public Optional<Friendship> save(Friendship entity) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO friendships (id1, id2) VALUES (?, ?)")
        ) {
            validator.validate(entity); //throws ValidationError
            statement.setLong(1, entity.getUserId1());
            statement.setLong(2, entity.getUserId2());
            int rez = statement.executeUpdate();
            if (rez > 0)
                return Optional.empty();
            else
                return Optional.of(entity);
        } catch (SQLException e) {
            throw new RepoException("Could not save friendship");
        }
    }

    /**
     * Deletes a Friendship by the IDs of the two users involved.
     *
     * @param a a Tuple containing the IDs of the two users in the friendship
     * @return an Optional containing the deleted Friendship if successful, or an empty Optional if not found
     */
    @Override
    public Optional<Friendship> delete(Tuple<Long, Long> a) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement result = connection.prepareStatement("SELECT * FROM friendships WHERE id1 = ? and id2=?");
             PreparedStatement statement = connection.prepareStatement("DELETE FROM friendships WHERE id1 = ? and id2=?")
        ) {
            result.setLong(1, a.getX());
            result.setLong(2, a.getY());
            statement.setLong(1, a.getX());
            statement.setLong(2, a.getY());
            ResultSet resultSet = result.executeQuery();
            resultSet.next();
            resultSet.getLong("id1");
            resultSet.getLong("id2");

            Friendship prietenie = new Friendship(a.getX(), a.getY());
            statement.executeUpdate();
            return Optional.of(prietenie);

        } catch (SQLException e) {
//            System.out.println(e.getMessage());
            throw new RepoException("Could not delete friendship - non existent friendship");
        }
    }

    /**
     * Updates a Friendship entity in the database.
     *
     * @param entity the Friendship entity with updated information
     * @return an Optional containing the updated Friendship if successful, or an empty Optional if not found
     */
    @Override
    public Optional<Friendship> update(Friendship entity) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("Select * FROM friendships WHERE id1=? AND id2=?")
        ) {
            statement.setLong(1, entity.getUserId1());
            statement.setLong(2, entity.getUserId2());
            int rez=statement.executeUpdate();
            if(rez<1){
                return Optional.empty();
            }
            return Optional.of(entity);

        } catch (SQLException e) {
//            System.out.println(e.getMessage());
            throw new RepoException("Could not update friendship - non existent friendship");
        }
    }


}

