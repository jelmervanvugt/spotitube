package nl.han.ica.dea.datasource.DatabaseConnection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.BadRequestException;
import java.util.Objects;
import java.util.Properties;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class DatabasePropertiesTest {

    private DatabaseProperties sut;
    private Properties mockedProperties;

    private String connectionString = "string";

    @BeforeEach
    public void setup() {
        mockedProperties = mock(Properties.class);
    }

    @Test
    public void test1_constructor_throws_Exception() {
        try {
            doThrow(Exception.class).when(mockedProperties).load(Objects.requireNonNull(getClass()
                    .getClassLoader()
                    .getResourceAsStream("database.properties")));
            Assertions.assertThrows(Exception.class, () -> sut = new DatabaseProperties());
        } catch (Exception e) {
            //
        }
    }

}
