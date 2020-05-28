package nl.han.ica.dea.datasource.dao;

import nl.han.ica.dea.controller.dto.TrackDTO;
import nl.han.ica.dea.controller.dto.TracksDTO;
import nl.han.ica.dea.datasource.datamappers.TracksDataMapper;
import nl.han.ica.dea.datasource.DatabaseConnection.ConnectionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackDAOTest {

    private TrackDAO sut;
    private TracksDataMapper mockedTracksDataMapper;
    private ConnectionService mockedConnectionService;
    private PreparedStatement mockedPreparedStatement;
    private Connection mockedConnection;

    private TrackDTO trackDTO;

    private int playlistId = 1;
    private int trackId = 1;

    @BeforeEach
    public void setup() {
        sut = new TrackDAO();
        mockedTracksDataMapper = mock(TracksDataMapper.class);
        mockedConnectionService = mock(ConnectionService.class);
        mockedPreparedStatement = mock(PreparedStatement.class);
        mockedConnection = mock(Connection.class);

        sut.setTracksDataMapper(mockedTracksDataMapper);
        sut.setConnectionService(mockedConnectionService);

        trackDTO = new TrackDTO();
    }

   @Test
    void test1_trackDataMapper_getAndSetter_Works() {
        var expected = mockedTracksDataMapper;
        sut.setTracksDataMapper(mockedTracksDataMapper);

        var result = sut.getTracksDataMapper();

       Assertions.assertEquals(result.getClass(), expected.getClass());
   }

    @Test
    void test2_connectionService_getAndSetter_Works() {
        var expected = mockedConnectionService;
        sut.setConnectionService(mockedConnectionService);

        var result = sut.getConnectionService();

        Assertions.assertEquals(result.getClass(), expected.getClass());
    }

    @Test
    void test3_addTrackToPlaylist_throws_exception() {
        try {
            doThrow(Exception.class).when(mockedConnectionService).initConnection();

            Assertions.assertThrows(Exception.class, () -> sut.addTrackToPlaylist(playlistId, trackDTO));
        } catch(Exception e) {
            e.printStackTrace();
            //
        }
    }

    @Test
    void test4_getAllTracksFromPlaylist_throws_exception() {
        try {
            doThrow(Exception.class).when(mockedConnectionService).initConnection();

            Assertions.assertThrows(Exception.class, () -> sut.getAllTracksFromPlaylist(playlistId));
        } catch(Exception e) {
            e.printStackTrace();
            //
        }
    }

    @Test
    void test5_getAllTracksNotInPlaylist_throws_exception() {
        try {
            doThrow(Exception.class).when(mockedConnectionService).initConnection();

            Assertions.assertThrows(Exception.class, () -> sut.getAllTracksNotInPlaylist(playlistId));
        } catch(Exception e) {
            e.printStackTrace();
            //
        }
    }

    @Test
    void test6_deleteTracksFromPlaylist_throws_exception() {
        try {
            doThrow(Exception.class).when(mockedConnectionService).initConnection();

            Assertions.assertThrows(Exception.class, () -> sut.deleteTrackFromPlaylist(playlistId, trackId));
        } catch(Exception e) {
            e.printStackTrace();
            //
        }
    }

}
