package nl.han.ica.dea.datasource.dao;

import nl.han.ica.dea.controller.dto.LoginDTO;
import nl.han.ica.dea.controller.dto.LoginResponseDTO;
import nl.han.ica.dea.datasource.datamappers.LoginResponseDataMapper;
import nl.han.ica.dea.service.ConnectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;

public class LoginDAOTest {

    private LoginDAO sut;
    private LoginResponseDataMapper mockedLoginResponseDataMapper;
    private ConnectionService mockedConnectionService;

    private String user = "user";
    private String password = "password";

    private LoginDTO loginDTO = new LoginDTO(user, password);

    @BeforeEach
    public void setup() {
        sut = new LoginDAO();
        mockedLoginResponseDataMapper = Mockito.mock(LoginResponseDataMapper.class);
        mockedConnectionService = Mockito.mock(ConnectionService.class);
        sut.setLoginResponseDataMapper(mockedLoginResponseDataMapper);
        sut.setConnectionService(mockedConnectionService);
    }

    @Nested
    public class FnCheckCredentials {
        @Test
        public void testWaarReturnWaardeJuistIs() {
            try {
                var expected = new LoginResponseDTO();

                var actual = sut.checkCredentials(loginDTO);

                assertEquals(expected, actual);
            } catch(Exception actual) {
                actual.printStackTrace();
            }
        }
    }
}
