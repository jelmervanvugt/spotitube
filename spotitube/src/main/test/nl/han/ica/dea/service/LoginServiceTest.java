package nl.han.ica.dea.service;

import nl.han.ica.dea.controller.dto.LoginDTO;
import nl.han.ica.dea.controller.dto.LoginResponseDTO;
import nl.han.ica.dea.datasource.dao.LoginDAO;
import nl.han.ica.dea.datasource.exceptions.InvalidCredentialsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginServiceTest {

    private LoginService sut;
    private LoginDAO mockedLoginDAO;

    private LoginDTO loginDTO;

    @BeforeEach
    public void setup() {
        sut = new LoginService();
        mockedLoginDAO = Mockito.mock(LoginDAO.class);
        sut.setLoginDAO(mockedLoginDAO);

        loginDTO = new LoginDTO();
    }

    @Test
    public void test1_loginDAO_setterWorks() {
        var expected = mockedLoginDAO;
        sut.setLoginDAO(expected);

        var result = sut.getLoginDAO();

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test2_checkCredentials_calls_DAO() {
        try {
            var nTimesCalled = 1;
            when(mockedLoginDAO.doesUserExist(loginDTO)).thenReturn(true);

            sut.checkCredentials(loginDTO);

            verify(mockedLoginDAO, times(nTimesCalled)).doesUserExist(loginDTO);
            verify(mockedLoginDAO, times(nTimesCalled)).generateToken(loginDTO);
            verify(mockedLoginDAO, times(nTimesCalled)).checkCredentials(loginDTO);

        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void test3_checkCredentials_returnsOK() {
        try {
            var expected = Response.Status.OK;
            when(mockedLoginDAO.doesUserExist(loginDTO)).thenReturn(true);

            var result = sut.checkCredentials(loginDTO);

            Assertions.assertEquals(expected.getStatusCode(), result.getStatus());
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void test4_checkCredentials_returns_InvalidCredentialsException_when_doesUserExist_isFalse() {
        try {
            when(mockedLoginDAO.doesUserExist(loginDTO)).thenReturn(false);

            Assertions.assertThrows(InvalidCredentialsException.class, () -> sut.checkCredentials(loginDTO));
        }  catch(Exception e) {
            fail();
        }
    }

    @Test
    public void test5_checkCredentials_throws_InvalidCredentialsException_when_generateToken_throwsException() {
        try {
            doThrow(Exception.class).when(mockedLoginDAO).generateToken(loginDTO);

            Assertions.assertThrows(BadRequestException.class, () -> sut.checkCredentials(loginDTO));
        }  catch(Exception e) {
            fail();
        }
    }

    @Test
    public void test6_checkCredentials_returns_InvalidCredentialsException_when_checkCredentials_throwsException() {
        try {
            doThrow(Exception.class).when(mockedLoginDAO).checkCredentials(loginDTO);

            Assertions.assertThrows(BadRequestException.class, () -> sut.checkCredentials(loginDTO));
        }  catch(Exception e) {
            fail();
        }
    }

}
