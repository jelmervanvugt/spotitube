package nl.han.ica.dea.controller.controllers;

import nl.han.ica.dea.controller.dto.LoginDTO;
import nl.han.ica.dea.controller.dto.LoginResponseDTO;
import nl.han.ica.dea.service.LoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import static org.mockito.Mockito.*;


public class LoginControllerTest {

    private LoginController sut;
    private LoginService mockedLoginService;

    private LoginDTO loginDTO;

    private String user = "user";
    private String password = "password";
    private String token = "token";
    private String fullname = "fullname";

    @BeforeEach
    public void setup() {
        sut = new LoginController();

        mockedLoginService = Mockito.mock(LoginService.class);
        sut.setLoginService(mockedLoginService);

        loginDTO = new LoginDTO(user, password);
    }

    @Test
    public void test1_loginService_injection_works() {
        var expected = mockedLoginService;
        sut.setLoginService(mockedLoginService);

        var result = sut.getLoginService();

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test2_login_returnsOK() {
        var expected = Response.Status.OK;
        when(mockedLoginService.checkCredentials(loginDTO)).thenReturn(Response.status(Response.Status.OK).build());

        var result = sut.login(loginDTO);

        Assertions.assertEquals(expected.getStatusCode(), result.getStatus());
    }

    @Test
    public void test3_login_returnsBadRequestException() {
        var expected = new BadRequestException();
        when(mockedLoginService.checkCredentials(loginDTO)).thenThrow(expected);

        Assertions.assertThrows(BadRequestException.class, () -> sut.login(loginDTO));
    }

    @Test
    public void test4_login_calls_checkCredentials() {
        var nTimesCalled = 1;

        sut.login(loginDTO);

        verify(mockedLoginService, times(nTimesCalled)).checkCredentials(loginDTO);
    }
}
















