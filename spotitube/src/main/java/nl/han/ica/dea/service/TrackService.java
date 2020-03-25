package nl.han.ica.dea.service;

import nl.han.ica.dea.datasource.dao.TrackDAO;
import nl.han.ica.dea.controller.dto.TrackDTO;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

public class TrackService {

    private TrackDAO trackDAO;

    public Response getTracksNotInPlaylist(int playlistId) {
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

    public Response addTrackToPlaylist(String token, int playlistId, TrackDTO track) {
        try {
            trackDAO.addTrackToPlaylist(playlistId, track);
            return Response
                    .status(Response.Status.OK)
                    .entity(trackDAO.getAllTracksFromPlaylist(playlistId))
                    .build();
        } catch(SQLException e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
    }

    public Response deleteTrackFromPlaylist(String token, int playlistId, int trackId) {
        try {
            trackDAO.deleteTrackFromPlaylist(playlistId, trackId);
            return Response
                    .status(Response.Status.OK)
                    .entity(trackDAO.getAllTracksFromPlaylist(playlistId))
                    .build();
        } catch(SQLException e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
    }

    public Response getTracksFromPlaylist(int playlistId) {
        try {
            return Response
                    .status(Response.Status.OK)
                    .entity(trackDAO.getAllTracksFromPlaylist(playlistId))
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

}
