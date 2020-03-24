package nl.han.ica.dea.controller.controllers;

import nl.han.ica.dea.controller.dto.LoginDTO;
import nl.han.ica.dea.controller.dto.LoginResponseDTO;
import nl.han.ica.dea.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginControllerTest {

    private LoginController sut;
    private LoginService mockedLoginService;

    @BeforeEach
    public void setup() {
        sut = new LoginController();

        mockedLoginService = Mockito.mock(LoginService.class);
        sut.setLoginService(mockedLoginService);
    }

    @Nested
    class FnLoginTest {
        @Test
        public void testResponseVanEndpoint() {
            var loginDTO = new LoginDTO("user", "password");
            var loginResponseDTO = new LoginResponseDTO("token", "fullname");
            var expected = Response.status(Response.Status.OK).entity(loginResponseDTO).build();

            Mockito.when(mockedLoginService.checkCredentials(loginDTO)).thenReturn(expected);
            var actual = sut.login(loginDTO);

            assertEquals(actual.getStatus(), expected.getStatus());
            assertEquals(actual.getEntity(), expected.getEntity());
        }
    }
}
















