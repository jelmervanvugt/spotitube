package nl.han.ica.dea.service;

import nl.han.ica.dea.controller.dto.LoginDTO;
import nl.han.ica.dea.controller.dto.LoginResponseDTO;
import nl.han.ica.dea.datasource.dao.LoginDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginServiceTest {

    private LoginService sut;
    private LoginDAO mockedLoginDAO;

    private String user = "user";
    private String password = "password";
    private String token = "token";
    private String fullname = "fullname";

    @BeforeEach
    public void setup() {
        sut = new LoginService();
        mockedLoginDAO = Mockito.mock(LoginDAO.class);
        sut.setLoginDAO(mockedLoginDAO);
    }

    @Nested
    public class FnCheckCredentials {
        @Test
        public void testReturnWaardeFunctie() {
            var loginDTO = new LoginDTO(user, password);
            var loginResponseDTO = new LoginResponseDTO(fullname, token);
            var expected = Response.status(Response.Status.OK).entity(loginResponseDTO).build();
            Mockito.when(mockedLoginDAO.checkCredentials(loginDTO)).thenReturn(expected);

            var actual = sut.checkCredentials(loginDTO);

            assertEquals(expected.getStatus(), actual.getStatus());
            assertEquals(expected.getEntity(), actual.getEntity());
        }
    }
}
