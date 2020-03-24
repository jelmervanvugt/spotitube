package nl.han.ica.dea.dao;

import nl.han.ica.dea.datamappers.TracksDataMapper;
import nl.han.ica.dea.dto.TrackDTO;
import nl.han.ica.dea.dto.TracksDTO;
import nl.han.ica.dea.services.ConnectionService;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import java.sql.*;

public class TrackDAO {

    private TracksDataMapper tracksDataMapper;
    private ConnectionService connectionService;

    public Response getTracksFromPlaylist(int playlistId) {
        connectionService.initConnection();
        Response response = null;
        try {
            response = Response
                    .status(Response.Status.OK)
                    .entity(getAllTracksFromPlaylist(playlistId))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
        connectionService.closeConnection();
        return response;
    }

    public Response getTracksNotInPlaylist(int playlistId) {
        connectionService.initConnection();
        Response response = null;
        try {
            response = Response
                    .status(Response.Status.OK)
                    .entity(getAllTracksNotInPlaylist(playlistId))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
        connectionService.closeConnection();
        return response;
    }

    public Response addTrackToPlaylist(String token, int playlistId, TrackDTO track) {
        connectionService.initConnection();
        Response response = null;
        try {
            queryAddTrackToPlaylist(playlistId, track);
            response = Response
                    .status(Response.Status.OK)
                    .entity(getAllTracksFromPlaylist(playlistId))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
        connectionService.closeConnection();
        return response;
    }

    public Response deleteTrackFromPlaylist(String token, int playlistId, int trackId) {
        connectionService.initConnection();
        Response response = null;
        try {
            queryDeleteTrackFromPlaylist(playlistId, trackId);
            response = Response
                    .status(Response.Status.OK)
                    .entity(getAllTracksFromPlaylist(playlistId))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
        connectionService.closeConnection();
        return response;
    }

    private void queryAddTrackToPlaylist(int playlistId, TrackDTO track) throws SQLException {
        var sql = "call addTrackToPlaylist(?, ?, ?);";
        var stmt = connectionService.getConnection().prepareStatement(sql);
        stmt.setInt(1, playlistId);
        stmt.setInt(2, track.getId());
        stmt.setBoolean(3, track.getOfflineAvailable());
        stmt.executeUpdate();
    }

    private TracksDTO getAllTracksFromPlaylist(int playlistId) throws SQLException {
        var sql = "call getTracksFromPlaylist(?);";
        var stmt = connectionService.getConnection().prepareStatement(sql);
        stmt.setInt(1, playlistId);
        TracksDTO tracks = tracksDataMapper.mapToDTO(stmt.executeQuery());
        return tracks;
    }

    private TracksDTO getAllTracksNotInPlaylist(int playlistId) throws SQLException {
        var sql = "call getAllTracksNotInPlaylist(?);";
        var stmt = connectionService.getConnection().prepareStatement(sql);
        stmt.setInt(1, playlistId);
        TracksDTO tracks = tracksDataMapper.mapToDTO(stmt.executeQuery());
        return tracks;
    }

    private void queryDeleteTrackFromPlaylist(int playlistId, int trackId) throws SQLException {
        var sql = "call deleteTrackFromPlaylist(?, ?);";
        var stmt = connectionService.getConnection().prepareStatement(sql);
        stmt.setInt(1, playlistId);
        stmt.setInt(2, trackId);
        stmt.executeUpdate();
    }

    @Inject
    private void setTracksDataMapper(TracksDataMapper tracksDataMapper)
    { this.tracksDataMapper = tracksDataMapper; }

    @Inject
    private void setConnectionService(ConnectionService connectionService)
    { this.connectionService = connectionService; }

}
