package ubb.scs.map.comenzirestaurant.repository;


import ubb.scs.map.comenzirestaurant.domeniu.Angajat;
import ubb.scs.map.comenzirestaurant.domeniu.Table;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DBStaff implements Repository<Long, Angajat> {

        private static final String URL = "jdbc:postgresql://localhost:5432/comenzi";
        private static final String USER = "postgres";
        private static final String PASSWORD="dabaeuisale";
        private Repository<Long, Table>repoTable;
        public DBStaff() {

        }
        @Override
        public Optional<Angajat> findOne(Long id) {
            return Optional.empty();
        }

        @Override
        public Iterable<Angajat> findAll() {
            Map<Long, Angajat> users = new HashMap<>();
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement statement = connection.prepareStatement("select * from angajati");
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String nume= resultSet.getString("nume");
                    Angajat an=new Angajat(nume);
                    an.setId(id);

                    users.put(an.getId(), an);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return users.values();
        }

        @Override
        public Optional<Angajat> save(Angajat entity) {

            return Optional.empty();
        }

        @Override
        public Optional<Angajat> delete(Long id) {

            return Optional.empty();
        }


    }

