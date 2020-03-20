package nl.han.ica.dea.controllers;

import nl.han.ica.dea.dao.PlaylistsDAO;
import nl.han.ica.dea.dto.PlaylistDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("playlists")
public class PlaylistController {

    private PlaylistsDAO playlistsDAO;

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(PlaylistDTO playlist, @PathParam("id") int id, @QueryParam("token") String token) {
       Response response = null;
       playlistsDAO.initConnection();
       try {
           return playlistsDAO.editPlaylistName(token, playlist);
       } catch(SQLException e) {
          e.printStackTrace();
       }
       playlistsDAO.closeConnection();
       return response;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(PlaylistDTO playlist, @QueryParam("token") String token) {
        Response response = null;
        playlistsDAO.initConnection();
        try {
            playlistsDAO.addPlaylist(token, playlist);
        } catch(SQLException e) {
            response = Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
            e.printStackTrace();
        }
        playlistsDAO.closeConnection();
        return response;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        Response response = null;
        playlistsDAO.initConnection();
        try {
            return playlistsDAO.deletePlaylist(token, id);
        } catch (SQLException e) {
            response = Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
            e.printStackTrace();
        }
        playlistsDAO.closeConnection();
        return response;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token) {
        Response response = null;
        playlistsDAO.initConnection();
        try {
            response = Response
                    .status(Response.Status.OK)
                    .entity(playlistsDAO.getAllPlaylists(token))
                    .build();
        } catch(SQLException e) {
            response = Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
            e.printStackTrace();
        }
        playlistsDAO.closeConnection();
        return response;
    }

    @Inject
    private void setPlaylistsDAO(PlaylistsDAO playlistsDAO) {
        this.playlistsDAO = playlistsDAO;
    }

}
