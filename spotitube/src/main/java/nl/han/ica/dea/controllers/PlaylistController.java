package nl.han.ica.dea.controllers;

import nl.han.ica.dea.dao.PlaylistDAO;
import nl.han.ica.dea.dto.LoginDTO;
import nl.han.ica.dea.dto.LoginResponseDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("playlists")
public class PlaylistController {

    private PlaylistDAO playlistDAO;

    @GET
    @Path("{token}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@PathParam("token") String token) {
        playlistDAO.initConnection();
        Response response;



        response = Response
                .status(Response.Status.OK)
                .entity(new LoginDTO("f", "f"))
                .build();

        playlistDAO.closeConnection();
        return response;
    }

    private void setPlaylistDAO(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

}
