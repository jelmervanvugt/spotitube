package nl.han.ica.dea.datasource.DatabaseConnection;

import nl.han.ica.dea.datasource.exceptions.DatabaseConnectionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.BadRequestException;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ConnectionServiceTest {

    private ConnectionService sut;
    private DatabaseProperties mockedDatabaseProperties;
    private Connection mockedConnection;

    private String string = "string";

    @BeforeEach
    public void setup() {
        sut = new ConnectionService();
        mockedDatabaseProperties = mock(DatabaseProperties.class);
    }

    @Test
    public void test1_DatabaseProperties_setterWorks() {
        var expected = mockedDatabaseProperties;
        sut.setDatabaseProperties(expected);

        var result = sut.getDatabaseProperties();

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test2_initConnection_throws_DatabaseConnectionException() {
        try {
            doThrow(Exception.class).when(mockedDatabaseProperties).connectionString();
            doThrow(Exception.class).when(mockedDatabaseProperties).driverString();

            Assertions.assertThrows(DatabaseConnectionException.class, () -> sut.initConnection());

        } catch(Exception e) {
            //
        }
    }

    @Test
    public void test3_close_throws_DatabaseConnectionException() {
        try {
            doThrow(Exception.class).when(mockedConnection).close();

            Assertions.assertThrows(DatabaseConnectionException.class, () -> sut.closeConnection());

        } catch(Exception e) {
           //
        }
    }

}
