package nl.han.ica.dea.datasource.dao;

import nl.han.ica.dea.controller.dto.PlaylistDTO;
import nl.han.ica.dea.controller.dto.PlaylistsDTO;
import nl.han.ica.dea.datasource.datamappers.PlaylistsDataMapper;
import nl.han.ica.dea.datasource.DatabaseConnection.ConnectionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class PlaylistDAOTest {

    private PlaylistDAO sut;
    private ConnectionService mockedConnectionService;
    private PlaylistsDataMapper mockedPlaylistsDataMapper;

    private String token = "token";

    @BeforeEach
    public void setup() {
        sut = new PlaylistDAO();
        mockedConnectionService = mock(ConnectionService.class);
        mockedPlaylistsDataMapper = mock(PlaylistsDataMapper.class);
        sut.setConnectionService(mockedConnectionService);
        sut.setPlaylistsDataMapper(mockedPlaylistsDataMapper);
    }

    @Test
    void test1_connectionService_getterAndSetter_works() {
        var expected = mockedConnectionService;
        sut.setConnectionService(mockedConnectionService);

        var result = sut.getConnectionService();

        Assertions.assertEquals(expected, result);
    }

    @Test
    void test2_playlistDataMapper_getterAndSetter_works() {
        var expected = mockedPlaylistsDataMapper;
        sut.setPlaylistsDataMapper(mockedPlaylistsDataMapper);

        var result = sut.getPlaylistsDataMapper();

        Assertions.assertEquals(expected, result);
    }

    @Test
    void test3_getAllPlaylists_throws_exception() {
        try {
           Assertions.assertThrows(Exception.class, () -> sut.getAllPlaylists(token));
        } catch(Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void test4_editPlaylistname_throws_exception() {
        try {
            Assertions.assertThrows(Exception.class, () -> sut.editPlaylistName(new PlaylistDTO()));
        } catch(Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void test5_addPlaylist_throws_exception() {
        try {
            Assertions.assertThrows(Exception.class, () -> sut.addPlaylist(token, new PlaylistDTO()));
        } catch(Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void test6_deletePlaylist_throws_exception() {
        try {
            Assertions.assertThrows(Exception.class, () -> sut.deletePlaylist(1));
        } catch(Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
