package nl.han.ica.dea.dao;

import nl.han.ica.dea.database.util.DatabaseProperties;
import nl.han.ica.dea.datamappers.PlaylistsDataMapper;
import nl.han.ica.dea.dto.PlaylistDTO;
import nl.han.ica.dea.dto.PlaylistsDTO;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;

public class PlaylistDAO {

    private Connection connection = null;
    private DatabaseProperties dbp = new DatabaseProperties();
    private PlaylistsDataMapper playlistsDataMapper;
    private ResultSet rs;

    public PlaylistsDTO getAllPlaylists(String token) throws SQLException {
        PlaylistsDTO playlistsDTO;
        rs = queryPlaylistInfo(token);
        ArrayList<PlaylistDTO> playlists = playlistsDataMapper.mapToDTO(rs);
        int playlistLength = getPlaylistsLength(playlists);
        playlistsDTO = new PlaylistsDTO(playlists, playlistLength);
        return playlistsDTO;
    }

    public Response deletePlaylist(String token, int id) {
        Response response = null;
        try {
            if (isOwner(token, id)) {
                queryDeletePlaylist(id);
                response = Response
                        .status(Response.Status.OK)
                        .entity(getAllPlaylists(token))
                        .build();
            }
            response = Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return response;
    }

    public Response addPlaylist(String token, PlaylistDTO playlistDTO) {
        Response response = null;
        try {
            queryAddPlaylist(token, playlistDTO);
            response = Response
                    .status(Response.Status.CREATED)
                    .entity(getAllPlaylists(token))
                    .build();
            return response;
        } catch(SQLException e) {
            e.printStackTrace();
            response = Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }
        return response;
    }

    public Response editPlaylistName(String token, PlaylistDTO playlist) {
        Response response = null;
        try {
            if (isOwner(token, playlist.getId())) {
                queryEditPlaylistName(playlist);
                response = Response
                        .status(Response.Status.OK)
                        .entity(getAllPlaylists(token))
                        .build();
            } else {
                response = Response
                        .status(Response.Status.UNAUTHORIZED)
                        .build();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
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

    private void queryEditPlaylistName(PlaylistDTO playlist) throws SQLException {
        var sql = "call editPlaylistName(?, ?);";
        var stmt = connection.prepareStatement(sql);
        stmt.setInt(1, playlist.getId());
        stmt.setString(2, playlist.getName());
        stmt.executeUpdate();
    }

    private void queryAddPlaylist(String token, PlaylistDTO playlistDTO) throws SQLException {
        var sql = "call addPlaylist(?, ?);";
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1, token);
        stmt.setString(2, playlistDTO.getName());
        stmt.executeUpdate();
    }

    private void queryDeletePlaylist(int id) throws SQLException {
        var sql = "call deletePlaylist(?);";
        var stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public boolean isOwner(String token, int id) throws SQLException {
        boolean isOwner = false;
        var sql = "call doesUserOwnPlaylist(?, ?);";
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1, token);
        stmt.setInt(2, id);
        rs = stmt.executeQuery();
        while (rs.next()) {
            isOwner = rs.getBoolean("isOwner");
        }
        return isOwner;
    }

    private ResultSet queryPlaylistInfo(String token) throws SQLException {
        var sql = "call getAllPlaylists(?);";
        var stmt = connection.prepareStatement(sql);
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
        var stmt = connection.prepareStatement(sql);
        stmt.setInt(1, playlistDTO.getId());
        rs = stmt.executeQuery();
        while (rs.next()) {
            length = rs.getInt("playlistlength");
        }
        return length;
    }

    @Inject
    private void setPlaylistsDataMapper(PlaylistsDataMapper playlistsDataMapper) {this.playlistsDataMapper = playlistsDataMapper;}

}