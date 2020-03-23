package nl.han.ica.dea.services;

import nl.han.ica.dea.dao.TrackDAO;
import nl.han.ica.dea.dto.TrackDTO;

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

    public Response addTrackToPlaylist(String token, int playlistId, TrackDTO track) {
        Response response;
        trackDAO.initConnection();
        response = trackDAO.addTrackToPlaylist(token, playlistId, track);
        trackDAO.closeConnection();
        return response;
    }

    public Response deleteTrackFromPlaylist(String token, int playlistId, int trackId) {
        Response response;
        trackDAO.initConnection();
        response = trackDAO.deleteTrackFromPlaylist(token, playlistId, trackId);
        trackDAO.closeConnection();
        return response;
    }

    public Response getTracksFromPlaylist(int playlistId) {
        Response response;
        trackDAO.initConnection();
        response = trackDAO.getTracksFromPlaylist(playlistId);
        trackDAO.closeConnection();
        return response;
    }

    @Inject
    private void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

}
