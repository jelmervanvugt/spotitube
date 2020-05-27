package nl.han.ica.dea.datasource.DatabaseConnection;

import nl.han.ica.dea.datasource.exceptions.DatabaseConnectionException;

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
            throw new DatabaseConnectionException();
        }
    }

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties)
    { this.databaseProperties = databaseProperties; }

}
