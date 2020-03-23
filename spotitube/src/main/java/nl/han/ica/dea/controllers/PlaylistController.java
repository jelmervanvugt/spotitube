package nl.han.ica.dea.controllers;

import nl.han.ica.dea.dao.PlaylistDAO;
import nl.han.ica.dea.dao.TrackDAO;
import nl.han.ica.dea.dto.PlaylistDTO;
import nl.han.ica.dea.dto.TrackDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("playlists")
public class PlaylistController {

    private PlaylistDAO playlistDAO;
    private TrackDAO trackDAO;

    @POST
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId, TrackDTO track) {
        Response response;
        trackDAO.initConnection();
        response = trackDAO.addTrackToPlaylist(token, playlistId, track);
        trackDAO.closeConnection();
        return response;
    }

    @DELETE
    @Path("/{playlistId}/tracks/{trackId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackFromPlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId) {
        Response response;
        trackDAO.initConnection();
        response = trackDAO.deleteTrackFromPlaylist(token, playlistId, trackId);
        trackDAO.closeConnection();
        return response;
    }

    @GET
    @Path("/{forPlaylist}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksFromPlaylist(@PathParam("forPlaylist") int playlistId, @QueryParam("token") String token) {
        Response response;
        trackDAO.initConnection();
        response = trackDAO.getTracksFromPlaylist(playlistId);
        trackDAO.closeConnection();
        return response;
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(PlaylistDTO playlist, @PathParam("id") int id, @QueryParam("token") String token) {
        Response response;
        playlistDAO.initConnection();
        response = playlistDAO.editPlaylistName(token, playlist);
        playlistDAO.closeConnection();
        return response;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(PlaylistDTO playlist, @QueryParam("token") String token) {
        Response response;
        playlistDAO.initConnection();
        response = playlistDAO.addPlaylist(token, playlist);
        playlistDAO.closeConnection();
        return response;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        Response response;
        playlistDAO.initConnection();
        response = playlistDAO.deletePlaylist(token, id);
        playlistDAO.closeConnection();
        return response;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token) {
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

    @Inject
    private void setPlaylistDAO(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    @Inject
    private void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

}
