package nl.han.ica.dea.datasource.datamappers;

import nl.han.ica.dea.controller.dto.PlaylistDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PlaylistsDataMapperTest {

    private PlaylistsDataMapper sut;
    private ResultSet mockedResultSet;

    private int id = 1;
    private String name = "name";
    private boolean isowner = true;

    @BeforeEach
    public void setup() {
        sut = new PlaylistsDataMapper();
        mockedResultSet = Mockito.mock(ResultSet.class);
    }

    @Nested
    public class FnMapToDTO {
        @Test
        public void testReturnWaardeMapToDTO() {
            try {
                var expected = new PlaylistDTO(id, name, isowner);
                Mockito.when(mockedResultSet.next()).thenReturn(true, false);
                Mockito.when(mockedResultSet.getInt("id")).thenReturn(id);
                Mockito.when(mockedResultSet.getString("name")).thenReturn(name);
                Mockito.when(mockedResultSet.getBoolean("isowner")).thenReturn(isowner);

                var actual = sut.mapToDTO(mockedResultSet);

                assertEquals(expected.getId(), actual.get(0).getId());
                assertEquals(expected.getName(), actual.get(0).getName());
                assertEquals(expected.isOwner(), actual.get(0).isOwner());
            } catch(SQLException e) {
                fail();
            }
        }

        @Test
        public void testAantalResultsInReturnWaarde() {
            try {
                var expected = 2;
                Mockito.when(mockedResultSet.next()).thenReturn(true, true, false);
                Mockito.when(mockedResultSet.getInt("id")).thenReturn(id);
                Mockito.when(mockedResultSet.getString("name")).thenReturn(name);
                Mockito.when(mockedResultSet.getBoolean("isowner")).thenReturn(isowner);

                var returnWaarde = sut.mapToDTO(mockedResultSet);
                var actual = returnWaarde.size();

                assertEquals(expected, actual);
            } catch(SQLException e) {
                fail();
            }
        }
    }
}
