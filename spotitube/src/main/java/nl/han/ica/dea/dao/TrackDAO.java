package nl.han.ica.dea.dao;

import nl.han.ica.dea.database.util.DatabaseProperties;
import nl.han.ica.dea.datamappers.TracksDataMapper;
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
    private TracksDataMapper tracksDataMapper;

    public Response getTracksFromPlaylist(int playlistId) {
        Response response = null;
        try {
            return response = Response
                    .status(Response.Status.OK)
                    .entity(getAllTracksFromPlaylist(playlistId))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return response = Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }
    }

    public Response getTracksNotInPlaylist(int playlistId) {
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
        return response;
    }

    public Response addTrackToPlaylist(String token, int playlistId, TrackDTO track) {
        Response response = null;
        playlistsDAO.initConnection();
        try {
            if (playlistsDAO.isOwner(token, playlistId) && queryDoesPlaylistContainTrack(playlistId, track.getId())) {
                queryAddTrackToPlaylist(playlistId, track);
                response = Response
                        .status(Response.Status.OK)
                        .entity(getAllTracksFromPlaylist(playlistId))
                        .build();
            } else {
                response = Response
                        .status(Response.Status.BAD_REQUEST)
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        playlistsDAO.closeConnection();
        return response;
    }

    public Response deleteTrackFromPlaylist(String token, int playlistId, int trackId) {
        Response response = null;
        playlistsDAO.initConnection();
        try {
            if (playlistsDAO.isOwner(token, playlistId)) {
                queryDeleteTrackFromPlaylist(playlistId, trackId);
                response = Response
                        .status(Response.Status.OK)
                        .entity(getAllTracksFromPlaylist(playlistId))
                        .build();
            } else {
                response = Response
                        .status(Response.Status.UNAUTHORIZED)
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

    private void queryAddTrackToPlaylist(int playlistId, TrackDTO track) throws SQLException {
        var sql = "call addTrackToPlaylist(?, ?, ?);";
        var stmt = connection.prepareStatement(sql);
        stmt.setInt(1, playlistId);
        stmt.setInt(2, track.getId());
        stmt.setBoolean(3, track.getOfflineAvailable());
        stmt.executeUpdate();
    }

    private boolean queryDoesPlaylistContainTrack(int playlistId, int trackId) throws SQLException {
        var sql = "call doesPlaylistContainTrack(?, ?);";
        var stmt = connection.prepareStatement(sql);
        stmt.setInt(1, playlistId);
        stmt.setInt(2, trackId);
        rs = stmt.executeQuery();

        while (rs.next()) {
            if (rs.getInt("nResults") == 0) {
                return true;
            }
        }
        return false;
    }

    private TracksDTO getAllTracksFromPlaylist(int playlistId) throws SQLException {
        var sql = "call getTracksFromPlaylist(?);";
        var stmt = connection.prepareStatement(sql);
        stmt.setInt(1, playlistId);
        return tracksDataMapper.mapToDTO(stmt.executeQuery());
    }

    private TracksDTO getAllTracksNotInPlaylist(int playlistId) throws SQLException {
        var sql = "call getAllTracksNotInPlaylist(?);";
        var stmt = connection.prepareStatement(sql);
        stmt.setInt(1, playlistId);
        return tracksDataMapper.mapToDTO(stmt.executeQuery());
    }

    private void queryDeleteTrackFromPlaylist(int playlistId, int trackId) throws SQLException {
        var sql = "call deleteTrackFromPlaylist(?, ?);";
        var stmt = connection.prepareStatement(sql);
        stmt.setInt(1, playlistId);
        stmt.setInt(2, trackId);
        stmt.executeUpdate();
    }

    @Inject
    private void setPlaylistsDAO(PlaylistsDAO playlistsDAO) {
        this.playlistsDAO = playlistsDAO;
    }

    @Inject
    private void setTracksDataMapper(TracksDataMapper tracksDataMapper) {
        this.tracksDataMapper = tracksDataMapper;
    }

}
