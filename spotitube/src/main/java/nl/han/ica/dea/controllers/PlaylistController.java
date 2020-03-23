package nl.han.ica.dea.controllers;

import nl.han.ica.dea.dto.PlaylistDTO;
import nl.han.ica.dea.dto.TrackDTO;
import nl.han.ica.dea.services.PlaylistService;
import nl.han.ica.dea.services.TrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("playlists")
public class PlaylistController {

    private PlaylistService playlistService;
    private TrackService trackService;

    @POST
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId, TrackDTO track)
    { return trackService.addTrackToPlaylist(token, playlistId, track); }

    @DELETE
    @Path("/{playlistId}/tracks/{trackId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackFromPlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId)
    { return trackService.deleteTrackFromPlaylist(token, playlistId, trackId); }

    @GET
    @Path("/{forPlaylist}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksFromPlaylist(@PathParam("forPlaylist") int playlistId, @QueryParam("token") String token)
    { return trackService.getTracksFromPlaylist(playlistId); }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(PlaylistDTO playlist, @PathParam("id") int id, @QueryParam("token") String token)
    { return playlistService.editPlaylistName(token, playlist); }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(PlaylistDTO playlist, @QueryParam("token") String token)
    { return playlistService.addPlaylist(token, playlist); }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") int id, @QueryParam("token") String token)
    { return playlistService.deletePlaylist(token, id); }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token)
    { return playlistService.getAllPlaylists(token); }

    @Inject
    private void setPlaylistService(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Inject
    private void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }

}
