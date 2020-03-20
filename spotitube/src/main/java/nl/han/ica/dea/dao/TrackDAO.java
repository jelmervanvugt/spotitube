package nl.han.ica.dea.dao;

import nl.han.ica.dea.database.util.DatabaseProperties;
import nl.han.ica.dea.dto.TrackDTO;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;

public class TrackDAO {

    private Connection connection = null;
    private DatabaseProperties dbp = new DatabaseProperties();
    private ResultSet rs;

    public Response getTracksFromPlaylist(int playlistId) throws SQLException {
        Response response;
        queryGetTracksFromPlaylist(playlistId);
        return response = Response
                .status(Response.Status.OK)
                .entity(procesTracksFromPlaylist())
                .build();
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

    private void queryGetTracksFromPlaylist(int playlistId) throws SQLException {
        Statement stmt = connection.createStatement();
        rs = stmt.executeQuery("call getTracksFromPlaylist(" + playlistId + ");");
    }

    private ArrayList<TrackDTO> procesTracksFromPlaylist() throws SQLException {
        ArrayList<TrackDTO> tracks = new ArrayList<>();
        while (rs.next()) {
            tracks.add(new TrackDTO(
                rs.getInt("id"),
                rs.getString("title"),
                    rs.getString("performer"),
                    rs.getInt("duration"),
                    rs.getString("album"),
                    rs.getInt("playcount"),
                    rs.getString("publicationDate"),
                    rs.getString("description"),
                    rs.getBoolean("offlineAvailable")
            ));
        }
        return tracks;
    }

}
