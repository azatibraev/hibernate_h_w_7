package peaksoft;

import peaksoft.dao.UserDaoHibernateImpl;
import peaksoft.model.User;
import peaksoft.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserServiceImpl userService = new UserServiceImpl();

//        userService.createUsersTable();
//        userService.dropUsersTable();
//        userService.saveUser("Benny", "Hill", (byte) 18);
//        userService.saveUser("Tony", "Montana", (byte) 19);
//        userService.saveUser("Jack", "Black", (byte) 20);
//        userService.saveUser("Michael", "Jackson", (byte) 21);
//        userService.saveUser("Andy", "Murray", (byte) 22);
//        userService.saveUser("Altynbek", "Jumadylov", (byte) 18);
//        userService.removeUserById(5);
//        System.out.println(userService.getAllUsers());
//        userService.cleanUsersTable();
//        System.out.println(userService.existsByFirstName("Altynbek"));


//        #############################################################

        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();

        userDaoHibernate.saveUser("Bill", "Gates", (byte) 65);
        userDaoHibernate.saveUser("Mark", "Zuckerberg", (byte) 35);
        userDaoHibernate.saveUser("Elon", "Musk", (byte) 52);

        userDaoHibernate.removeUserById(1);

        userDaoHibernate.existsByFirstName("Elon");

        userDaoHibernate.getAllUsers().forEach(System.out::println);

        userDaoHibernate.cleanUsersTable();

        userDaoHibernate.dropUsersTable();


    }
}