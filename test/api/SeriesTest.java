package api;

import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;


public class SeriesTest extends TestCase {
    @Test
    public void testSeriesConstructor() {
        Series series = new Series("Σειρά", "Τεστ Σειρά.", true, FilmCategory.COMEDY, "Ηθοποιός1,Ηθοποιός2", "Σειρά1");

        assertEquals("Σειρά", series.getTitle());
        assertEquals("Τεστ Σειρά.", series.getDescription());
        assertTrue(series.isForOver18());
        assertEquals(FilmCategory.COMEDY, series.getCategory());
        assertEquals("Ηθοποιός1,Ηθοποιός2", series.getStars());
        assertEquals("Σειρά1", series.getRelatives());
    }
    @Test
    public void testSettersandGetters() {
        Series series = new Series("Σειρά", "Τεστ Σειρά", true, FilmCategory.COMEDY, "Ηθοποιός1");
        ArrayList<Seazon> seasons = new ArrayList<>();

        seasons.add(new Seazon("1", "2022", new HashMap<Integer,Integer>()));
        seasons.add(new Seazon("2", "2023", new HashMap<Integer,Integer>()));

        series.setSeazons(seasons);
        assertEquals(seasons, series.getSeazons());
    }
    public void testAddSeason() {
        Series series = new Series("Σειρά", "Σειρά Τεστ", true, FilmCategory.COMEDY, "Ηθοποιός1,Ηθοποιός2");
        series.addSeazon("1", "2019", "1,10,2,11");

        assertEquals(1, series.getSeazons().size());
        assertEquals("1", series.getSeazons().get(0).getNumber());
        assertEquals("2019", series.getSeazons().get(0).getPremier());
        assertEquals(2, series.getSeazons().get(0).getEpisodes().size());
        assertEquals(Integer.valueOf(10), series.getSeazons().get(0).getEpisodes().get(1));
        assertEquals(Integer.valueOf(11), series.getSeazons().get(0).getEpisodes().get(2));
    }




}