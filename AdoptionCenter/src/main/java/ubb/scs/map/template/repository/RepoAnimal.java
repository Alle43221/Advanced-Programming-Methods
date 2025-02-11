package ubb.scs.map.template.repository;

import ubb.scs.map.template.domain.Animal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepoAnimal implements Repository<Integer, Animal> {

    private String url;
    private String usernamee;
    private String passwd;

    public RepoAnimal(String url, String usernamee, String passwd) {
        this.url = url;
        this.usernamee = usernamee;
        this.passwd = passwd;
    }


    public Iterable<Animal> findAll() {
        List<Animal> all = new ArrayList<>();
        String query = "SELECT id, name, centerId, type FROM \"animal\"";

        try (Connection connection = DriverManager.getConnection(url, usernamee, passwd);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int centerId = resultSet.getInt("centerId");
                String type = resultSet.getString("type");

                Animal animal = new Animal(id, name, centerId, Animal.Type.valueOf(type)); // Assuming Type is an Enum
                all.add(animal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return all;
    }

    public Iterable<Animal> findAllOfType(Animal.Type type) {
        List<Animal> all = new ArrayList<>();
        String query = "SELECT id, name, centerId, type FROM \"animal\" WHERE type = ?";

        try (Connection connection = DriverManager.getConnection(url, usernamee, passwd);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, type.name()); // Convert Enum to String for SQL query
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int centerId = resultSet.getInt("centerId");

                Animal animal = new Animal(id, name, centerId, type);
                all.add(animal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return all;
    }

    public void update(Animal animal) {
        String query = "UPDATE \"animal\" SET name = ?, centerId = ?, type = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, usernamee, passwd);
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set the parameters for the query
            statement.setString(1, animal.getName());          // Set name
            statement.setInt(2, animal.getCenterId());        // Set centerId
            statement.setString(3,animal.getType());  // Set type (Enum to String)
            statement.setInt(4, animal.getId());              // Set the id for the WHERE clause

            // Execute the update query
            int rowsAffected = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Optional<Animal> findOne(int id) {
        Animal animal = null;
        String query = "SELECT id, name, centerId, type FROM \"animal\" WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, usernamee, passwd);
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set the id parameter for the query
            statement.setInt(1, id);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // If an animal with the given id is found
            if (resultSet.next()) {
                int animalId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int centerId = resultSet.getInt("centerId");
                String type = resultSet.getString("type");

                // Create the Animal object
                animal = new Animal(animalId, name, centerId, Animal.Type.valueOf(type)); // Assuming Type is an Enum
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return the Animal wrapped in an Optional
        return Optional.ofNullable(animal);
    }

}
