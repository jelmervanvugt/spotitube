package nl.han.ica.dea.datasource.datamappers;

import nl.han.ica.dea.controller.dto.PlaylistDTO;
import nl.han.ica.dea.controller.dto.TrackDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TracksDataMapperTest {

    private TracksDataMapper sut;
    private ResultSet mockedResultSet;

    private int id = 1;
    private String title = "title";
    private String performer = "performer";
    private int duration = 2;
    private String album = "album";
    private int playcount = 3;
    private String publicationDate = "publicationDate";
    private String description = "description";
    private boolean offlineAvailable = true;

    @BeforeEach
    public void setup() {
        sut = new TracksDataMapper();
        mockedResultSet = Mockito.mock(ResultSet.class);
    }

    @Nested
    public class FnMapToDTO {
        @Test
        public void testReturnWaardeMapToDTO() {
            try {
                var expected = new TrackDTO(id, title, performer, duration, album, playcount, publicationDate, description, offlineAvailable);
                Mockito.when(mockedResultSet.next()).thenReturn(true, false);
                Mockito.when(mockedResultSet.getInt("id")).thenReturn(id);
                Mockito.when(mockedResultSet.getString("title")).thenReturn(title);
                Mockito.when(mockedResultSet.getString("performer")).thenReturn(performer);
                Mockito.when(mockedResultSet.getInt("duration")).thenReturn(duration);
                Mockito.when(mockedResultSet.getString("album")).thenReturn(album);
                Mockito.when(mockedResultSet.getInt("playcount")).thenReturn(playcount);
                Mockito.when(mockedResultSet.getString("publicationDate")).thenReturn(publicationDate);
                Mockito.when(mockedResultSet.getString("description")).thenReturn(description);
                Mockito.when(mockedResultSet.getBoolean("offlineAvailable")).thenReturn(offlineAvailable);

                var actual = sut.mapToDTO(mockedResultSet);

                assertEquals(expected.getId(), actual.getTracks().get(0).getId());
                assertEquals(expected.getTitle(), actual.getTracks().get(0).getTitle());
                assertEquals(expected.getPerformer(), actual.getTracks().get(0).getPerformer());
                assertEquals(expected.getDuration(), actual.getTracks().get(0).getDuration());
                assertEquals(expected.getAlbum(), actual.getTracks().get(0).getAlbum());
                assertEquals(expected.getPlaycount(), actual.getTracks().get(0).getPlaycount());
                assertEquals(expected.getPublicationDate(), actual.getTracks().get(0).getPublicationDate());
                assertEquals(expected.getDescription(), actual.getTracks().get(0).getDescription());
                assertEquals(expected.getOfflineAvailable(), actual.getTracks().get(0).getOfflineAvailable());
            } catch(SQLException e) {
                fail();
            }
        }

        @Test
        public void testAantalResultatenVanReturnwaarde() {
            try {
                var expected = 2;
                Mockito.when(mockedResultSet.next()).thenReturn(true, true, false);
                Mockito.when(mockedResultSet.getInt("id")).thenReturn(id);
                Mockito.when(mockedResultSet.getString("title")).thenReturn(title);
                Mockito.when(mockedResultSet.getString("performer")).thenReturn(performer);
                Mockito.when(mockedResultSet.getInt("duration")).thenReturn(duration);
                Mockito.when(mockedResultSet.getString("album")).thenReturn(album);
                Mockito.when(mockedResultSet.getInt("playcount")).thenReturn(playcount);
                Mockito.when(mockedResultSet.getString("publicationDate")).thenReturn(publicationDate);
                Mockito.when(mockedResultSet.getString("description")).thenReturn(description);
                Mockito.when(mockedResultSet.getBoolean("offlineAvailable")).thenReturn(offlineAvailable);

                var returnWaarde = sut.mapToDTO(mockedResultSet);
                var actual = returnWaarde.getTracks().size();

                assertEquals(expected, actual);

            } catch(SQLException e) {
                fail();
            }
        }
    }
}
