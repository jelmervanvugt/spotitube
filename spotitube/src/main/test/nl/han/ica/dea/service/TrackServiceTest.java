package nl.han.ica.dea.service;

import nl.han.ica.dea.controller.dto.TrackDTO;
import nl.han.ica.dea.controller.dto.TracksDTO;
import nl.han.ica.dea.datasource.dao.TrackDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;

public class TrackServiceTest {

    private TrackService sut;
    private TrackDAO mockedTrackDAO;

    private int playlistId = 1;
    private int trackId = 1;
    private String token = "token";
    private TrackDTO track = new TrackDTO();

    @BeforeEach
    public void setup() {
        sut = new TrackService();
        mockedTrackDAO = Mockito.mock(TrackDAO.class);
        sut.setTrackDAO(mockedTrackDAO);
    }

    @Nested
   public class FnGetTracksNotInPlaylist {
        @Test
        public void testWaarReturnWaardeJuistIs() {
            try {
                var tracks = new TracksDTO();
                var expected = Response.status(Response.Status.OK).entity(tracks).build();
                Mockito.when(mockedTrackDAO.getAllTracksNotInPlaylist(playlistId)).thenReturn(tracks);

                var actual = sut.getTracksNotInPlaylist(playlistId);

                assertEquals(expected.getStatus(), actual.getStatus());
                assertEquals(expected.getEntity(), actual.getEntity());
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }

        @Test
        public void testWaarBadRequestExceptionWordtGegooid() {
            try {
                var tracks = new TracksDTO();
                Mockito.when(mockedTrackDAO.getAllTracksNotInPlaylist(playlistId)).thenThrow(new BadRequestException());

               sut.getTracksNotInPlaylist(playlistId);

            } catch(Exception actual) {
                var expected = new BadRequestException();
                assertEquals(expected.getClass(), actual.getClass());
            }
        }
   }

   @Nested
    public class FnAddTrackPlaylist {
        @Test
        public void testWaarReturnWaardeJuistIs() {
            try {
                var tracks = new TracksDTO();
                var expected = Response.status(Response.Status.OK).entity(tracks).build();
                Mockito.when(mockedTrackDAO.getAllTracksFromPlaylist(playlistId)).thenReturn(tracks);

                var actual = sut.addTrackToPlaylist(token, playlistId, track);

                assertEquals(expected.getStatus(), actual.getStatus());
                assertEquals(expected.getEntity(), actual.getEntity());
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        @Test
        public void testWaarBadRequestExceptionWordtGegooid() {
            try {
                doThrow(new SQLException()).when(mockedTrackDAO).addTrackToPlaylist(playlistId, track);
                Response response = sut.addTrackToPlaylist(token, playlistId, track);
            } catch(Exception actual) {
                var expected = new BadRequestException();
                assertEquals(expected.getClass(), actual.getClass());
            }
        }
    }

    @Nested
    public class FnDeleteTrackFromPlaylist {
        @Test
        public void testWaarReturnWaardeJuistIs() {
            try {
                var tracks = new TracksDTO();
                var expected = Response.status(Response.Status.OK).entity(tracks).build();
                Mockito.when(mockedTrackDAO.getAllTracksFromPlaylist(playlistId)).thenReturn(tracks);

                var actual = sut.deleteTrackFromPlaylist(token, playlistId, trackId);

                assertEquals(expected.getStatus(), actual.getStatus());
                assertEquals(expected.getEntity(), actual.getEntity());
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        @Test
        public void testWaarBadRequestExceptionWordtGegooid() {
            try {
                doThrow(new SQLException()).when(mockedTrackDAO).deleteTrackFromPlaylist(playlistId, trackId);
                Response response = sut.deleteTrackFromPlaylist(token, playlistId, trackId);
            } catch(Exception actual) {
                var expected = new BadRequestException();
                assertEquals(expected.getClass(), actual.getClass());
            }
        }
    }

    @Nested
    public class FnGetTracksFromPlaylist {
        @Test
        public void testWaarReturnWaardeJuistIs() {
            try {
                var tracks = new TracksDTO();
                var expected = Response.status(Response.Status.OK).entity(tracks).build();
                Mockito.when(mockedTrackDAO.getAllTracksFromPlaylist(playlistId)).thenReturn(tracks);

                var actual = sut.getTracksFromPlaylist(playlistId);

                assertEquals(expected.getStatus(), actual.getStatus());
                assertEquals(expected.getEntity(), actual.getEntity());
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        @Test
        public void testWaarBadRequestExceptionWordtGegooid() {
            try {
                Mockito.when(mockedTrackDAO.getAllTracksFromPlaylist(playlistId)).thenThrow(new BadRequestException());
                sut.getTracksFromPlaylist(playlistId);
            } catch(Exception actual) {
                var expected = new BadRequestException();
                assertEquals(expected.getClass(), actual.getClass());
            }
        }
    }
}
