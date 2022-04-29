package peaksoft.service;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final Connection connection;

    public UserServiceImpl() throws SQLException {
        connection = new Util().getConnection();
    }

    public void createUsersTable() {
        String query = """
                create table if not exists users(
                id serial primary key,
                name varchar(50) not null,
                last_name varchar(50) not null,
                age smallint not null);
                """;
        try (Statement statement = connection.createStatement();) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String query = "drop table users;";
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
            System.out.println("You've drop table successfully!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "insert into users(name, last_name, age) values(?,?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String query = "delete from users where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        String query = "select * from users;";
        List<User> users = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte(4));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String query = "truncate table users;";
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
            System.out.println("You've clean table successfully!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existsByFirstName(String firstName) {
        String query = "select * from users where name ='" + firstName + "';";
        try (Statement statement = connection.createStatement();){
            if (firstName != null && !(firstName.isEmpty())) {
                statement.executeQuery(query);
            }
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error in database: \n" + e.getMessage());
            return false;
        }
        System.out.println("First name doesn't exist");
        return false;
    }
}