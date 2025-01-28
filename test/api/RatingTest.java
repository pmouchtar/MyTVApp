package api;

import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;


public class RatingTest extends TestCase {


    private Rating testRating = new Rating("Σχόλια", 8);

    @Test
    public void testComments() {
        assertEquals("Σχόλια", testRating.getComments());
        testRating.setComments("Νέα σχόλια");
        assertEquals("Νέα σχόλια", testRating.getComments());
    }

    @Test
    public void testRating() {
        assertEquals(8, testRating.getRating());
        testRating.setRating(5);
        assertEquals(5, testRating.getRating());
    }

    @Test
    public void testToString() {
        assertEquals("Rating: 8. Comment: Σχόλια", testRating.toString());
    }


}