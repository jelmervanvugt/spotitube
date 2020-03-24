package nl.han.ica.dea.controller.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginDTOTest {

    private LoginDTO sut;
    private String user = "user";
    private String password = "password";

    @BeforeEach
    public void setup() {
        sut = new LoginDTO(user, password);
    }

    @Nested
    public class FnGetUser {
       @Test
       public void testReturnWaarde() {
           var expected = user;

           var actual = sut.getUser();

           assertEquals(expected, actual);
       }
    }

    @Nested
    public class FnGetPassword {
        @Test
        public void testReturnWaarde() {
            var expected = password;

            var actual = sut.getPassword();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnSetUser {
        @Test
        public void testSetFn() {
            var expected = "newuser";
            sut.setUser(expected);

            var actual = sut.getUser();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnSetPassword {
        @Test
        public void testSetFn() {
            var expected = "newpassword";
            sut.setPassword(expected);

            var actual = sut.getPassword();

            assertEquals(expected, actual);
        }
    }
}
