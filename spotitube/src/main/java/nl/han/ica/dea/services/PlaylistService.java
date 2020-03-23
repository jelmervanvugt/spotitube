package nl.han.ica.dea.services;

import nl.han.ica.dea.dao.PlaylistDAO;
import nl.han.ica.dea.dto.PlaylistDTO;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

public class PlaylistService {

    private PlaylistDAO playlistDAO;

    @Inject
    private void setPlaylistDAO(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    public Response editPlaylistName(String token, PlaylistDTO playlist) {
        Response response;
        playlistDAO.initConnection();
        response = playlistDAO.editPlaylistName(token, playlist);
        playlistDAO.closeConnection();
        return response;
    }

    public Response addPlaylist(String token, PlaylistDTO playlist) {
        Response response;
        playlistDAO.initConnection();
        response = playlistDAO.addPlaylist(token, playlist);
        playlistDAO.closeConnection();
        return response;
    }

    public Response deletePlaylist(String token, int id) {
        Response response;
        playlistDAO.initConnection();
        response = playlistDAO.deletePlaylist(token, id);
        playlistDAO.closeConnection();
        return response;
    }

    public Response getAllPlaylists(String token) {
        Response response = null;
        playlistDAO.initConnection();
        try {
            response = Response
                    .status(Response.Status.OK)
                    .entity(playlistDAO.getAllPlaylists(token))
                    .build();
        } catch (SQLException e) {
            response = Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
            e.printStackTrace();
        }
        playlistDAO.closeConnection();
        return response;
    }
}
