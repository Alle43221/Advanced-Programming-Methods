package ubb.scs.map.zboruri.repo;


import ubb.scs.map.zboruri.domain.Flight;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightRepo implements Repository<Flight>{
    private String url;
    private String username;
    private String passwd;

    public FlightRepo(String url, String username, String passwd) {
        this.url = url;
        this.username = username;
        this.passwd = passwd;
    }

    @Override
    public List<Flight> getAll() {
        List<Flight> all = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, passwd);
             PreparedStatement statement = connection.prepareStatement("SELECT * from \"flights\"");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id=resultSet.getLong("id");
                String from = resultSet.getString("from_city");
                String to = resultSet.getString("to_city");
                LocalDateTime departure=resultSet.getTimestamp("departure").toLocalDateTime();
                LocalDateTime landing=resultSet.getTimestamp("landing").toLocalDateTime();
                Integer seats=resultSet.getInt("seats");

                Flight fl=new Flight(id,from,to,departure,landing,seats);
                all.add(fl);
            }
            return all;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return all;
    }

    public int countEntries(String fromCity, String toCity, LocalDate departure){
        String query2 = "Select count(*) from flights where from_city = ? and to_city = ? and date(departure) = ?";

        try (Connection connection = DriverManager.getConnection(url, username, passwd);
             PreparedStatement statement = connection.prepareStatement(query2);) {

            statement.setString(1, fromCity);
            statement.setString(2, toCity);
            statement.setObject(3, departure);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Flight> findAllOnPageFilter(int paginaStart, String fromCity, String toCity, LocalDate departure) {

        List<Flight> friends = new ArrayList<>();

        String query2 = "Select * from flights where from_city = ? and to_city = ? and date(departure) = ?";

        query2 += " LIMIT ? OFFSET ? ";
        try (Connection connection = DriverManager.getConnection(url, username, passwd);
             PreparedStatement statement = connection.prepareStatement(query2);) {

            statement.setString(1, fromCity);
            statement.setString(2, toCity);
            statement.setObject(3, departure);
            statement.setInt(4, 5);
            statement.setInt(5, 5*paginaStart);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id=resultSet.getLong("id");
                String from = resultSet.getString("from_city");
                String to = resultSet.getString("to_city");
                LocalDateTime departure1=resultSet.getTimestamp("departure").toLocalDateTime();
                LocalDateTime landing=resultSet.getTimestamp("landing").toLocalDateTime();
                Integer seats=resultSet.getInt("seats");

                Flight fl=new Flight(id,from,to,departure1,landing,seats);
                friends.add(fl);
            }
            return friends;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
