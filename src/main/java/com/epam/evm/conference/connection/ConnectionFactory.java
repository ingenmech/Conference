package com.epam.evm.conference.connection;

import com.epam.evm.conference.exception.ConnectionPoolException;
import com.epam.evm.conference.exception.DaoException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private final static String DB_PROPERTIES = "properties/database.properties";
    private final Properties properties;

    public ConnectionFactory() {
        properties = readPropertiesFile(DB_PROPERTIES);
    }

    public ProxyConnection create() throws DaoException {

        String driver = properties.getProperty("db.driver");
        String url = properties.getProperty("db.url");
        String userName = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, userName, password);
            ConnectionPool pool = ConnectionPool.getInstance();
            return new ProxyConnection(connection, pool);
        } catch (SQLException | ClassNotFoundException e) {
            throw new DaoException("Can't create ProxyConnection", e);
        }
    }

    private Properties readPropertiesFile(String fileName){

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
