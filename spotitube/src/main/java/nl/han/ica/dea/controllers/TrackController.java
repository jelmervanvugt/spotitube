package nl.han.ica.dea.controllers;

import nl.han.ica.dea.services.TrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackController {

    private TrackService trackService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksFromPlaylist(@QueryParam("token") String token, @QueryParam("forPlaylist") int playlistId) {
        return trackService.getTracksNotInPlaylist(playlistId);
    }

    @Inject
    private void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }

}
