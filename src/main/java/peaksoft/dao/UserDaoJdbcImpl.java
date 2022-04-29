package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    UserService userService = new UserServiceImpl();


    public UserDaoJdbcImpl() throws SQLException {
    }

    public void createUsersTable() {
        userService.createUsersTable();
    }

    public void dropUsersTable() {
        userService.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userService.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userService.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public void cleanUsersTable() {
        userService.cleanUsersTable();
    }

    public boolean existsByFirstName(String firstName) {

        return userService.existsByFirstName(firstName);
    }
}