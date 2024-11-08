package ubb.scs.map.repository.database;

import ubb.scs.map.domain.User;
import ubb.scs.map.domain.validators.Validator;
import ubb.scs.map.repository.RepoException;
import ubb.scs.map.repository.Repository;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * UserDBRepository is a repository for managing User entities in a database.
 * It provides methods to perform CRUD (Create, Read, Update, Delete) operations on User records.
 */
public class UserDBRepository implements Repository<Long, User> {
    private final String url;
    private final String username;
    private final String password;
    private final Validator<User> validator;

    /**
     * Constructs a UserDBRepository with the specified database connection details and user validator.
     *
     * @param url the database connection URL
     * @param username the username for database authentication
     * @param password the password for database authentication
     * @param validator the validator used to validate User entities before saving
     */
    public UserDBRepository(String url, String username, String password, Validator<User> validator) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.validator = validator;
    }

    /**
     * Finds a User by its ID in the database.
     *
     * @param id the ID of the user to find
     * @return an Optional containing the User if found, or an empty Optional if not found
     */
    @Override
    public Optional<User> findOne(Long id) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from users WHERE id = ?")
             ) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next())
            {
                return Optional.empty();
            }
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");

            User utilizator = new User(firstName, lastName);
            utilizator.setId(id);
            return Optional.of(utilizator);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RepoException("Could not fetch user");
        }
    }

    /**
     * Retrieves all Users from the database.
     *
     * @return an Iterable collection of all Users
     */
    @Override
    public Iterable<User> findAll() {
        Set<User> users = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from users");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                User utilizator = new User(firstName, lastName);
                utilizator.setId(id);
                users.add(utilizator);
            }
            return users;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
//            throw new RepoException("Could not fetch users");
        }
        return null;
    }

    /**
     * Saves a User entity to the database.
     *
     * @param entity the User entity to save
     * @return an Optional containing the User if the save operation was unsuccessful (e.g., user ID already exists),
     *         or an empty Optional if the save was successful
     */
    @Override
    public Optional<User> save(User entity) {
        int rez;
        try (
                Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO users (id, first_name, last_name) VALUES (?, ?, ?)")
        ) {
            validator.validate(entity); //throws validation error
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getFirstName());
            statement.setString(3, entity.getLastName());
            rez = statement.executeUpdate();
        } catch (SQLException e) {
//            System.out.println(e.getMessage());
            throw new RepoException("Could not save user - id must be unique");
        }
        if (rez > 0)
            return Optional.empty();
        else
            return Optional.of(entity);
    }


    /**
     * Deletes a User by its ID from the database.
     *
     * @param id the ID of the User to delete
     * @return an Optional containing the deleted User if successful, or an empty Optional if the user was not found
     */
    @Override
    public Optional<User> delete(Long id) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement result = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
             PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?")
        ) {
            result.setLong(1, id);
            statement.setLong(1, id);
            ResultSet resultSet = result.executeQuery();
            resultSet.next();
            String lastName = resultSet.getString("last_name");
            String firstName = resultSet.getString("first_name");

            User utilizator = new User(firstName, lastName);
            utilizator.setId(id);
            statement.executeUpdate();
            return Optional.of(utilizator);

        } catch (SQLException e) {
//            System.out.println(e.getMessage());
            throw new RepoException("Could not delete user - non existent user");
        }
    }

    /**
     * Updates a User entity in the database.
     *
     * @param entity the User entity with updated information
     * @return an Optional containing the updated User if successful, or an empty Optional if the user was not found
     */
    @Override
    public Optional<User> update(User entity) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("UPDATE users SET last_name=?, first_name=? WHERE id = ?")
        ) {
            statement.setString(1, entity.getLastName());
            statement.setString(2, entity.getFirstName());
            statement.setLong(3, entity.getId());
            int rez=statement.executeUpdate();
            if(rez<1){
                return Optional.empty();
            }
            return Optional.of(entity);

        } catch (SQLException e) {
//            System.out.println(e.getMessage());
            throw new RepoException("Could not update user - non existent user");
        }
    }
}

