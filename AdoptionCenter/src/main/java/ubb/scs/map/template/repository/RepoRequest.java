package ubb.scs.map.template.repository;

import ubb.scs.map.template.domain.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepoRequest implements Repository<Integer, Request>{
    private String url;
    private String usernamee;
    private String passwd;

    public RepoRequest(String url, String usernamee, String passwd) {
        this.url = url;
        this.usernamee = usernamee;
        this.passwd = passwd;
    }


    @Override
    public Iterable<Request> findAll() {
        List<Request> all = new ArrayList<>();
        String query = "SELECT id, id_sursa, id_animal FROM \"requests\"";

        try (Connection connection = DriverManager.getConnection(url, usernamee, passwd);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");  // Get the id of the request
                int idSursa = resultSet.getInt("id_sursa");  // Get id_sursa
                int idAnimal = resultSet.getInt("id_animal");  // Get id_animal

                // Create a Request object with the retrieved data
                Request request = new Request(id, idSursa, idAnimal);  // Set null for idDestinatie since it is not used
                all.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return all;
    }

    public Request save(Request request) {
        String query = "INSERT INTO \"requests\" (id_sursa, id_animal) VALUES (?, ?) RETURNING id";

        try (Connection connection = DriverManager.getConnection(url, usernamee, passwd);
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set parameters for the query
            statement.setInt(1, request.getIdSursa());  // Set id_sursa
            statement.setInt(2, request.getIdAnimal());  // Set id_animal

            // Execute the insert query and retrieve the generated id
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Get the generated id
                    int generatedId = resultSet.getInt("id");

                    // Set the generated id in the Request object
                    request.setId(generatedId);

                    System.out.println("Request saved successfully with ID: " + generatedId);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return the saved entity (Request object with the generated ID)
        return request;
    }


    public void delete(int requestId) {
        String query = "DELETE FROM \"requests\" WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, usernamee, passwd);
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set the ID parameter for the query
            statement.setInt(1, requestId);

            // Execute the delete query
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Request with ID " + requestId + " deleted successfully!");
            } else {
                System.out.println("No request found with ID " + requestId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
