package api;

import java.io.Serializable;
import java.util.ArrayList;

/***
 * The abstract class for representing films
 */
public abstract class Film implements Serializable {
    String title;
    String description;
    boolean forOver18;
    FilmCategory category;
    String stars;
    String relatives;
    ArrayList<Rating> filmRating = new ArrayList<>();
    /***
     * Constructs a Film object with specified attributes
     *
     * @param title       The title of the film
     * @param description The description of the film
     * @param forOver18   Indicates whether the film is suitable for viewers over 18 years old
     * @param category    The category of the film
     * @param stars       The names of star actors in the film
     * @param relatives   The names of films similar with this
     */
    public Film(String title, String description, boolean forOver18, FilmCategory category, String stars, String relatives) {
        this.title = title;
        this.description = description;
        this.forOver18 = forOver18;
        this.category = category;
        this.stars = stars;
        this.relatives = relatives;
        //this.filmRating = null;
    }
/***
 * Constructs a Film object with specified attributes (excluding relatives)
 *
 * @param title       The title of the film
 * @param description The description of the film
 * @param forOver18   Indicates whether the film is suitable for viewers over 18 years old
 * @param category    The category of the film
 * @param stars       The names of star actors in the film
 */
    public Film(String title, String description, boolean forOver18, FilmCategory category, String stars) {
        this.title = title;
        this.description = description;
        this.forOver18 = forOver18;
        this.category = category;
        this.stars = stars;
       // this.filmRating = null;
    }
    /***
     *  Method that returns the film's rating
     *
     * @return the film's rating
     */
    public ArrayList<Rating> getFilmRating(){
        return filmRating;
    }
    /***
     * Method that calculates and returns the average rating for the film.
     *
     * @return The average rating fot the film
     */
    public double getRatingNumber(){
        int i=0;
        int counter = 0;
        for (Rating r: filmRating){
            i+=r.getRating();
            counter+=1;
        }
        return i*1.0/counter;
    }

//    public void setRating(ArrayList<rating> rate){
//        this.filmRating = rate;
//    }
    /***
     * Method that adds a rating to the list of ratings for the film
     *
     * @param r The rating to be added
     */
    public void addRating(Rating r){
        filmRating.add(r);
    }

    /***
     *  Method that returns the film's title
     *
     * @return The film's title
     */
    public String getTitle() {
        return title;
    }
    /***
     *  Method that returns the film's description
     *
     * @return The film's description
     */
    public String getDescription() {
        return description;
    }
    /***
     *  Method that returns true if the film is suitable for viewers over 18 years old , else it returns false(boolean)
     *
     *  @return true if the movie is suitable for viewers over 18 , false otherwise
     */
    public boolean isForOver18() {
        return forOver18;
    }
    /***
     *  Method that returns "true" if the film is suitable for viewers over 18 years old , else it returns "false"(string)
     *
     * @return "true" if the movie is suitable for viewers over 18 , "false" otherwise
     */
    public String isForOver18toString(){
        if (isForOver18()){
            return "true";
        }
        else {
            return "false";
        }
    }
    /***
     * Method that returns the category of the film
     *
     * @return The category of the film
     */
    public FilmCategory getCategory() {
        return category;
    }

    /***
     * Method that returns the names of star actors in the film
     *
     * @return The names of star actors
     */
    public String getStars() {
        return stars;
    }
    /***
     * Method that returns the names of films similar with this
     *
     * @return The names of similar films
     */
    public String getRelatives() {
        return relatives;
    }
    /***
     * Method that sets the title of the film
     *
     * @param title The title to set for the film
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /***
     * Method that sets the description of the film
     *
     * @param description The description to set for the film
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /***
     * Method that sets true if the film is suitable for viewers over 18 years old , else it returns false
     *
     * @param forOver18 A boolean indicating whether the film is suitable for viewers over 18
     */
    public void setForOver18(boolean forOver18) {
        this.forOver18 = forOver18;
    }
    /***
     * Method that sets the category of the film
     *
     * @param category The category to set for the film
     */
    public void setCategory(FilmCategory category) {
        this.category = category;
    }
    /***
     * Method that sets the star actors of the film
     *
     * @param * @param stars The names of the star actors to set for the film
     */
    public void setStars(String stars) {
        this.stars = stars;
    }

//    public String starsAsArray() {
//        StringBuilder builder = new StringBuilder();
//        builder.append(stars[0]);
//        for (int i=1; i<stars.length; i++) {
//            builder.append(",");
//            builder.append(stars[i]);
//        }
//        return builder.toString();
//    }
    /***
     * Method that sets the names of films similar with this
     *
     * @param relatives The names of similar films to set for the film.
     */
    public void setRelatives(String relatives) {
        this.relatives = relatives;
    }

    /***
     * Method that returns a string representation of the film, including its title and description.
     *
     * @return A string representation of the film.
     */
    public String toString(){
        return "Title: "+ getTitle() + ", Description: " + getDescription() ;
    }

//    public void setFavorite(String username){
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user_favorites/"+username))){
//            oos.writeObject(this);
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//    }

}
