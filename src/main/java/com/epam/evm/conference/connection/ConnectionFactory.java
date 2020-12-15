package com.epam.evm.conference.connection;

import com.epam.evm.conference.exception.ConnectionPoolException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private final static String DB_PROPERTIES = "properties/database.properties";
    private final static String DB_DRIVER = "db.driver";
    private final static String DB_URL = "db.url";
    private final static String DB_USER = "db.user";
    private final static String DB_PASSWORD = "db.password";

    private final Properties properties;

    public ConnectionFactory() {
        properties = readProperties(DB_PROPERTIES);
    }

    public Connection create() {

        String driver = properties.getProperty(DB_DRIVER);
        String url = properties.getProperty(DB_URL);
        String userName = properties.getProperty(DB_USER);
        String password = properties.getProperty(DB_PASSWORD);

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, userName, password);
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            throw new ConnectionPoolException("Can't create connection", e);
        }
    }

    private Properties readProperties(String fileName){

        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName)){
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new ConnectionPoolException("DB properties not found", e);
        }
    }

}
