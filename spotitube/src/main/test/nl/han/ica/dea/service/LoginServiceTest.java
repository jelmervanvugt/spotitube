package nl.han.ica.dea.service;

import nl.han.ica.dea.controller.dto.LoginDTO;
import nl.han.ica.dea.controller.dto.LoginResponseDTO;
import nl.han.ica.dea.datasource.dao.LoginDAO;
import nl.han.ica.dea.datasource.exceptions.InvalidCredentialsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {

    private LoginService sut;
    private LoginDAO mockedLoginDAO;

    @BeforeEach
    public void setup() {
        sut = new LoginService();
        mockedLoginDAO = Mockito.mock(LoginDAO.class);
        sut.setLoginDAO(mockedLoginDAO);
    }

    @Nested
    public class FnCheckCredentials {
        @Test
        public void testValidCredentials() {
            try {
                var loginDTO = new LoginDTO();
                var loginResponseDTO = new LoginResponseDTO();
                var expected = Response.status(Response.Status.OK).entity(loginResponseDTO).build();
                Mockito.when(mockedLoginDAO.doesUserExist(loginDTO)).thenReturn(true);
                Mockito.when(mockedLoginDAO.checkCredentials(loginDTO)).thenReturn(loginResponseDTO);

                var actual = sut.checkCredentials(loginDTO);

                assertEquals(expected.getStatus(), actual.getStatus());
                assertEquals(expected.getEntity(), actual.getEntity());
            } catch(SQLException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        @Test
        public void testThrowsInvalidCredentialsException() throws SQLException, NoSuchAlgorithmException {
            try {
                var loginDTO = new LoginDTO();
                Mockito.when(mockedLoginDAO.doesUserExist(loginDTO)).thenReturn(false);
                sut.checkCredentials(loginDTO);
            } catch(Exception actual) {
                var expected = new InvalidCredentialsException();
                assertEquals(expected.getClass(), actual.getClass());
            }
        }

        @Test
        public void testThrowsBadRequestException() throws SQLException, NoSuchAlgorithmException {
            try {
                var loginDTO = new LoginDTO();
                Mockito.when(mockedLoginDAO.doesUserExist(loginDTO)).thenThrow(new SQLException());
                sut.checkCredentials(loginDTO);
            } catch(Exception actual) {
                var expected = new BadRequestException();
                assertEquals(expected.getClass(), actual.getClass());
            }
        }
    }
}
