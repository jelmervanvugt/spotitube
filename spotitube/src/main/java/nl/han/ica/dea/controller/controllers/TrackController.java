package nl.han.ica.dea.controller.controllers;

import nl.han.ica.dea.datasource.dao.TrackDAO;

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
    public Response getTracksNotInPlaylist(@QueryParam("token") String token, @QueryParam("forPlaylist") int playlistId) {
        try {
            return Response
                    .status(Response.Status.OK)
                    .entity(trackDAO.getAllTracksNotInPlaylist(playlistId))
                    .build();
        } catch(SQLException e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
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
