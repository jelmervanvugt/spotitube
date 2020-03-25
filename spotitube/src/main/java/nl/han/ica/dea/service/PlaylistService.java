package nl.han.ica.dea.service;

import nl.han.ica.dea.datasource.dao.PlaylistDAO;
import nl.han.ica.dea.controller.dto.PlaylistDTO;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

public class PlaylistService {

    private PlaylistDAO playlistDAO;

    public Response editPlaylistName(String token, PlaylistDTO playlist) {
        try {
            playlistDAO.editPlaylistName(playlist);
            return Response
                    .status(Response.Status.OK)
                    .entity(playlistDAO.getAllPlaylists(token))
                    .build();
        } catch(SQLException e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
    }

    public Response addPlaylist(String token, PlaylistDTO playlist) {
        try {
            playlistDAO.addPlaylist(token, playlist);
            return Response
                    .status(Response.Status.OK)
                    .entity(playlistDAO.getAllPlaylists(token))
                    .build();
        } catch(SQLException e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
    }

    public Response deletePlaylist(String token, int playlistId) {
        try {
            playlistDAO.deletePlaylist(playlistId);
            return Response
                    .status(Response.Status.OK)
                    .entity(playlistDAO.getAllPlaylists(token))
                    .build();
        } catch(SQLException e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
    }

    public Response getAllPlaylists(String token) {
        try {
            return Response
                    .status(Response.Status.OK)
                    .entity(playlistDAO.getAllPlaylists(token))
                    .build();
        } catch(SQLException e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
    }

    @Inject
    public void setPlaylistDAO(PlaylistDAO playlistDAO)
    { this.playlistDAO = playlistDAO; }
}



