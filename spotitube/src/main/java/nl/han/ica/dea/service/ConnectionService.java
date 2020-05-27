package nl.han.ica.dea.service;

import nl.han.ica.dea.datasource.exceptions.DatabaseConnectionException;
import nl.han.ica.dea.datasource.exceptions.DatabaseConnectionExceptionMapper;
import nl.han.ica.dea.datasource.util.DatabaseProperties;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionService {

    private Connection connection;
    private DatabaseProperties databaseProperties;

    public Connection getConnection() {
        return connection;
    }

    public void initConnection() {
        try {
            Class.forName(databaseProperties.driverString());
            connection = DriverManager.getConnection(databaseProperties.connectionString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseConnectionException();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties)
    { this.databaseProperties = databaseProperties; }

}
