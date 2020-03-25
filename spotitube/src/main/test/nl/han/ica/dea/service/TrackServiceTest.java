//package nl.han.ica.dea.service;
//
//import nl.han.ica.dea.controller.dto.TrackDTO;
//import nl.han.ica.dea.controller.dto.TracksDTO;
//import nl.han.ica.dea.datasource.dao.TrackDAO;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import javax.ws.rs.core.Response;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class TrackServiceTest {
//
//    private TrackService sut;
//    private TrackDAO mockedTrackDAO;
//
//    private int playlistId = 1;
//    private int trackId = 1;
//    private String token = "token";
//
//    @BeforeEach
//    public void setup() {
//        sut = new TrackService();
//        mockedTrackDAO = Mockito.mock(TrackDAO.class);
//        sut.setTrackDAO(mockedTrackDAO);
//    }
//
//    @Nested
//    public class FnGetTracksNotInPlaylist {
//        @Test
//        public void testReturnWaardeFunctie() {
//            var tracks = new TracksDTO();
//            var expected = Response.status(Response.Status.OK).entity(tracks).build();
//            Mockito.when(mockedTrackDAO.getTracksNotInPlaylist(playlistId)).thenReturn(expected);
//
//            var actual = sut.getTracksNotInPlaylist(playlistId);
//
//            assertEquals(expected.getStatus(), actual.getStatus());
//            assertEquals(expected.getEntity(), actual.getEntity());
//        }
//    }
//
//    @Nested
//    public class FnAddTrackToPlaylist {
//        @Test
//        public void testReturnWaardeFunctie() {
//            var tracks = new TracksDTO();
//            var track = new TrackDTO();
//            var expected = Response.status(Response.Status.OK).entity(tracks).build();
//            Mockito.when(mockedTrackDAO.addTrackToPlaylist(token, playlistId, track)).thenReturn(expected);
//
//            var actual = sut.addTrackToPlaylist(token, playlistId, track);
//
//            assertEquals(expected.getStatus(), actual.getStatus());
//            assertEquals(expected.getEntity(), actual.getEntity());
//        }
//    }
//
//    @Nested
//    public class FnDeleteTrackFromPlaylist {
//        @Test
//        public void testReturnWaardeFunctie() {
//            var tracks = new TracksDTO();
//            var expected = Response.status(Response.Status.OK).entity(tracks).build();
//            Mockito.when(mockedTrackDAO.deleteTrackFromPlaylist(token, playlistId, trackId)).thenReturn(expected);
//
//            var actual = sut.deleteTrackFromPlaylist(token, playlistId, trackId);
//
//            assertEquals(expected.getStatus(), actual.getStatus());
//            assertEquals(expected.getEntity(), actual.getEntity());
//        }
//    }
//
//    @Nested
//    public class FnGetTracksFromPlaylist {
//        @Test
//        public void testReturnWaardeFunctie() {
//            var tracks = new TracksDTO();
//            var expected = Response.status(Response.Status.OK).entity(tracks).build();
//            Mockito.when(mockedTrackDAO.getTracksFromPlaylist(playlistId)).thenReturn(expected);
//
//            var actual = sut.getTracksFromPlaylist(playlistId);
//
//            assertEquals(expected.getStatus(), actual.getStatus());
//            assertEquals(expected.getEntity(), actual.getEntity());
//        }
//    }
//}
