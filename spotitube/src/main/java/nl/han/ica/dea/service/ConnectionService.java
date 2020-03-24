package nl.han.ica.dea.service;

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
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error connecting to a database: " + e);
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
    private void setDbp(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

}
