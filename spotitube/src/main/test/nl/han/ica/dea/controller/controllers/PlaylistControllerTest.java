package nl.han.ica.dea.controller.controllers;

import nl.han.ica.dea.controller.dto.PlaylistDTO;
import nl.han.ica.dea.controller.dto.PlaylistsDTO;
import nl.han.ica.dea.controller.dto.TrackDTO;
import nl.han.ica.dea.controller.dto.TracksDTO;
import nl.han.ica.dea.service.PlaylistService;
import nl.han.ica.dea.service.TrackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaylistControllerTest {

    private PlaylistController sut;
    private PlaylistService mockedPlaylistService;
    private TrackService mockedTrackService;

    private static TracksDTO tracksDTO = new TracksDTO();
    private static TrackDTO trackDTO = new TrackDTO();
    private static PlaylistDTO playlistDTO = new PlaylistDTO();
    private static PlaylistsDTO playlistsDTO = new PlaylistsDTO();

    private static String token = "token";
    private static int playlistId = 1;
    private static int trackId = 1;

    @BeforeEach
    public void setup() {
        sut = new PlaylistController();
        mockedPlaylistService = Mockito.mock(PlaylistService.class);
        mockedTrackService = Mockito.mock(TrackService.class);

        sut.setPlaylistService(mockedPlaylistService);
        sut.setTrackService(mockedTrackService);
    }

    @Nested
    class FnAddTrackToPlaylist {
        @Test
        public void testResponseVanEndpoint() {
            var expected = Response.status(Response.Status.OK).entity(tracksDTO).build();

            Mockito.when(mockedTrackService.addTrackToPlaylist(token, playlistId, trackDTO)).thenReturn(expected);
            var actual = sut.addTrackToPlaylist(token, playlistId, trackDTO);

            assertEquals(actual.getStatus(), expected.getStatus());
            assertEquals(actual.getEntity(), expected.getEntity());
        }
    }

    @Nested
    class FnDeleteTrackFromPlaylist {
        @Test
        public void testResponseVanEndpoint() {
            var expected = Response.status(Response.Status.OK).entity(tracksDTO).build();

            Mockito.when(mockedTrackService.deleteTrackFromPlaylist(token, playlistId, trackId)).thenReturn(expected);
            var actual = sut.deleteTrackFromPlaylist(token, playlistId, trackId);

            assertEquals(actual.getStatus(), expected.getStatus());
            assertEquals(actual.getEntity(), expected.getEntity());
        }
    }

    @Nested
    class FnGetTracksFromPlaylist {
        @Test
        public void testResponseVanEndpoint() {
            var expected = Response.status(Response.Status.OK).entity(tracksDTO).build();

            Mockito.when(mockedTrackService.getTracksFromPlaylist(playlistId)).thenReturn(expected);
            var actual = sut.getTracksFromPlaylist(playlistId, token);

            assertEquals(actual.getStatus(), expected.getStatus());
            assertEquals(actual.getEntity(), expected.getEntity());
        }
    }

    @Nested
    class FnEditPlaylist {
        @Test
        public void testResponseVanEndpoint() {
            var expected = Response.status(Response.Status.OK).entity(playlistsDTO).build();

            Mockito.when(mockedPlaylistService.editPlaylistName(token, playlistDTO)).thenReturn(expected);
            var actual = sut.editPlaylist(playlistDTO, playlistId, token);

            assertEquals(actual.getStatus(), expected.getStatus());
            assertEquals(actual.getEntity(), expected.getEntity());
        }
    }

    @Nested
    class FnAddPlaylist {
        @Test
        public void testResponseVanEndpoint() {
            var expected = Response.status(Response.Status.OK).entity(playlistsDTO).build();

            Mockito.when(mockedPlaylistService.addPlaylist(token, playlistDTO)).thenReturn(expected);
            var actual = sut.addPlaylist(playlistDTO, token);

            assertEquals(actual.getStatus(), expected.getStatus());
            assertEquals(actual.getEntity(), expected.getEntity());
        }
    }

    @Nested
    class FnDeletePlaylist {
        @Test
        public void testResponseVanEndpoint() {
            var expected = Response.status(Response.Status.OK).entity(playlistsDTO).build();

            Mockito.when(mockedPlaylistService.deletePlaylist(token, playlistId)).thenReturn(expected);
            var actual = sut.deletePlaylist(playlistId, token);

            assertEquals(actual.getStatus(), expected.getStatus());
            assertEquals(actual.getEntity(), expected.getEntity());
        }
    }

    @Nested
    class FnGetAllPlaylists {
        @Test
        public void testResponseVanEndpoint() {
            var expected = Response.status(Response.Status.OK).entity(playlistsDTO).build();

            Mockito.when(mockedPlaylistService.getAllPlaylists(token)).thenReturn(expected);
            var actual = sut.getAllPlaylists(token);

            assertEquals(actual.getStatus(), expected.getStatus());
            assertEquals(actual.getEntity(), expected.getEntity());
        }
    }

}
