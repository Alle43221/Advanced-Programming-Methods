package ubb.scs.map.template.repository;

import ubb.scs.map.template.domain.AdoptionCenter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepoCenter implements Repository<Integer, AdoptionCenter> {
    private String url;
    private String usernamee;
    private String passwd;

    public RepoCenter(String url, String usernamee, String passwd) {
        this.url = url;
        this.usernamee = usernamee;
        this.passwd = passwd;
    }


    public Iterable<AdoptionCenter> findAll() {
        List<AdoptionCenter> all = new ArrayList<>();
        String query = "SELECT id, name, location, capacity FROM \"adoptioncenter\"";

        try (Connection connection = DriverManager.getConnection(url, usernamee, passwd);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String location = resultSet.getString("location");
                int capacity = resultSet.getInt("capacity");

                AdoptionCenter center = new AdoptionCenter(id, name, location, capacity);
                all.add(center);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return all;
    }

    public Optional<AdoptionCenter> findOne(int centerId) {
        String query = "SELECT id, name, location, capacity FROM \"adoptioncenter\" WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, usernamee, passwd);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, centerId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String location = resultSet.getString("location");
                int capacity = resultSet.getInt("capacity");

                AdoptionCenter center = new AdoptionCenter(id, name, location, capacity);
                return Optional.of(center);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }


    public Iterable<AdoptionCenter> findAllFromLocation(String location) {
        List<AdoptionCenter> all = new ArrayList<>();

        // Modify the query to filter by the location
        String query = "SELECT id, name, location, capacity FROM \"adoptioncenter\" WHERE location = ?";

        try (Connection connection = DriverManager.getConnection(url, usernamee, passwd);
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set the location parameter in the query
            statement.setString(1, location);

            // Execute the query and get the result
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String locationFromDB = resultSet.getString("location");
                    int capacity = resultSet.getInt("capacity");

                    // Create an AdoptionCenter object from the retrieved data
                    AdoptionCenter center = new AdoptionCenter(id, name, locationFromDB, capacity);
                    all.add(center);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return all;
    }

}
