package nl.han.ica.dea.datasource.dao;

import nl.han.ica.dea.datasource.datamappers.TracksDataMapper;
import nl.han.ica.dea.controller.dto.TrackDTO;
import nl.han.ica.dea.controller.dto.TracksDTO;
import nl.han.ica.dea.service.ConnectionService;

import javax.inject.Inject;
import java.sql.*;

public class TrackDAO {

    private TracksDataMapper tracksDataMapper;
    private ConnectionService connectionService;

    public void addTrackToPlaylist(int playlistId, TrackDTO track) throws SQLException {
        connectionService.initConnection();
        var sql = "call addTrackToPlaylist(?, ?, ?);";
        var stmt = connectionService.getConnection().prepareStatement(sql);
        stmt.setInt(1, playlistId);
        stmt.setInt(2, track.getId());
        stmt.setBoolean(3, track.getOfflineAvailable());
        stmt.executeUpdate();
        connectionService.closeConnection();
    }

    public TracksDTO getAllTracksFromPlaylist(int playlistId) throws SQLException {
        connectionService.initConnection();
        var sql = "call getTracksFromPlaylist(?);";
        var stmt = connectionService.getConnection().prepareStatement(sql);
        stmt.setInt(1, playlistId);
        TracksDTO tracks = tracksDataMapper.mapToDTO(stmt.executeQuery());
        connectionService.closeConnection();
        return tracks;
    }

    public TracksDTO getAllTracksNotInPlaylist(int playlistId) throws SQLException {
        connectionService.initConnection();
        var sql = "call getAllTracksNotInPlaylist(?);";
        var stmt = connectionService.getConnection().prepareStatement(sql);
        stmt.setInt(1, playlistId);
        TracksDTO tracks = tracksDataMapper.mapToDTO(stmt.executeQuery());
        connectionService.closeConnection();
        return tracks;
    }

    public void deleteTrackFromPlaylist(int playlistId, int trackId) throws SQLException {
        connectionService.initConnection();
        var sql = "call deleteTrackFromPlaylist(?, ?);";
        var stmt = connectionService.getConnection().prepareStatement(sql);
        stmt.setInt(1, playlistId);
        stmt.setInt(2, trackId);
        stmt.executeUpdate();
        connectionService.closeConnection();
    }

    @Inject
    public void setTracksDataMapper(TracksDataMapper tracksDataMapper)
    { this.tracksDataMapper = tracksDataMapper; }

    @Inject
    public void setConnectionService(ConnectionService connectionService)
    { this.connectionService = connectionService; }

}
