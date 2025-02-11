package ubb.scs.map.comenzirestaurant.repository;

import ubb.scs.map.comenzirestaurant.domeniu.Order;
import ubb.scs.map.comenzirestaurant.domeniu.Table;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class DBComanda implements Repository<Long, Order> {

    private static final String URL = "jdbc:postgresql://localhost:5432/comenzi";
    private static final String USER = "postgres";
    private static final String PASSWORD = "dabaeuisale";
    private Repository<Long, Table> repoTable;

    public DBComanda(Repository<Long, Table> repoTable) {
        this.repoTable = repoTable;
    }

    @Override
    public Optional<Order> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public Iterable<Order> findAll() {
        Map<Long, Order> comenzi = new HashMap<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("select * from comanda");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long masa = resultSet.getLong("masa");
                LocalDateTime date = resultSet.getTimestamp("date").toLocalDateTime();
                String status = resultSet.getString("status");

                List<Long> items = new ArrayList<>();
                String query = "SELECT * FROM comanda_item WHERE id_comanda = ?";
                try (PreparedStatement statement1 = connection.prepareStatement(query)) {
                    statement1.setLong(1, id);
                    ResultSet resultSet1 = statement1.executeQuery();
                    while (resultSet1.next()) {
                        Long id_item = resultSet1.getLong("id_item");
                        items.add(id_item);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                Order.Status st = Order.Status.valueOf(status);
                Order comanda = new Order(masa, items, date, st);
                comanda.setId(id);
                comenzi.put(comanda.getId(), comanda);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return comenzi.values();
    }

    @Override
    public Optional<Order> save(Order entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Persoana nu poate fi nula");
        }
        String query = "INSERT INTO comanda(masa, date, status) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, entity.getTableId());
            statement.setTimestamp(2, Timestamp.valueOf(entity.getDate()));
            statement.setString(3, entity.getStatus().toString());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long generatedId = generatedKeys.getLong(1);
                        entity.setId(generatedId);

                        for (Long itemId : entity.getMenuItems()) {
                            String query2 = "INSERT INTO comanda_item(id_comanda, id_item) VALUES (?, ?)";
                            try (PreparedStatement statement2 = connection.prepareStatement(query2)) {
                                statement2.setLong(1, generatedId);
                                statement2.setLong(2, itemId);

                                statement2.executeUpdate();
                            }
                        }
                        return Optional.of(entity);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saving order: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Order> delete(Long id) {
        return Optional.empty();
    }
}