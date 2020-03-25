package nl.han.ica.dea.service;

import nl.han.ica.dea.controller.dto.PlaylistDTO;
import nl.han.ica.dea.controller.dto.PlaylistsDTO;
import nl.han.ica.dea.datasource.dao.PlaylistDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaylistServiceTest {

    private PlaylistService sut;
    private PlaylistDAO mockedPlaylistDAO;

    private PlaylistDTO playlist;
    private ArrayList<PlaylistDTO> playlists;

    private String token = "token";
    private int length = 10;
    private int playlistId = 1;

    @BeforeEach
    public void setup() {
        sut = new PlaylistService();
        mockedPlaylistDAO = Mockito.mock(PlaylistDAO.class);
        sut.setPlaylistDAO(mockedPlaylistDAO);
    }

    @Nested
    public class FnEditPlaylistName {
        @Test
        public void testReturnWaardeFunctie() {
            var playlistsDTO = new PlaylistsDTO(playlists, length);
            var expected = Response.status(Response.Status.OK).entity(playlistsDTO).build();
            Mockito.when(mockedPlaylistDAO.editPlaylistName(token, playlist)).thenReturn(expected);

            var actual = sut.editPlaylistName(token, playlist);

            assertEquals(expected.getStatus(), actual.getStatus());
            assertEquals(expected.getEntity(), actual.getEntity());
        }
    }

    @Nested
    public class FnAddPlaylistName {
        @Test
        public void testReturnWaardeFunctie() {
            var playlistsDTO = new PlaylistsDTO(playlists, length);
            var expected = Response.status(Response.Status.OK).entity(playlistsDTO).build();
            Mockito.when(mockedPlaylistDAO.addPlaylist(token, playlist)).thenReturn(expected);

            var actual = sut.addPlaylist(token, playlist);

            assertEquals(expected.getStatus(), actual.getStatus());
            assertEquals(expected.getEntity(), actual.getEntity());
        }
    }

    @Nested
    public class FnDeletePlaylist {
        @Test
        public void testReturnWaardeFunctie() {
            var playlistsDTO = new PlaylistsDTO(playlists, length);
            var expected = Response.status(Response.Status.OK).entity(playlistsDTO).build();
            Mockito.when(mockedPlaylistDAO.deletePlaylist(token, playlistId)).thenReturn(expected);

            var actual = sut.deletePlaylist(token, playlistId);

            assertEquals(expected.getStatus(), actual.getStatus());
            assertEquals(expected.getEntity(), actual.getEntity());
        }
    }

    @Nested
    public class FnGetAllPlaylists {
        @Test
        public void testReturnWaardeFunctie() {
            var playlistsDTO = new PlaylistsDTO(playlists, length);
            var expected = Response.status(Response.Status.OK).entity(playlistsDTO).build();
            Mockito.when(mockedPlaylistDAO.getAllPlaylists(token)).thenReturn(expected);

            var actual = sut.getAllPlaylists(token);

            assertEquals(expected.getStatus(), actual.getStatus());
            assertEquals(expected.getEntity(), actual.getEntity());
        }
    }
}
