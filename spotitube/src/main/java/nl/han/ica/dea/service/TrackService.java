package nl.han.ica.dea.service;

import nl.han.ica.dea.datasource.dao.TrackDAO;
import nl.han.ica.dea.controller.dto.TrackDTO;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

public class TrackService {

    private TrackDAO trackDAO;

    public Response getTracksNotInPlaylist(int playlistId)
    { return trackDAO.getTracksNotInPlaylist(playlistId); }

    public Response addTrackToPlaylist(String token, int playlistId, TrackDTO track)
    { return trackDAO.addTrackToPlaylist(token, playlistId, track); }

    public Response deleteTrackFromPlaylist(String token, int playlistId, int trackId)
    { return trackDAO.deleteTrackFromPlaylist(token, playlistId, trackId); }

    public Response getTracksFromPlaylist(int playlistId)
    { return trackDAO.getTracksFromPlaylist(playlistId); }

    @Inject
    private void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

}
