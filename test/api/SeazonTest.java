package api;

import junit.framework.TestCase;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;


public class SeazonTest extends TestCase {
     Seazon testSeazon = new Seazon("Σεζόν1", "2018", new HashMap<Integer, Integer>() {{
        put(1,10);
        put(2,10);
    }});

    @Test
    public void testSeazonProperties() {
        assertEquals("Σεζόν1", testSeazon.getNumber());
        assertEquals("2018", testSeazon.getPremier());
        assertEquals(10, testSeazon.getEpisodes().get(1).intValue());
        assertEquals(10, testSeazon.getEpisodes().get(2).intValue());
    }

    @Test
    public void testSettersandGetters() {
        testSeazon.setNumber("Σεζόν2");
        testSeazon.setPremier("2020");
        testSeazon.getEpisodes().put(1, 20);
        testSeazon.getEpisodes().put(2, 20);

        assertEquals("Σεζόν2", testSeazon.getNumber());
        assertEquals("2020", testSeazon.getPremier());
        assertEquals(20, testSeazon.getEpisodes().get(1).intValue());
        assertEquals(20, testSeazon.getEpisodes().get(2).intValue());
    }

}