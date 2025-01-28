package api;
/***
 * Represents a series, extending the base class Film
 * Series have additional attributes such as seasons containing episodes
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Series extends Film implements Serializable {
    ArrayList<Seazon> seazons;
    /***
     * Constructs a Series object with specified attributes
     *
     * @param title       The title of the series
     * @param description The description of the series
     * @param forOver18   Indicates whether the series is suitable for viewers over 18 years old
     * @param category    The category of the series
     * @param stars       The names of the star actors in the series
     * @param relatives   The names of series similar to the series
     */
    public Series(String title, String description, boolean forOver18, FilmCategory category, String stars, String relatives) {
        super(title, description, forOver18, category, stars, relatives);
        seazons = new ArrayList<>();
    }
    /***
     * Constructs a Series object with specified attributes (excluding relatives)
     *
     * @param title       The title of the series
     * @param description The description of the series
     * @param forOver18   Indicates whether the series is suitable for viewers over 18 years old
     * @param category    The category of the series
     * @param stars       The names of the star actors in the series
     */
    public Series(String title, String description, boolean forOver18, FilmCategory category, String stars) {
        super(title, description, forOver18, category, stars);
        seazons = new ArrayList<>();
    }
    /***
     * Method that returns the list of seasons for the  series
     *
     * @return The list of seasons
     */
    public ArrayList<Seazon> getSeazons() {
        return seazons;
    }
    /***
     * Method that sets the list of seasons for the series
     *
     * @param seazons The list of seasons to set for the series
     */

    public void setSeazons(ArrayList<Seazon> seazons) {
        this.seazons = seazons;
    }
    /***
     * Method that adds a new season to the TV series with specified attributes
     *
     * @param number   The season number
     * @param premier  The premiere date of the season
     * @param episodes A string representation of episodes
     */
    public void addSeazon(String number,String premier,String episodes){
        String[] Sepisodes = episodes.split(",");
        HashMap<Integer,Integer> SeEpisodes = new HashMap<Integer,Integer>();
        for (int i=0;i<Sepisodes.length;i+=2){
            SeEpisodes.put(Integer.parseInt(Sepisodes[i]),Integer.parseInt(Sepisodes[i+1]));
        }
        Seazon seazon = new Seazon(number,premier,SeEpisodes);
        seazons.add(seazon);
    }

}
