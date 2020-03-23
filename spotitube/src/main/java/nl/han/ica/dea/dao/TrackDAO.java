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

    public Response getTracksFromPlaylist(int playlistId) throws SQLException {
        Response response;
        return response = Response
                .status(Response.Status.OK)
                .entity(getAllTracksFromPlaylist(playlistId))
                .build();
    }

    public Response getTracksNotInPlaylist(int playlistId) throws SQLException {
        Response response;
        return response = Response
                .status(Response.Status.OK)
                .entity(getAllTracksNotInPlaylist(playlistId))
                .build();
    }

    public Response addTrackToPlaylist(String token, int playlistId, TrackDTO track) throws SQLException {
        Response response;
        playlistsDAO.initConnection();
        if(playlistsDAO.isOwner(token, playlistId) && queryDoesPlaylistContainTrack(playlistId, track.getId())) {
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
        playlistsDAO.closeConnection();
        return response;
    }

    public Response deleteTrackFromPlaylist(String token, int playlistId, int trackId) throws SQLException {
        Response response;
        playlistsDAO.initConnection();
        if(playlistsDAO.isOwner(token, playlistId)) {
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
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("call addTrackToPlaylist(" + playlistId + ", " + track.getId() + ", " + track.getOfflineAvailable() + ");");
    }

    private boolean queryDoesPlaylistContainTrack(int playlistId, int trackId) throws SQLException {
        Statement stmt = connection.createStatement();
        rs = stmt.executeQuery("call doesPlaylistContainTrack(" + playlistId + ", " + trackId + ");");
        while(rs.next()) {
            if(rs.getInt("nResults") == 0) { return true; }
        }
        return false;
    }

    private TracksDTO getAllTracksFromPlaylist(int playlistId) throws SQLException {
        Statement stmt = connection.createStatement();
        return tracksDataMapper.mapToDTO(stmt.executeQuery("call getTracksFromPlaylist(" + playlistId + ");"));
    }

    private TracksDTO getAllTracksNotInPlaylist(int playlistId) throws SQLException {
        Statement stmt = connection.createStatement();
        return tracksDataMapper.mapToDTO(stmt.executeQuery("call getAllTracksNotInPlaylist(" + playlistId + ");"));
    }

    private void queryDeleteTrackFromPlaylist(int playlistId, int trackId) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("call deleteTrackFromPlaylist(" + playlistId + ", " + trackId + ");");
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
