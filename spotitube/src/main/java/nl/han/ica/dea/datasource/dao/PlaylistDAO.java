package nl.han.ica.dea.datasource.dao;

import nl.han.ica.dea.datasource.datamappers.PlaylistsDataMapper;
import nl.han.ica.dea.controller.dto.PlaylistDTO;
import nl.han.ica.dea.controller.dto.PlaylistsDTO;
import nl.han.ica.dea.service.ConnectionService;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;

public class PlaylistDAO {

    private ConnectionService connectionService;
    private PlaylistsDataMapper playlistsDataMapper;

    public Response getAllPlaylists(String token) {
        Response response = null;
        connectionService.initConnection();
       try {
           ArrayList<PlaylistDTO> playlists = playlistsDataMapper.mapToDTO(queryPlaylistInfo(token));
           int playlistLength = getPlaylistsLength(playlists);
           PlaylistsDTO playlistsDTO = new PlaylistsDTO(playlists, playlistLength);
           response = Response
                   .status(Response.Status.OK)
                   .entity(playlistsDTO)
                   .build();
       } catch(SQLException e) {
           e.printStackTrace();
           throw new BadRequestException();
       }
        connectionService.closeConnection();
       return response;
    }

    public Response deletePlaylist(String token, int id) {
        Response response = null;
        connectionService.initConnection();
        try {
            queryDeletePlaylist(id);
            response = getAllPlaylists(token);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
        connectionService.closeConnection();
        return response;
    }

    public Response addPlaylist(String token, PlaylistDTO playlistDTO) {
        connectionService.initConnection();
        Response response = null;
        try {
            queryAddPlaylist(token, playlistDTO);
            response = getAllPlaylists(token);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
        connectionService.closeConnection();
        return response;
    }

    public Response editPlaylistName(String token, PlaylistDTO playlist) {
        Response response = null;
        connectionService.initConnection();
        try {
            queryEditPlaylistName(playlist);
            response = getAllPlaylists(token);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
        connectionService.closeConnection();
        return response;
    }

    private void queryEditPlaylistName(PlaylistDTO playlist) throws SQLException {
        var sql = "call editPlaylistName(?, ?);";
        var stmt = connectionService.getConnection().prepareStatement(sql);
        stmt.setInt(1, playlist.getId());
        stmt.setString(2, playlist.getName());
        stmt.executeUpdate();
    }

    private void queryAddPlaylist(String token, PlaylistDTO playlistDTO) throws SQLException {
        var sql = "call addPlaylist(?, ?);";
        var stmt = connectionService.getConnection().prepareStatement(sql);
        stmt.setString(1, token);
        stmt.setString(2, playlistDTO.getName());
        stmt.executeUpdate();
    }

    private void queryDeletePlaylist(int id) throws SQLException {
        var sql = "call deletePlaylist(?);";
        var stmt = connectionService.getConnection().prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    private boolean isOwner(String token, int id) throws SQLException {
        boolean isOwner = false;
        var sql = "call doesUserOwnPlaylist(?, ?);";
        var stmt = connectionService.getConnection().prepareStatement(sql);
        stmt.setString(1, token);
        stmt.setInt(2, id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            isOwner = rs.getBoolean("isOwner");
        }
        return isOwner;
    }

    private ResultSet queryPlaylistInfo(String token) throws SQLException {
        var sql = "call getAllPlaylists(?);";
        var stmt = connectionService.getConnection().prepareStatement(sql);
        stmt.setString(1, token);
        ResultSet rs = stmt.executeQuery();
        return rs;
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
    private void setPlaylistsDataMapper(PlaylistsDataMapper playlistsDataMapper) {
        this.playlistsDataMapper = playlistsDataMapper;
    }

    @Inject
    private void setConnectionService(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }
}
