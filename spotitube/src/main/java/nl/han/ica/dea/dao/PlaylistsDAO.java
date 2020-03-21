package nl.han.ica.dea.dao;

import nl.han.ica.dea.database.util.DatabaseProperties;
import nl.han.ica.dea.dto.PlaylistDTO;
import nl.han.ica.dea.dto.PlaylistsDTO;

import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;

public class PlaylistsDAO {

    private Connection connection = null;
    private DatabaseProperties dbp = new DatabaseProperties();
    private ResultSet rs;

    public PlaylistsDTO getAllPlaylists(String token) throws SQLException {
        PlaylistsDTO playlistsDTO;
        rs = queryPlaylistInfo(token);
        ArrayList<PlaylistDTO> playlists = getPlaylistInfo();
        int playlistLength = getPlaylistsLength(playlists);
        playlistsDTO = new PlaylistsDTO(playlists, playlistLength);
        return playlistsDTO;
    }

    public Response deletePlaylist(String token, int id) throws SQLException {
        Response response = null;
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
        return response;
    }

    public Response addPlaylist(String token, PlaylistDTO playlistDTO) throws SQLException {
        Response response = null;
        queryAddPlaylist(token, playlistDTO);
        response = Response
                    .status(Response.Status.CREATED)
                    .entity(getAllPlaylists(token))
                    .build();
        return response;
    }

    public Response editPlaylistName(String token, PlaylistDTO playlist) throws SQLException {
        Response response = null;
        if(isOwner(token, playlist.getId())) {
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
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("call editPlaylistName(" + playlist.getId() + ", \"" + playlist.getName() + "\");");
    }

    private void queryAddPlaylist(String token, PlaylistDTO playlistDTO) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("call addPlaylist(\"" + token + "\", \"" + playlistDTO.getName() + "\");");
    }

    private void queryDeletePlaylist(int id) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("call deletePlaylist(" + id + ");");
    }

    public boolean isOwner(String token, int id) throws SQLException {
        boolean isOwner = false;
        Statement stmt = connection.createStatement();
        rs = stmt.executeQuery("call doesUserOwnPlaylist(\"" + token + "\", " + id + ");");
        while (rs.next()) {
            isOwner = rs.getBoolean("isOwner");
        }
        return isOwner;
    }

    private ResultSet queryPlaylistInfo(String token) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery("call getAllPlaylists(\"" + token + "\");");
    }

    private ArrayList<PlaylistDTO> getPlaylistInfo() throws SQLException {
        ArrayList<PlaylistDTO> playlists = new ArrayList<>();
        while (rs.next()) {
            playlists.add(new PlaylistDTO(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getBoolean("isowner")));
        }
        return playlists;
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
        Statement stmt = connection.createStatement();
        rs = stmt.executeQuery("call getPlaylistLength(\"" + playlistDTO.getId() + "\");");
        while (rs.next()) {
            length = rs.getInt("playlistlength");
        }
        return length;
    }

}
