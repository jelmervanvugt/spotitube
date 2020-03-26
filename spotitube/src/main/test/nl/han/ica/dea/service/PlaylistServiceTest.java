package nl.han.ica.dea.service;

import nl.han.ica.dea.controller.dto.PlaylistDTO;
import nl.han.ica.dea.controller.dto.PlaylistsDTO;
import nl.han.ica.dea.controller.dto.TracksDTO;
import nl.han.ica.dea.datasource.dao.PlaylistDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;

public class PlaylistServiceTest {

    private PlaylistService sut;
    private PlaylistDAO mockedPlaylistDAO;

    private PlaylistDTO playlist;
    private PlaylistsDTO playlists;

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
        public void testWaarReturnWaardeJuistIs() {
            try {
                var expected = Response.status(Response.Status.OK).entity(playlists).build();
                Mockito.when(mockedPlaylistDAO.getAllPlaylists(token)).thenReturn(playlists);

                var actual = sut.editPlaylistName(token, playlist);

                assertEquals(expected.getStatus(), actual.getStatus());
                assertEquals(expected.getEntity(), actual.getEntity());
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        @Test
        public void testWaarBadRequestExceptionWordtGegooid() {
            try {
                doThrow(new SQLException()).when(mockedPlaylistDAO).editPlaylistName(playlist);
                Response response = sut.editPlaylistName(token, playlist);
            } catch(Exception actual) {
                var expected = new BadRequestException();
                assertEquals(expected.getClass(), actual.getClass());
            }
        }
    }

    @Nested
    public class FnAddPlaylist {
        @Test
        public void testWaarReturnWaardeJuistIs() {
            try {
                var expected = Response.status(Response.Status.OK).entity(playlists).build();
                Mockito.when(mockedPlaylistDAO.getAllPlaylists(token)).thenReturn(playlists);

                var actual = sut.addPlaylist(token, playlist);

                assertEquals(expected.getStatus(), actual.getStatus());
                assertEquals(expected.getEntity(), actual.getEntity());
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        @Test
        public void testWaarBadRequestExceptionWordtGegooid() {
            try {
                doThrow(new SQLException()).when(mockedPlaylistDAO).addPlaylist(token, playlist);
                Response response = sut.addPlaylist(token, playlist);
            } catch(Exception actual) {
                var expected = new BadRequestException();
                assertEquals(expected.getClass(), actual.getClass());
            }
        }
    }

    @Nested
    public class FnDeletePlaylist {
        @Test
        public void testWaarReturnWaardeJuistIs() {
            try {
                var expected = Response.status(Response.Status.OK).entity(playlists).build();
                Mockito.when(mockedPlaylistDAO.getAllPlaylists(token)).thenReturn(playlists);

                var actual = sut.deletePlaylist(token, playlistId);

                assertEquals(expected.getStatus(), actual.getStatus());
                assertEquals(expected.getEntity(), actual.getEntity());
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        @Test
        public void testWaarBadRequestExceptionWordtGegooid() {
            try {
                doThrow(new SQLException()).when(mockedPlaylistDAO).deletePlaylist(playlistId);
                Response response = sut.deletePlaylist(token, playlistId);
            } catch(Exception actual) {
                var expected = new BadRequestException();
                assertEquals(expected.getClass(), actual.getClass());
            }
        }
    }

    @Nested
    public class FnGetAllPlaylists {
        @Test
        public void testWaarReturnWaardeJuistIs() {
            try {
                var expected = Response.status(Response.Status.OK).entity(playlists).build();
                Mockito.when(mockedPlaylistDAO.getAllPlaylists(token)).thenReturn(playlists);

                var actual = sut.getAllPlaylists(token);

                assertEquals(expected.getStatus(), actual.getStatus());
                assertEquals(expected.getEntity(), actual.getEntity());
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        @Test
        public void testWaarBadRequestExceptionWordtGegooid() {
            try {
                doThrow(new SQLException()).when(mockedPlaylistDAO).getAllPlaylists(token);
                Response response = sut.getAllPlaylists(token);
            } catch(Exception actual) {
                var expected = new BadRequestException();
                assertEquals(expected.getClass(), actual.getClass());
            }
        }
    }
}
