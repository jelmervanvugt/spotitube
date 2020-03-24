package nl.han.ica.dea.controller.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TracksDTOTest {

    private TracksDTO sut;

    private ArrayList<TrackDTO> tracks;

    @BeforeEach
    public void setup() {
        sut = new TracksDTO(tracks);
    }

    @Nested
    public class FnGetTracks {
        @Test
        public void testReturnWaardeGetTracks() {
            var expected = tracks;

            var actual = sut.getTracks();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnSetTracks {
        @Test
        public void testSetTracks() {
            tracks = new ArrayList<TrackDTO>();
            var expected = tracks;

            sut.setTracks(tracks);
            var actual = sut.getTracks();

            assertEquals(expected, actual);
        }
    }

}
