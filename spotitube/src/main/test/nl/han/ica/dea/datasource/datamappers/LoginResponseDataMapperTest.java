package nl.han.ica.dea.datasource.datamappers;

import nl.han.ica.dea.controller.dto.LoginResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class LoginResponseDataMapperTest {

    private LoginResponseDataMapper sut;
    private ResultSet mockedResultSet;

    private String fullname = "fullname";
    private String token = "token";

    @BeforeEach
    public void setup() {
        sut = new LoginResponseDataMapper();
        mockedResultSet = Mockito.mock(ResultSet.class);
    }

    @Nested
    public class FnMapToDTO {
        @Test
        public void testReturnWaardeMapToDTO() {
            try {
                var expected = new LoginResponseDTO(fullname, token);
                Mockito.when(mockedResultSet.getString("fullname")).thenReturn(fullname);
                Mockito.when(mockedResultSet.getString("token")).thenReturn(token);

                var actual = sut.mapToDTO(mockedResultSet);

                assertEquals(expected.getUser(), actual.getUser());
                assertEquals(expected.getToken(), actual.getToken());
            } catch(SQLException e) {
                fail();
            }
        }
    }
}
