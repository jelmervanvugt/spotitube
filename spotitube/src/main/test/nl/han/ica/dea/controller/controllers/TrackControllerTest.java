package nl.han.ica.dea.controller.controllers;

import nl.han.ica.dea.controller.dto.TracksDTO;
import nl.han.ica.dea.datasource.dao.TrackDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;


public class TrackControllerTest {

    private TrackController sut;
    private TrackDAO mockedTrackDAO;

    private TracksDTO tracksDTO = new TracksDTO();

    private String token = "token";
    private int playlistId = 1;

    @BeforeEach
    public void setup() {
        sut = new TrackController();
        mockedTrackDAO = Mockito.mock(TrackDAO.class);

        sut.setTrackDAO(mockedTrackDAO);
    }

    @Test
    public void test1_trackDAO_setterWorks() {
        var expected = mockedTrackDAO;
        sut.setTrackDAO(expected);

        var result = sut.getTrackDAO();

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test2_getTracksNotInPlaylist_calls_DAO() {
        try {
            var nTimesCalled = 1;

            sut.getTracksNotInPlaylist(token, playlistId);

            verify(mockedTrackDAO, times(nTimesCalled)).getAllTracksNotInPlaylist(playlistId);

        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void test3_getTracksNotInPlaylist_returnsOK() {
        try {
            var expected = Response.Status.OK;

            var result = sut.getTracksNotInPlaylist(token, playlistId);

            Assertions.assertEquals(expected.getStatusCode(), result.getStatus());
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void test4_getTracksNotInPlaylist_returns_BadRequestException() {
        try {
            var expected = new BadRequestException();
            doThrow(BadRequestException.class).when(mockedTrackDAO).getAllTracksNotInPlaylist(playlistId);

            Assertions.assertThrows(BadRequestException.class, () -> sut.getTracksNotInPlaylist(token, playlistId));
        }  catch(Exception e) {
            fail();
        }
    }

}
