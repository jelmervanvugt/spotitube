package nl.han.ica.dea.service;

import nl.han.ica.dea.datasource.dao.PlaylistDAO;
import nl.han.ica.dea.controller.dto.PlaylistDTO;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

public class PlaylistService {

    private PlaylistDAO playlistDAO;

    public Response editPlaylistName(String token, PlaylistDTO playlist)
    { return playlistDAO.editPlaylistName(token, playlist); }

    public Response addPlaylist(String token, PlaylistDTO playlist)
    { return playlistDAO.addPlaylist(token, playlist); }

    public Response deletePlaylist(String token, int id)
    { return playlistDAO.deletePlaylist(token, id); }

    public Response getAllPlaylists(String token)
    { return playlistDAO.getAllPlaylists(token); }

    @Inject
    public void setPlaylistDAO(PlaylistDAO playlistDAO)
    { this.playlistDAO = playlistDAO; }
}



