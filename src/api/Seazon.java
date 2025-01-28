package api;
/***
 * Represents a season of a series, containing information about the season number, premiere date, and episodes
 */

import java.io.Serializable;
import java.util.HashMap;

public class Seazon implements Serializable {
    String number;
    String premier;
    HashMap<Integer,Integer> episodes;
    /***
     * Constructs a Seazon object with specified attributes
     *
     * @param number   The season number
     * @param premier  The premiere date of the season
     * @param episodes A mapping of episode numbers to their durations
     */
    public Seazon(String number, String premier, HashMap<Integer, Integer> episodes) {
        this.number = number;
        this.premier = premier;
        this.episodes = episodes;
    }
    /***
     * Method that returns the season number
     *
     * @return The season number
     */

    public String getNumber() {
        return number;
    }
    /***
     * Method sets the season number
     *
     * @param number The season number to set
     */

    public void setNumber(String number) {
        this.number = number;
    }
    /***
     * Method that returns the premiere date of the season
     *
     * @return The premiere date of the season
     */

    public String getPremier() {
        return premier;
    }
    /***
    * Method that sets the premiere date of the season
    *
    * @param premier The premiere date to set for the season
    */
    public void setPremier(String premier) {
        this.premier = premier;
    }
    /***
    * Method that returns the mapping of episode numbers to their durations
     *
     * @return A mapping of episode numbers to durations
     */
    public HashMap<Integer, Integer> getEpisodes() {
        return episodes;
    }
    /***
    * Method that sets the mapping of episode numbers to their durations
    *
    * @param episodes The mapping to set for the season
    */
    public void setEpisodes(HashMap<Integer, Integer> episodes) {
        this.episodes = episodes;
    }
}
