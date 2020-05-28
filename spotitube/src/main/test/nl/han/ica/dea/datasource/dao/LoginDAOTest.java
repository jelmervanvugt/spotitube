package nl.han.ica.dea.datasource.dao;

import nl.han.ica.dea.controller.dto.LoginDTO;
import nl.han.ica.dea.controller.dto.LoginResponseDTO;
import nl.han.ica.dea.datasource.datamappers.LoginResponseDataMapper;
import nl.han.ica.dea.datasource.DatabaseConnection.ConnectionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class LoginDAOTest {

    private LoginDAO sut;
    private LoginResponseDataMapper mockedLoginResponseDataMapper;
    private ConnectionService mockedConnectionService;
    private Connection mockedConnection;
    private PreparedStatement preparedStatement;

    private String user = "user";
    private String password = "password";
    private String token = "token";

    private ResultSet mockedResultSet;

    private LoginDTO loginDTO = new LoginDTO(user, password);
    private LoginResponseDTO loginResponseDTO = new LoginResponseDTO(user, token);

    @BeforeEach
    public void setup() {
        sut = new LoginDAO();
        mockedLoginResponseDataMapper = mock(LoginResponseDataMapper.class);
        mockedConnectionService = mock(ConnectionService.class);
        mockedResultSet = mock(ResultSet.class);
        mockedConnection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        sut.setLoginResponseDataMapper(mockedLoginResponseDataMapper);
        sut.setConnectionService(mockedConnectionService);
    }

    @Test
    void test1_connectionService_getterAndSetter_works() {
        var expected = mockedConnectionService;
        sut.setConnectionService(mockedConnectionService);

        var result = sut.getConnectionService();

        Assertions.assertEquals(expected, result);
    }

    @Test
    void test2_loginResponseDataMapper_getterAndSetter_works() {
        var expected = mockedLoginResponseDataMapper;
        sut.setLoginResponseDataMapper(mockedLoginResponseDataMapper);

        var result = sut.getLoginResponseDataMapper();

        Assertions.assertEquals(expected, result);
    }

    @Test
    void test3_checkCredentials_calls_connectionService() {
        try {
            var nTimesCalled = 1;
            doNothing().when(mockedConnectionService).initConnection();
            doNothing().when(mockedConnectionService).closeConnection();
            when(mockedConnectionService.getConnection()).thenReturn(mockedConnection);
            when(mockedConnectionService.getConnection().prepareStatement(any(String.class))).thenReturn(preparedStatement);

            sut.checkCredentials(loginDTO);

            verify(mockedConnectionService, times(nTimesCalled)).initConnection();
            verify(mockedConnectionService, times(nTimesCalled)).closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void test4_checkCredentials_calls_LoginDataMapper() {
        try {
            var nTimesCalled = 1;
            doNothing().when(mockedConnectionService).initConnection();
            doNothing().when(mockedConnectionService).closeConnection();
            when(mockedConnectionService.getConnection()).thenReturn(mockedConnection);
            when(mockedConnectionService.getConnection().prepareStatement(any(String.class))).thenReturn(preparedStatement);
            when(mockedLoginResponseDataMapper.mapToDTO(mockedResultSet)).thenReturn(loginResponseDTO);

            sut.checkCredentials(loginDTO);

            verify(mockedLoginResponseDataMapper, times(nTimesCalled)).mapToDTO(null);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void test5_checkCredentials_throws_exception() {
        try {
            doThrow(Exception.class).when(mockedConnectionService).initConnection();

            Assertions.assertThrows(Exception.class, () -> sut.checkCredentials(loginDTO));
        } catch (Exception e) {
            //
        }
    }

    @Test
    void test6_doesUserExist_throws_Exception() {
        try {
            Assertions.assertThrows(Exception.class, () -> sut.doesUserExist(loginDTO));
        } catch (Exception e) {
            //
        }
    }

    @Test
    void test7_generateToken_throws_Exception() {
        try {
            Assertions.assertThrows(Exception.class, () -> sut.generateToken(loginDTO));
        } catch (Exception e) {
            //
        }
    }

    @Test
    void test8_generateToken_calls_connectionService() {
        try {
            var nTimesCalled = 1;
            doNothing().when(mockedConnectionService).initConnection();
            doNothing().when(mockedConnectionService).closeConnection();
            when(mockedConnectionService.getConnection()).thenReturn(mockedConnection);
            when(mockedConnectionService.getConnection().prepareStatement(any(String.class))).thenReturn(preparedStatement);

            sut.generateToken(loginDTO);

            verify(mockedConnectionService, times(nTimesCalled)).initConnection();
            verify(mockedConnectionService, times(nTimesCalled)).closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }


    }
}