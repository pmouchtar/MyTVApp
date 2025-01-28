package api;

import java.io.*;
import java.util.ArrayList;

public class Controller implements Serializable{
    /***
     * Class that manages films (series and movies) and performs searches
     */

    ArrayList<Movie> movies; //= new ArrayList<>();
    ArrayList<Series> series; //= new ArrayList<>();
    ArrayList<Film> films; //= new ArrayList<>();
    ArrayList<Film> favoriteFilms;
    String username;

    /***
     * Constructs a Controller object for a user
     * @param username the username of the user
     * @param i parameter
     */

    public Controller(String username,int i) {
        movies = new ArrayList<>();
        series = new ArrayList<>();
        films = new ArrayList<>();
        if (i==0) {
            favoriteFilms = loadFavorites(username);
        }
        this.username=username;
    }

    /***
     * Method that returns the username of the user
     *
     * @return the username of the user
     */
    public String getUsername(){
        return this.username;
    }

    /***
     * Method that adds a movie to the list of movies (and films)
     *
     * @param m movie to add to the list
     */

    public void add(Movie m){
        movies.add(m);
        films.add(m);
    }
    /***
     * Method that adds a series to the list of series (and films)
     *
     * @param s series to add to the list
     */
    public void add(Series s){
        series.add(s);
        films.add(s);
    }
    /***
     * Method that deletes a movie to the list of movies (and films)
     *
     * @param m movie to delete to the list
     */

    public void delete(Movie m){
        movies.remove(m);
        films.remove(m);
    }
    /***
     * Method that deletes a sereis to the list of movies (and films)
     *
     * @param s series to delete to the list
     */
    public void delete(Series s){
        series.remove(s);
        films.remove(s);
    }

    /***
     * Method that returns the list of movies
     *
     * @return The list of movies
     */
    public ArrayList<Movie> getMovies(){
        return movies;
    }
    /***
     * Method that returns the list of series
     *
     * @return The list of series
     */

    public ArrayList<Series> getSeries(){
        return series;
    }
    /***
     * Method that returns the list of films
     *
     * @return The list of films
     */
    public ArrayList<Film> getFilms(){
        ArrayList<Film> films1=new ArrayList<>();
        films1.addAll(movies);
        films1.addAll(series);
        return films1;
    }

    /***
     * Method that searches for a film (series and movie) by their title
     *
     * @param title The title of the film
     * @return The list of films with the specific title
     */
    public ArrayList<Film> searchByTitle(String title){
        ArrayList<Film> titleFilms = new ArrayList<>();
        for (Movie m: movies){
            if (m.getTitle().equals(title)){
                titleFilms.add(m);
            }
        }
        for (Series s: series){
            if (s.getTitle().equals(title)){
                titleFilms.add(s);
            }
        }
        return titleFilms;
    }
    /***
     * Method that searches for a film by their type (series/movie)
     *
     * @param type The title of the film
     * @return The list of films with the specific type
     */


    public ArrayList<Film> searchByType(String type){
        ArrayList<Film> typeFilms=new ArrayList<>();
        if (type.equals("Ταινία")){
            typeFilms.addAll(movies);
        }
        else if (type.equals("Σειρά")){
            typeFilms.addAll(series);
        }
        return typeFilms;
    }
    /***
     * Method that searches for a film (series and movie) by their star actor
     *
     * @param star The star actor of the film
     * @return The list of films with the specific star actor
     */

    public ArrayList<Film> searchByStar(String star){
        ArrayList<Film> starFilms = new ArrayList<>();
        for (Movie m: movies){
            String[] starsArray = m.getStars().split(",");
            for (String s: starsArray){
                if (s.equals(star)){
                    starFilms.add(m);
                }
            }
        }
        for (Series se: series){
            String[] starsArrayS = se.getStars().split(",");
            for (String s: starsArrayS){
                if (s.equals(star)){
                    starFilms.add(se);
                }
            }
        }
        return starFilms;
    }
    /***
     * Method that searches for films based on whether they are suitable for viewers under 18
     *
     * @param under18 true if searching for films suitable for viewers under 18, false otherwise
     * @return A list of films based on the specified suitability
     */

    public ArrayList<Film> searchByProperness(boolean under18){
        ArrayList<Film> filmsUnder18= new ArrayList<>();
        if (under18) {
            for (Movie m : movies) {
                if (!m.isForOver18()){
                    filmsUnder18.add(m);
                }
            }
            for (Series s : series) {
                if (!s.isForOver18()){
                    filmsUnder18.add(s);
                }
            }
            return filmsUnder18;
        }
        else {
            return films;
        }
    }
    /***
     * Method that searches for a film (series and movie) by their category
     *
     * @param category The category of the film
     * @return The list of films of the specific category
     */


    public ArrayList<Film> searchByCategory(String category){
        ArrayList<Film> categoryFilms = new ArrayList<>();
        FilmCategory filmCategory = FilmCategory.valueOf(category);
        for (Movie m: movies){
            if (m.getCategory().equals(filmCategory)){
                categoryFilms.add(m);
            }
        }
        for (Series s: series){
            if (s.getCategory().equals(filmCategory)){
                categoryFilms.add(s);
            }
        }
        return categoryFilms;
    }
    /***
     * Method that searches for a film (series and movie) by their ratting
     *
     * @param rate The ratting of the film
     * @return The list of films with ratting equal or higher than the specific ratting
     */


    public ArrayList<Film> searchByRate(int rate){
        ArrayList<Film> rateFilms = new ArrayList<>();
        for (Movie m: movies){
            if (m.getRatingNumber()>=1.0*rate){
                rateFilms.add(m);
            }
        }
        for (Series s: series){
            if (s.getRatingNumber()>=1.0*rate){
                rateFilms.add(s);
            }
        }
        return rateFilms;
    }
    /*κάνει save το ολόκληρο το αντικείμενο στο αρχείο films_database.bin*/
    public void save(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("films_database.bin"))){
            oos.writeObject(this);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    /*κάνει load το ολόκληρο το αντικείμενο από το αρχείο films_database.bin και μετά γεμίζει τις λίστες με τις τιμές τους*/
    public Controller load(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("films_database.bin"))){
            Controller controller = (Controller) ois.readObject();
            movies = controller.movies;
            series = controller.series;
            films = controller.films;
            return controller;
        }
        catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    /***
     * Method that loads the favorite films for a specific user from the file
     *
     * @param username The username of the user
     * @return A list of favorite films for the specified user
     */

    public ArrayList<Film> loadFavorites(String username){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user_favorites/"+username))){
            ArrayList<Film> favorites = (ArrayList<Film>) ois.readObject();
            favoriteFilms = favorites;
            return favorites;
        }
        catch (IOException | ClassNotFoundException e){
            System.out.println("found");
            e.printStackTrace();
        }
        return null;
    }

    /***
     * Method that returns the favorite films of the user
     *
     * @param username the username of the user
     * @return The list of favorite films of the user
     */

    public ArrayList<Film> getFavoriteFilms(String username){
        return favoriteFilms;
    }
    /***
     * Method that adds a film to the list of favorite films for the user
     *
     * @param f The film to be added to favorites
     * @param username The username of the user
     */
    public void setFavorite(Film f,String username){
        favoriteFilms.add(f);
        saveFavorites(username);
    }
    /***
     * Method that deletes a film from the list of favorite films for the user
     *
     * @param f The film to be deleted from favorites
     * @param username The username of the user
     */

    public void removeFavorite(Film f,String username){
        favoriteFilms.remove(f);
        saveFavorites(username);
    }
    /***
     * Method taht saves the list of favorite films for the user to the file
     *
     * @param username The username of the user
     */

    public void saveFavorites(String username){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user_favorites/"+ username))){
            oos.writeObject(favoriteFilms);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    /* Δεν χρειάζεται*/
    public String toString(){
        return getMovies().toString()+getSeries().toString();
    }


}