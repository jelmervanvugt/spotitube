package nl.han.ica.dea.datasource.dao;

import nl.han.ica.dea.controller.dto.PlaylistsDTO;
import nl.han.ica.dea.datasource.datamappers.PlaylistsDataMapper;
import nl.han.ica.dea.service.ConnectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaylistDAOTest {

    private PlaylistDAO sut;
    private ConnectionService mockedConnectionService;
    private PlaylistsDataMapper mockedPlaylistsDataMapper;

    private String token = "token";

    @BeforeEach
    public void setup() {
        sut = new PlaylistDAO();
        mockedConnectionService = Mockito.mock(ConnectionService.class);
        mockedPlaylistsDataMapper = Mockito.mock(PlaylistsDataMapper.class);
        sut.setConnectionService(mockedConnectionService);
        sut.setPlaylistsDataMapper(mockedPlaylistsDataMapper);
    }

    @Nested
    public class FnGetAllPlaylists {
        @Test
        public void testWaarFunctieJuisteReturnWaardeHeeft() {
            try {
                var expected = new PlaylistsDTO();

                var actual = sut.getAllPlaylists(token);

                assertEquals(expected, actual);
            } catch(Exception e) {
                e.printStackTrace();;
            }
        }
    }
}
