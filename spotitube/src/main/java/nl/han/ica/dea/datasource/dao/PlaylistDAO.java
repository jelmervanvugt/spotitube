package nl.han.ica.dea.datasource.dao;

import nl.han.ica.dea.datasource.datamappers.PlaylistsDataMapper;
import nl.han.ica.dea.controller.dto.PlaylistDTO;
import nl.han.ica.dea.controller.dto.PlaylistsDTO;
import nl.han.ica.dea.datasource.DatabaseConnection.ConnectionService;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;

public class PlaylistDAO {

    private ConnectionService connectionService;
    private PlaylistsDataMapper playlistsDataMapper;

    public PlaylistsDTO getAllPlaylists(String token) throws SQLException {
        connectionService.initConnection();
        ArrayList<PlaylistDTO> playlists = playlistsDataMapper.mapToDTO(queryPlaylistInfo(token));
        int playlistLength = getPlaylistsLength(playlists);
        connectionService.closeConnection();
        return new PlaylistsDTO(playlists, playlistLength);
    }

    public void editPlaylistName(PlaylistDTO playlist) throws SQLException {
        connectionService.initConnection();
        var sql = "call editPlaylistName(?, ?);";
        var stmt = connectionService.getConnection().prepareStatement(sql);
        stmt.setInt(1, playlist.getId());
        stmt.setString(2, playlist.getName());
        stmt.executeUpdate();
        connectionService.closeConnection();
    }

    public void addPlaylist(String token, PlaylistDTO playlistDTO) throws SQLException {
        connectionService.initConnection();
        var sql = "call addPlaylist(?, ?);";
        var stmt = connectionService.getConnection().prepareStatement(sql);
        stmt.setString(1, token);
        stmt.setString(2, playlistDTO.getName());
        stmt.executeUpdate();
        connectionService.closeConnection();
    }

    public void deletePlaylist(int id) throws SQLException {
        connectionService.initConnection();
        var sql = "call deletePlaylist(?);";
        var stmt = connectionService.getConnection().prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        connectionService.closeConnection();
    }

    private ResultSet queryPlaylistInfo(String token) throws SQLException {
        var sql = "call getAllPlaylists(?);";
        var stmt = connectionService.getConnection().prepareStatement(sql);
        stmt.setString(1, token);
        return stmt.executeQuery();
    }

    private int getPlaylistsLength(ArrayList<PlaylistDTO> playlists) throws SQLException {
        int playlistsLength = 0;
        for (PlaylistDTO playlist : playlists) {
            playlistsLength += getPlaylistLength(playlist);
        }
        return playlistsLength;
    }

    private int getPlaylistLength(PlaylistDTO playlistDTO) throws SQLException {
        int length = 0;
        var sql = "call getPlaylistLength(?);";
        var stmt = connectionService.getConnection().prepareStatement(sql);
        stmt.setInt(1, playlistDTO.getId());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            length = rs.getInt("playlistlength");
        }
        return length;
    }

    @Inject
    public void setPlaylistsDataMapper(PlaylistsDataMapper playlistsDataMapper) {
        this.playlistsDataMapper = playlistsDataMapper;
    }

    @Inject
    public void setConnectionService(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    // Puur voor test doeleinden.
    public PlaylistsDataMapper getPlaylistsDataMapper() {
        return playlistsDataMapper;
    }

    // Puur voor test doeleinden.
    public ConnectionService getConnectionService() {
        return connectionService;
    }
}
