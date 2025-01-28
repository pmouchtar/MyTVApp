package api;
/***
 * Represents a movie, extending the base class Film
 * Movies have additional attributes such as premiere year and duration
 */
import java.io.Serializable;
import java.util.ArrayList;

public class Movie extends Film implements Serializable {
    int premier;
    int duration;

    /***
     * Constructs a Movie object with specified attributes
     *
     * @param title       The title of the movie
     * @param description The description of the movie
     * @param forOver18   Indicates whether the movie is suitable for viewers over 18 years old
     * @param category    The category of the movie
     * @param stars       The names of star actors in the movie
     * @param relatives   The names of movies similar with the movie
     * @param premier     The premiere year of the movie
     * @param duration    The duration of the movie
     */

    public Movie(String title, String description, boolean forOver18, FilmCategory category, String stars, String relatives,int premier,int duration) {
        super(title, description, forOver18, category, stars, relatives);
        this.premier = premier;
        this.duration = duration;
    }
    /***
     * Constructs a Movie object with specified attributes
     *
     * @param title       The title of the movie
     * @param description The description of the movie
     * @param forOver18   Indicates whether the movie is suitable for viewers over 18 years old
     * @param category    The category of the movie
     * @param stars       The names of star actors in the movie
     * @param premier     The premiere year of the movie
     * @param duration    The duration of the movie
     */
    public Movie(String title, String description, boolean forOver18, FilmCategory category, String stars,int premier,int duration) {
        super(title, description, forOver18, category,  stars);
        this.premier = premier;
        this.duration = duration;
    }
    /***
     * Method that returns the premiere year of the movie
     *
     * @return The premiere year of the movie
     */
    public int getPremier() {
        return premier;
    }
    /***
     * Method that sets the premiere year of the movie
     *
     * @param premier The premiere year of the movie
     */

    public void setPremier(int premier) {
        this.premier = premier;
    }
    /***
     * Method that returns the duration of the movie
     *
     * @return The duration of the movie
     */

    public int getDuration() {
        return duration;
    }
    /***
     * Method that sets the duration of the movie
     *
     * @param duration The duration of the movie
     */

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
