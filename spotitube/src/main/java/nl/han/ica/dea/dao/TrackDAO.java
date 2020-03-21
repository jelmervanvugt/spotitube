package nl.han.ica.dea.dao;

import nl.han.ica.dea.database.util.DatabaseProperties;
import nl.han.ica.dea.dto.TrackDTO;
import nl.han.ica.dea.dto.TracksDTO;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;

public class TrackDAO {

    private PlaylistsDAO playlistsDAO;
    private Connection connection = null;
    private DatabaseProperties dbp = new DatabaseProperties();
    private ResultSet rs;

    public Response getTracksFromPlaylist(int playlistId) throws SQLException {
        Response response;
        queryGetTracksFromPlaylist(playlistId);
        return response = Response
                .status(Response.Status.OK)
                .entity(new TracksDTO(procesTracksFromPlaylist()))
                .build();
    }

    public Response addTrackToPlaylist(String token, int playlistId, TrackDTO track) {
        Response response;
        playlistsDAO.initConnection();
        if(playlistsDAO.isOwner(token, playlistId)) {

        } else {
            response = Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
        }
        playlistsDAO.closeConnection();
        return response;
    }

    public Response deleteTrackFromPlaylist(String token, int playlistId, int trackId) throws SQLException {
        Response response;
        playlistsDAO.initConnection();
        if(playlistsDAO.isOwner(token, playlistId)) {
            queryDeleteTrackFromPlaylist(playlistId, trackId);
            queryGetTracksFromPlaylist(playlistId);
            response = Response
                    .status(Response.Status.OK)
                    .entity(new TracksDTO(procesTracksFromPlaylist()))
                    .build();
        } else {
            response = Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
        }
        playlistsDAO.closeConnection();
        return response;
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

    private void queryDeleteTrackFromPlaylist(int playlistId, int trackId) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("call deleteTrackFromPlaylist(" + playlistId + ", " + trackId + ");");
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

    @Inject
    private void setPlaylistsDAO(PlaylistsDAO playlistsDAO) {
       this.playlistsDAO = playlistsDAO;
    }

}
