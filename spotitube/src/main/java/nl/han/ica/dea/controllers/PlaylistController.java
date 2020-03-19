package nl.han.ica.dea.controllers;

import nl.han.ica.dea.dao.PlaylistDAO;
import nl.han.ica.dea.dao.PlaylistsDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("playlists")
public class PlaylistController {

    private PlaylistsDAO playlistsDAO;

    @GET
    @Path("{token}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@PathParam("token") String token) {
        Response response;
        try {
            playlistsDAO.initConnection();
            response = Response
                    .status(Response.Status.OK)
                    .entity(new PlaylistDTO())
                    .build();
            playlistsDAO.closeConnection();
        } catch (SQLException e) {
            response = Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
            e.printStackTrace();
        }
        return response;
    }

    private void setPlaylistDAO(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

}
