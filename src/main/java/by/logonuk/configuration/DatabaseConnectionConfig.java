package by.logonuk.configuration;

import by.logonuk.util.DatabasePropertiesReader;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static by.logonuk.util.DatabasePropertiesReader.*;

public class DatabaseConnectionConfig {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DatabasePropertiesReader.getProperty(POSTGRES_DRIVER_NAME));
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        String url = DatabasePropertiesReader.getProperty(DATABASE_URL);
        String login = DatabasePropertiesReader.getProperty(DATABASE_LOGIN);
        String password = DatabasePropertiesReader.getProperty(DATABASE_PASSWORD);

        String jdbcURL = StringUtils.join(url);

        return DriverManager.getConnection(jdbcURL, login, password);
    }
}
