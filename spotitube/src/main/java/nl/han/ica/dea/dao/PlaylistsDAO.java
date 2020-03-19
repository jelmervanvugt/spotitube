package nl.han.ica.dea.dao;

import nl.han.ica.dea.database.util.DatabaseProperties;
import nl.han.ica.dea.dto.PlaylistsDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistsDAO {

    private Connection connection = null;
    private DatabaseProperties dbp = new DatabaseProperties();
    private ResultSet rs;

    public PlaylistsDTO getAllPlaylists() {

    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initConnection() {
        try {
            Class.forName(dbp.driverString());
            connection = DriverManager.getConnection(dbp.connectionString());
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error connecting to a database: " + e);
        }
    }

}
