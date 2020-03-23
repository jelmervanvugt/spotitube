package nl.han.ica.dea.services;

import nl.han.ica.dea.dao.TrackDAO;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

public class TrackService {

    private TrackDAO trackDAO;

    public Response getTracksNotInPlaylist(int playlistId) {
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
