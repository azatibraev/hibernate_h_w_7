package peaksoft.configuration;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import peaksoft.model.User;

import java.util.Properties;

public class DatabaseConnection {

    private EntityManagerFactory entityManagerFactory;

public static EntityManagerFactory createEntityManagerFactory() {
    Properties properties = new Properties();
    properties.setProperty(Environment.DRIVER, "org.postgresql.Driver");
    properties.setProperty(Environment.URL, "jdbc:postgresql://localhost:5432/orm");
    properties.setProperty(Environment.USER, "postgres");
    properties.setProperty(Environment.PASS, "postgres");
    properties.setProperty(Environment.HBM2DDL_AUTO,"update");
    properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
    properties.setProperty(Environment.SHOW_SQL, "true");

    Configuration configuration =new Configuration();
    configuration.setProperties(properties);
    configuration.addAnnotatedClass(User.class);

    return configuration.buildSessionFactory().unwrap(EntityManagerFactory.class);
}

public static EntityManagerFactory factoryEntityManagerFactory() {
    return Persistence.createEntityManagerFactory("java5");
}
}
