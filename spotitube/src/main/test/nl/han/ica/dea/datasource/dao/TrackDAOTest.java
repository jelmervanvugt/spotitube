package nl.han.ica.dea.datasource.dao;

import nl.han.ica.dea.controller.dto.TracksDTO;
import nl.han.ica.dea.datasource.datamappers.TracksDataMapper;
import nl.han.ica.dea.datasource.DatabaseConnection.ConnectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrackDAOTest {

    private TrackDAO sut;
    private TracksDataMapper mockedTracksDataMapper;
    private ConnectionService mockedConnectionService;

    private int playlistId = 1;

    @BeforeEach
    public void setup() {
        sut = new TrackDAO();
        mockedTracksDataMapper = Mockito.mock(TracksDataMapper.class);
        mockedConnectionService = Mockito.mock(ConnectionService.class);
        sut.setTracksDataMapper(mockedTracksDataMapper);
        sut.setConnectionService(mockedConnectionService);
    }

    @Nested
    public class FnGetAllTracksFromPlaylist {
        @Test
        public void testWaarFunctieJuisteReturnWaardeHeeft() {
            try {
                var expected = new TracksDTO();

                var actual = sut.getAllTracksFromPlaylist(playlistId);

                assertEquals(expected, actual);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Nested
    public class FnGetAllTracksNotInPlaylist {
        @Test
        public void testWaarFunctieJuisteReturnWaardeHeeft() {
            try {
                var expected = new TracksDTO();

                var actual = sut.getAllTracksNotInPlaylist(playlistId);

                assertEquals(expected, actual);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
