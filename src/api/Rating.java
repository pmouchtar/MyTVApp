package api;
/***
 * Represents a rating for a film, containing comments and a numerical rating
 */

import java.io.Serializable;

public class Rating implements Serializable {
    String comments;
    int rating;
    /***
     * Constructs a Rating object with specified attributes
     *
     * @param comments The comments associated with the rating
     * @param rating   The rating given
     */
    public Rating(String comments, int rating) {
        this.comments = comments;
        this.rating = rating;
    }
    /***
     * Method that returns the comments associated with the rating
     *
     * @return The comments associated with the rating
     */

    public String getComments() {
        return comments;
    }
    /***
     * Method that sets the comments associated with the rating
     *
     * @param comments The comments to set for the rating
     */

    public void setComments(String comments) {
        this.comments = comments;
    }
    /***
     * Method that returns the  rating
     *
     * @return The  rating
     */

    public int getRating() {
        return rating;
    }
    /***
     * Method that sets the  rating
     *
     * @param rating The rating to set
     */

    public void setRating(int rating) {
        this.rating = rating;
    }
    /***
     * Method that returns the rating and comments (string)
     *
     * @return The rating and comments (string)
     */

    public String toString(){
        return "Rating: " + getRating() + ". Comment: " + getComments();
    }
}
