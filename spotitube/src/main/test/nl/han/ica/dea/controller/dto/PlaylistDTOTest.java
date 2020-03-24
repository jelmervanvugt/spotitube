package nl.han.ica.dea.controller.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaylistDTOTest {

    private PlaylistDTO sut;

    private int id = 1;
    private String name = "name";
    private boolean owner = true;
    private ArrayList<TrackDTO> tracks;

    @BeforeEach
    public void setup() {
        sut = new PlaylistDTO(id, name, owner);
    }

    @Nested
    public class FnGetId {
        @Test
        public void testReturnWaardeGetId() {
            var expected = id;

            var actual = sut.getId();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnGetName {
        @Test
        public void testReturnWaardeGetName() {
            var expected = name;

            var actual = sut.getName();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnGetOwner {
        @Test
        public void testReturnWaardeGetName() {
            var expected = owner;

            var actual = sut.isOwner();

            assertEquals(expected, actual);
        }
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
    public class FnSetId {
        @Test
        public void testSetId() {
            var expected = 2;

            sut.setId(expected);
            var actual = sut.getId();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnSetName {
        @Test
        public void testSetName() {
            var expected = "newname";

            sut.setName(expected);
            var actual = sut.getName();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnSetOwner {
        @Test
        public void testSetOwner() {
            var expected = false;

            sut.setOwner(expected);
            var actual = sut.isOwner();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnSetTracks {
        @Test
        public void testSetTracks() {
            var expected = new ArrayList<TrackDTO>();

            sut.setTracks(expected);
            var actual = sut.getTracks();

            assertEquals(expected, actual);
        }
    }

}
