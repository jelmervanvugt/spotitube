package nl.han.ica.dea.controller.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginDTOTest {

    private LoginDTO loginDTO;
    private String user = "user";
    private String password = "password";

    @BeforeEach
    public void setup() {
        loginDTO = new LoginDTO(user, password);
    }

    @Nested
    public class FnGetUser {
       @Test
       public void testReturnWaarde() {
           var expected = user;

           var actual = loginDTO.getUser();

           assertEquals(expected, actual);
       }
    }

    @Nested
    public class FnGetPassword {
        @Test
        public void testReturnWaarde() {
            var expected = password;

            var actual = loginDTO.getPassword();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnSetUser {
        @Test
        public void testSetFn() {
            var expected = "newuser";
            loginDTO.setUser(expected);

            var actual = loginDTO.getUser();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnSetPassword {
        @Test
        public void testSetFn() {
            var expected = "newpassword";
            loginDTO.setPassword(expected);

            var actual = loginDTO.getPassword();

            assertEquals(expected, actual);
        }
    }
}
