package nl.han.ica.dea.controllers;

import nl.han.ica.dea.dao.TrackDAO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/tracks")
public class TrackController {

    private TrackDAO trackDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksFromPlaylist(@QueryParam("token") String token, @QueryParam("forPlaylist") int playlistId) {
        Response response;
        trackDAO.initConnection();
        response = trackDAO.getTracksNotInPlaylist(playlistId);
        trackDAO.closeConnection();
        return response;
    }

    @Inject
    private void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

}
