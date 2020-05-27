package nl.han.ica.dea.controller.controllers;

import nl.han.ica.dea.controller.dto.PlaylistDTO;
import nl.han.ica.dea.controller.dto.PlaylistsDTOTest;
import nl.han.ica.dea.controller.dto.TrackDTO;
import nl.han.ica.dea.controller.dto.TracksDTO;
import nl.han.ica.dea.datasource.dao.PlaylistDAO;
import nl.han.ica.dea.datasource.dao.TrackDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class PlaylistControllerTest {

    private PlaylistController sut;
    private PlaylistDAO mockedPlaylistDAO;
    private TrackDAO mockedTrackDAO;

    private static TracksDTO tracksDTO = new TracksDTO();
    private static TrackDTO trackDTO = new TrackDTO();
    private static PlaylistDTO playlistDTO = new PlaylistDTO();
    private static PlaylistsDTOTest playlistsDTOTest = new PlaylistsDTOTest();

    private static String token = "token";
    private static int playlistId = 1;
    private static int trackId = 1;

    @BeforeEach
    public void setup() {
        sut = new PlaylistController();
        mockedPlaylistDAO = Mockito.mock(PlaylistDAO.class);
        mockedTrackDAO = Mockito.mock(TrackDAO.class);

        sut.setPlaylistDAO(mockedPlaylistDAO);
        sut.setTrackDAO(mockedTrackDAO);
    }

    @Test
    public void test1_setPlaylistDAO_works() {
        var expected = mockedPlaylistDAO;
        sut.setPlaylistDAO(expected);

        var result = sut.getPlaylistDAO();

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test2_setTrackDAO_works() {
        var expected = mockedTrackDAO;
        sut.setTrackDAO(expected);

        var result = sut.getTrackDAO();

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test3_addTrackToPlaylist_calls_DAO() {
        try {
            var nTimesCalled = 1;

            sut.addTrackToPlaylist(token, playlistId, trackDTO);

            verify(mockedTrackDAO, times(nTimesCalled)).addTrackToPlaylist(playlistId, trackDTO);
            verify(mockedTrackDAO, times(nTimesCalled)).getAllTracksFromPlaylist(playlistId);

        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void test4_deleteTrackFromPlaylist_calls_DAO() {
        try {
            var nTimesCalled = 1;

            sut.deleteTrackFromPlaylist(token, playlistId, trackId);

            verify(mockedTrackDAO, times(nTimesCalled)).deleteTrackFromPlaylist(playlistId, trackId);
            verify(mockedTrackDAO, times(nTimesCalled)).getAllTracksFromPlaylist(playlistId);

        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void test5_getTracksFromPlaylist_calls_DAO() {
        try {
            var nTimesCalled = 1;

            sut.getTracksFromPlaylist(playlistId, token);

            verify(mockedTrackDAO, times(nTimesCalled)).getAllTracksFromPlaylist(playlistId);

        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void test6_editPlaylist_calls_DAO() {
        try {
            var nTimesCalled = 1;

            sut.editPlaylist(playlistDTO, playlistId, token);

            verify(mockedPlaylistDAO, times(nTimesCalled)).editPlaylistName(playlistDTO);
            verify(mockedPlaylistDAO, times(nTimesCalled)).getAllPlaylists(token);

        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void test7_addPlaylist_calls_DAO() {
        try {
            var nTimesCalled = 1;

            sut.addPlaylist(playlistDTO, token);

            verify(mockedPlaylistDAO, times(nTimesCalled)).addPlaylist(token, playlistDTO);
            verify(mockedPlaylistDAO, times(nTimesCalled)).getAllPlaylists(token);

        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void test8_deletePlaylist_calls_DAO() {
        try {
            var nTimesCalled = 1;

            sut.deletePlaylist(playlistId, token);

            verify(mockedPlaylistDAO, times(nTimesCalled)).deletePlaylist(playlistId);
            verify(mockedPlaylistDAO, times(nTimesCalled)).getAllPlaylists(token);

        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void test9_getAllPlaylists_calls_DAO() {
        try {
            var nTimesCalled = 1;

            sut.getAllPlaylists(token);

            verify(mockedPlaylistDAO, times(nTimesCalled)).getAllPlaylists(token);

        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void test10_addTrackToPlaylist_returnsOK() {
        try {
            var expected = Response.Status.OK;

            var result = sut.addTrackToPlaylist(token, playlistId, trackDTO);

            Assertions.assertEquals(expected.getStatusCode(), result.getStatus());
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void test11_addTrackToPlaylist_returns_BadRequestException() {
        try {
            var expected = new BadRequestException();
            doThrow(BadRequestException.class).when(mockedTrackDAO).addTrackToPlaylist(playlistId, trackDTO);

            Assertions.assertThrows(BadRequestException.class, () -> sut.addTrackToPlaylist(token, playlistId, trackDTO));
        }  catch(Exception e) {
            fail();
        }
    }

    @Test
    public void test12_deleteTrackFromPlaylist_returnsOK() {
        try {
            var expected = Response.Status.OK;

            var result = sut.deleteTrackFromPlaylist(token, playlistId, trackId);

            Assertions.assertEquals(expected.getStatusCode(), result.getStatus());
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void test13_deleteTrackFromPlaylist_returns_BadRequestException() {
        try {
            var expected = new BadRequestException();
            doThrow(BadRequestException.class).when(mockedTrackDAO).deleteTrackFromPlaylist(playlistId, trackId);

            Assertions.assertThrows(BadRequestException.class, () -> sut.deleteTrackFromPlaylist(token, playlistId, trackId));
        }  catch(Exception e) {
            fail();
        }
    }

    @Test
    public void test14_getTracksFromPlaylist_returnsOK() {
        try {
            var expected = Response.Status.OK;

            var result = sut.getTracksFromPlaylist(playlistId, token);

            Assertions.assertEquals(expected.getStatusCode(), result.getStatus());
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void test15_getTracksFromPlaylist_returns_BadRequestException() {
        try {
            var expected = new BadRequestException();
            doThrow(BadRequestException.class).when(mockedTrackDAO).getAllTracksFromPlaylist(playlistId);

            Assertions.assertThrows(BadRequestException.class, () -> sut.getTracksFromPlaylist(playlistId, token));
        }  catch(Exception e) {
            fail();
        }
    }

    @Test
    public void test16_editPlaylist_returnsOK() {
        try {
            var expected = Response.Status.OK;

            var result = sut.editPlaylist(playlistDTO, playlistId, token);

            Assertions.assertEquals(expected.getStatusCode(), result.getStatus());
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void test17_editPlaylist_returns_BadRequestException() {
        try {
            var expected = new BadRequestException();
            doThrow(BadRequestException.class).when(mockedPlaylistDAO).editPlaylistName(playlistDTO);

            Assertions.assertThrows(BadRequestException.class, () -> sut.editPlaylist(playlistDTO, playlistId, token));
        }  catch(Exception e) {
            fail();
        }
    }

    @Test
    public void test18_addPlaylist_returnsOK() {
        try {
            var expected = Response.Status.OK;

            var result = sut.addPlaylist(playlistDTO, token);

            Assertions.assertEquals(expected.getStatusCode(), result.getStatus());
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void test19_addPlaylist_returns_BadRequestException() {
        try {
            var expected = new BadRequestException();
            doThrow(BadRequestException.class).when(mockedPlaylistDAO).addPlaylist(token, playlistDTO);

            Assertions.assertThrows(BadRequestException.class, () -> sut.addPlaylist(playlistDTO, token));
        }  catch(Exception e) {
            fail();
        }
    }

    @Test
    public void test20_deletePlaylist_returnsOK() {
        try {
            var expected = Response.Status.OK;

            var result = sut.deletePlaylist(playlistId, token);

            Assertions.assertEquals(expected.getStatusCode(), result.getStatus());
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void test21_deletePlaylist_returns_BadRequestException() {
        try {
            var expected = new BadRequestException();
            doThrow(BadRequestException.class).when(mockedPlaylistDAO).deletePlaylist(playlistId);

            Assertions.assertThrows(BadRequestException.class, () -> sut.deletePlaylist(playlistId, token));
        }  catch(Exception e) {
            fail();
        }
    }

    @Test
    public void test22_deletePlaylist_returnsOK() {
        try {
            var expected = Response.Status.OK;

            var result = sut.getAllPlaylists(token);

            Assertions.assertEquals(expected.getStatusCode(), result.getStatus());
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void test23_deletePlaylist_returns_BadRequestException() {
        try {
            var expected = new BadRequestException();
            doThrow(BadRequestException.class).when(mockedPlaylistDAO).getAllPlaylists(token);

            Assertions.assertThrows(BadRequestException.class, () -> sut.getAllPlaylists(token));
        }  catch(Exception e) {
            fail();
        }
    }
}
