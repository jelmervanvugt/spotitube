package nl.han.ica.dea.controllers;

import nl.han.ica.dea.dao.PlaylistsDAO;
import nl.han.ica.dea.dto.PlaylistsDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("playlists")
public class PlaylistController {

    private PlaylistsDAO playlistsDAO;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") int id, @QueryParam("token") String token) {

        return null;

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token) {
        Response response;
        playlistsDAO.initConnection();

        response = Response
                .status(Response.Status.OK)
                .entity(playlistsDAO.getAllPlaylists(token))
                .build();

        playlistsDAO.closeConnection();
        return response;
    }

    @Inject
    private void setPlaylistsDAO(PlaylistsDAO playlistsDAO) {
        this.playlistsDAO = playlistsDAO;
    }

}
