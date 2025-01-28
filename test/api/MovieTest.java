package api;

import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;



public class MovieTest extends TestCase{

    @Test
   public void testMovieConstructorWithRelatives() {
        Movie movie = new Movie("Τεστ", "Ταινία Τεστ", true, FilmCategory.ACTION, "Ηθοποιός1", "Ταινία1, Ταινία2", 2023, 180);

        assertEquals("Τεστ", movie.getTitle());
        assertEquals("Ταινία Τεστ", movie.getDescription());
        assertTrue(movie.isForOver18());
        assertEquals(FilmCategory.ACTION, movie.getCategory());
        assertEquals("Ηθοποιός1", movie.getStars());
        assertEquals("Ταινία1, Ταινία2", movie.getRelatives());
        assertEquals(2023, movie.getPremier());
        assertEquals(180, movie.getDuration());
    }

    @Test
    public void testGettersAndSetters() {
        Movie testMovie = new Movie("Τιτλος1", "Περιγραφή", true, FilmCategory.ACTION, "Ηθοποιός1", "Ταινία1", 2022, 120);

        testMovie.setTitle("Ταινία2");
        testMovie.setDescription("Περιγραφή2");
        testMovie.setForOver18(false);
        testMovie.setCategory(FilmCategory.DRAMA);
        testMovie.setStars("Ηθοποιός2");
        testMovie.setRelatives("Ταινία2");
        testMovie.setPremier(2023);
        testMovie.setDuration(130);

        assertEquals("Ταινία2", testMovie.getTitle());
        assertEquals("Περιγραφή2", testMovie.getDescription());
        assertFalse(testMovie.isForOver18());
        assertEquals(FilmCategory.DRAMA, testMovie.getCategory());
        assertEquals("Ηθοποιός2", testMovie.getStars());
        assertEquals("Ταινία2", testMovie.getRelatives());
        assertEquals(2023, testMovie.getPremier());
        assertEquals(130, testMovie.getDuration());
    }

    @Test
    public void testToString() {
        Movie movie = new Movie("Τεστ", "Ταινία Τεστ", true, FilmCategory.COMEDY, "Ηθοποιός1, Ηθοποιός2", "Ταινία1, Ταινία2", 2023, 220);
        assertEquals("Title: Τεστ, Description: Ταινία Τεστ", movie.toString());
    }



}