package nl.han.ica.dea.controller.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrackDTOTest {

    private TrackDTO sut;
    private int id = 1;
    private String title = "title";
    private String performer = "performer";
    private int duration = 1;
    private String album = "album";
    private int playcount = 1;
    private String publicationDate = "publicationDate";
    private String description = "description";
    private boolean offlineAvailable = true;

    @BeforeEach
    public void setup() {
        sut = new TrackDTO(id, title, performer, duration, album, playcount, publicationDate, description, offlineAvailable);
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
    public class FnGetTitle {
        @Test
        public void testReturnWaardeGetTitle() {
            var expected = title;

            var actual = sut.getTitle();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnGetPerformer {
        @Test
        public void testReturnWaardeGetPerformer() {
            var expected = performer;

            var actual = sut.getPerformer();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnGetDuration {
        @Test
        public void testReturnWaardeGetDuration() {
            var expected = duration;

            var actual = sut.getDuration();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnGetAlbum {
        @Test
        public void testReturnWaardeGetAlbum() {
            var expected = album;

            var actual = sut.getAlbum();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnGetPlaycount {
        @Test
        public void testReturnWaardeGetPlaycount() {
            var expected = playcount;

            var actual = sut.getPlaycount();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnGetPublicationDate {
        @Test
        public void testReturnWaardeGetPublicationDate() {
            var expected = publicationDate;

            var actual = sut.getPublicationDate();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnGetDescription {
        @Test
        public void testReturnWaardeGetDescription() {
            var expected = description;

            var actual = sut.getDescription();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnGetOfflineAvailable {
        @Test
        public void testReturnWaardeGetOfflineAvailable() {
            var expected = offlineAvailable;

            var actual = sut.getOfflineAvailable();

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
    public class FnSetTitle {
        @Test
        public void testSetTitle() {
            var expected = "newtitle";

            sut.setTitle(expected);
            var actual = sut.getTitle();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnSetPerformer {
        @Test
        public void testSetPerformer() {
            var expected = "newperformer";

            sut.setPerformer(expected);
            var actual = sut.getPerformer();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnSetDuration {
        @Test
        public void testSetDuration() {
            var expected = 2;

            sut.setDuration(expected);
            var actual = sut.getDuration();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnSetAlbum {
        @Test
        public void testSetAlbum() {
            var expected = "newalbum";

            sut.setAlbum(expected);
            var actual = sut.getAlbum();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnSetPlaycount {
        @Test
        public void testSetPlaycount() {
            var expected = 2;

            sut.setPlaycount(expected);
            var actual = sut.getPlaycount();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnSetPublicationDate {
        @Test
        public void testSetPublicationDate() {
            var expected = "newPublicationDate";

            sut.setPublicationDate(expected);
            var actual = sut.getPublicationDate();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnSetDescription {
        @Test
        public void testSetDescription() {
            var expected = "newdescription";

            sut.setDescription(expected);
            var actual = sut.getDescription();

            assertEquals(expected, actual);
        }
    }

    @Nested
    public class FnSetOfflineAvailable {
        @Test
        public void testSetOfflineAvailable() {
            var expected = false;

            sut.setOfflineAvailable(expected);
            var actual = sut.getOfflineAvailable();

            assertEquals(expected, actual);
        }
    }

}
