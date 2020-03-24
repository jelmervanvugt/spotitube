package nl.han.ica.dea.controller.controllers;

import nl.han.ica.dea.controller.dto.TracksDTO;
import nl.han.ica.dea.service.TrackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrackControllerTest {

    private TrackController sut;
    private TrackService mockedTrackService;

    private TracksDTO tracksDTO = new TracksDTO();

    private String token = "token";
    private int playlistId = 1;

    @BeforeEach
    public void setup() {
        sut = new TrackController();
        mockedTrackService = Mockito.mock(TrackService.class);

        sut.setTrackService(mockedTrackService);
    }

    @Nested
    public class FnGetTracksFromPlaylist {
        @Test
        public void testResponseVanEndpoint() {
            var expected = Response.status(Response.Status.OK).entity(tracksDTO).build();

            Mockito.when(mockedTrackService.getTracksNotInPlaylist(playlistId)).thenReturn(expected);
            var actual = sut.getTracksFromPlaylist(token, playlistId);

            assertEquals(actual.getStatus(), expected.getStatus());
            assertEquals(actual.getEntity(), expected.getEntity());
        }
    }

}
