package nl.han.ica.dea.dao;

import nl.han.ica.dea.database.util.DatabaseProperties;
import nl.han.ica.dea.datamappers.TracksDataMapper;
import nl.han.ica.dea.dto.TrackDTO;
import nl.han.ica.dea.dto.TracksDTO;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.sql.*;

public class TrackDAO {

    private Connection connection = null;
    private DatabaseProperties dbp = new DatabaseProperties();
    private TracksDataMapper tracksDataMapper;

    public Response getTracksFromPlaylist(int playlistId) {
        initConnection();
        Response response = null;
        try {
            response = Response
                    .status(Response.Status.OK)
                    .entity(getAllTracksFromPlaylist(playlistId))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            response = Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }
        closeConnection();
        return response;
    }

    public Response getTracksNotInPlaylist(int playlistId) {
        initConnection();
        Response response = null;
        try {
            response = Response
                    .status(Response.Status.OK)
                    .entity(getAllTracksNotInPlaylist(playlistId))
                    .build();
        } catch (SQLException e) {
            response = Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
            e.printStackTrace();
        }
        closeConnection();
        return response;
    }

    public Response addTrackToPlaylist(String token, int playlistId, TrackDTO track) {
        initConnection();
        Response response = null;
        try {
            queryAddTrackToPlaylist(playlistId, track);
            response = Response
                    .status(Response.Status.OK)
                    .entity(getAllTracksFromPlaylist(playlistId))
                    .build();
        } catch (SQLException e) {
            response = Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
            e.printStackTrace();
        }
        closeConnection();
        return response;
    }

    public Response deleteTrackFromPlaylist(String token, int playlistId, int trackId) {
        initConnection();
        Response response = null;
        try {
            queryDeleteTrackFromPlaylist(playlistId, trackId);
            response = Response
                    .status(Response.Status.OK)
                    .entity(getAllTracksFromPlaylist(playlistId))
                    .build();
        } catch (SQLException e) {
            response = Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
            e.printStackTrace();
        }
        closeConnection();
        return response;
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initConnection() {
        try {
            Class.forName(dbp.driverString());
            connection = DriverManager.getConnection(dbp.connectionString());
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error connecting to a database: " + e);
        }
    }

    private void queryAddTrackToPlaylist(int playlistId, TrackDTO track) throws SQLException {
        var sql = "call addTrackToPlaylist(?, ?, ?);";
        var stmt = connection.prepareStatement(sql);
        stmt.setInt(1, playlistId);
        stmt.setInt(2, track.getId());
        stmt.setBoolean(3, track.getOfflineAvailable());
        stmt.executeUpdate();
    }

    private TracksDTO getAllTracksFromPlaylist(int playlistId) throws SQLException {
        var sql = "call getTracksFromPlaylist(?);";
        var stmt = connection.prepareStatement(sql);
        stmt.setInt(1, playlistId);
        TracksDTO tracks = tracksDataMapper.mapToDTO(stmt.executeQuery());
        return tracks;
    }

    private TracksDTO getAllTracksNotInPlaylist(int playlistId) throws SQLException {
        var sql = "call getAllTracksNotInPlaylist(?);";
        var stmt = connection.prepareStatement(sql);
        stmt.setInt(1, playlistId);
        TracksDTO tracks = tracksDataMapper.mapToDTO(stmt.executeQuery());
        return tracks;
    }

    private void queryDeleteTrackFromPlaylist(int playlistId, int trackId) throws SQLException {
        var sql = "call deleteTrackFromPlaylist(?, ?);";
        var stmt = connection.prepareStatement(sql);
        stmt.setInt(1, playlistId);
        stmt.setInt(2, trackId);
        stmt.executeUpdate();
    }

    @Inject
    private void setTracksDataMapper(TracksDataMapper tracksDataMapper) {
        this.tracksDataMapper = tracksDataMapper;
    }

}
