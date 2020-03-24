package nl.han.ica.dea.controller.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginResponseDTOTest {

    private LoginResponseDTO sut;
    private String user = "user";
    private String token = "token";

    @BeforeEach
    public void setup() {
        sut = new LoginResponseDTO(user, token);
    }

    @Nested
    public class FnGetUser {
        @Test
        public void testReturnWaardeGetUser() {
            var expected = user;

            var actual = sut.getUser();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnGetToken {
        @Test
        public void testReturnWaardeGetToken() {
            var expected = token;

            var actual = sut.getToken();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnSetUser {
        @Test
        public void testSetUser() {
            var expected = "newuser";

            sut.setUser(expected);
            var actual = sut.getUser();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnSetToken {
        @Test
        public void testSetToken() {
            var expected = "newtoken";

            sut.setToken(expected);
            var actual = sut.getToken();

            assertEquals(expected, actual);
        }
    }
}
