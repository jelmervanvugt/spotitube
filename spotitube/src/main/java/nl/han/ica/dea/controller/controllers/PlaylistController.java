package nl.han.ica.dea.controller.controllers;

import nl.han.ica.dea.controller.dto.PlaylistDTO;
import nl.han.ica.dea.controller.dto.TrackDTO;
import nl.han.ica.dea.datasource.dao.PlaylistDAO;
import nl.han.ica.dea.datasource.dao.TrackDAO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("playlists")
public class PlaylistController {

    private PlaylistDAO playlistDAO;
    private TrackDAO trackDAO;

    @POST
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId, TrackDTO track) {
        try {
            trackDAO.addTrackToPlaylist(playlistId, track);
            return Response
                    .status(Response.Status.OK)
                    .entity(trackDAO.getAllTracksFromPlaylist(playlistId))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
    }

    @DELETE
    @Path("/{playlistId}/tracks/{trackId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackFromPlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId) {
        try {
            trackDAO.deleteTrackFromPlaylist(playlistId, trackId);
            return Response
                    .status(Response.Status.OK)
                    .entity(trackDAO.getAllTracksFromPlaylist(playlistId))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
    }

    @GET
    @Path("/{forPlaylist}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksFromPlaylist(@PathParam("forPlaylist") int playlistId, @QueryParam("token") String token) {
        try {
            return Response
                    .status(Response.Status.OK)
                    .entity(trackDAO.getAllTracksFromPlaylist(playlistId))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(PlaylistDTO playlist, @PathParam("id") int id, @QueryParam("token") String token) {
        try {
            playlistDAO.editPlaylistName(playlist);
            return Response
                    .status(Response.Status.OK)
                    .entity(playlistDAO.getAllPlaylists(token))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(PlaylistDTO playlist, @QueryParam("token") String token) {
        try {
            playlistDAO.addPlaylist(token, playlist);
            return Response
                    .status(Response.Status.OK)
                    .entity(playlistDAO.getAllPlaylists(token))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        try {
            playlistDAO.deletePlaylist(id);
            return Response
                    .status(Response.Status.OK)
                    .entity(playlistDAO.getAllPlaylists(token))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token) {
        try {
            return Response
                    .status(Response.Status.OK)
                    .entity(playlistDAO.getAllPlaylists(token))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
    }

    @Inject
    public void setPlaylistDAO(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    // Puur voor test doeleinden.
    public PlaylistDAO getPlaylistDAO() {
        return playlistDAO;
    }

    @Inject
    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    // Puur voor test doeleinden.
    public TrackDAO getTrackDAO() {
        return trackDAO;
    }
}
