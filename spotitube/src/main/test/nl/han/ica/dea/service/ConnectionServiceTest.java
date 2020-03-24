package nl.han.ica.dea.service;


import nl.han.ica.dea.datasource.util.DatabaseProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConnectionServiceTest {

    private ConnectionService sut;
    private DatabaseProperties mockedDatabaseProperties;

    @BeforeEach
    public void setup() {
        sut = new ConnectionService();
        mockedDatabaseProperties = Mockito.mock(DatabaseProperties.class);

        sut.setDatabaseProperties(mockedDatabaseProperties);
    }

    @Nested
    public class FnGetConnection {
        @Test
        public void testReturnWaardeVanGetConnection() {
            //missing
        }
    }

    @Nested
    public class FnInitConnection {
        //missing
    }

    @Nested
    public class FnCloseConnection {
        //missing
    }

}
