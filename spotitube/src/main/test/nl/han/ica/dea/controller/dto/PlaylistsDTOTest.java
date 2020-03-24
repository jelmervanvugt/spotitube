package nl.han.ica.dea.controller.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaylistsDTOTest {

    private PlaylistsDTO sut;

    private ArrayList<PlaylistDTO> playlists;
    private int length = 10;

    @BeforeEach
    public void setup() {
      sut = new PlaylistsDTO(playlists, length);
    }

    @Nested
    public class FnGetPlaylists {
        @Test
        public void testReturnWaardeGetPlaylists() {
            var expected = playlists;

            var actual = sut.getPlaylists();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnGetLength {
        @Test
        public void testReturnWaardeGetLength() {
            var expected = length;

            var actual = sut.getLength();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnSetPlaylists {
        @Test
        public void testSetPlaylists() {
            var expected = new ArrayList<PlaylistDTO>();

            sut.setPlaylists(expected);
            var actual = sut.getPlaylists();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnSetLength {
        @Test
        public void testSetLength() {
            var expected = 20;

            sut.setLength(expected);
            var actual = sut.getLength();

            assertEquals(expected, actual);
        }
    }

}
