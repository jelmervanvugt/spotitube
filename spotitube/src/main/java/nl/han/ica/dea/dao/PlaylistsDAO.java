package nl.han.ica.dea.dao;

import nl.han.ica.dea.database.util.DatabaseProperties;
import nl.han.ica.dea.dto.PlaylistDTO;
import nl.han.ica.dea.dto.PlaylistsDTO;
import nl.han.ica.dea.dto.TrackDTO;

import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;

public class PlaylistsDAO {

    private Connection connection = null;
    private DatabaseProperties dbp = new DatabaseProperties();
    private ResultSet rs;

    public PlaylistsDTO getAllPlaylists(String token) throws SQLException {
        PlaylistsDTO playlistsDTO = null;
            rs = queryPlaylistInfo(token);
            ArrayList<PlaylistDTO> playlists = getPlaylistInfo();
            int playlistLength = getPlaylistsLength(playlists);
            playlistsDTO = new PlaylistsDTO(playlists, playlistLength);
        return playlistsDTO;
    }

    public Response deletePlaylist(String token, int id) throws SQLException {
        Response response = null;
            if(isOwner(token, id)) {
              queryDeletePlaylist(id);
                response = Response
                        .status(Response.Status.OK)
                        .entity(getAllPlaylists(token))
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

    private void queryDeletePlaylist(int id) throws SQLException {
        Statement stmt = connection.createStatement();
        rs = stmt.executeQuery("call deletePlaylist(" + id + ");");
    }

    private boolean isOwner(String token, int id) throws SQLException {
        boolean isOwner = false;
        Statement stmt = connection.createStatement();
        rs = stmt.executeQuery("call doesUserOwnPlaylist(\"" + token + "\", " + id + ");");
        while(rs.next()) {
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
                    rs.getBoolean("isowner"),
                    new ArrayList<TrackDTO>()));
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
