package api;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/***
 * Represents a subscriber, extending the base class User
 * Subscribers have the ability to register and create a list of favorites
 */
public class Subscribe extends User{
    /***
     * Constructs a Subscribe object and registers a new subscriber with the provided credentials
     *
     * @param username The username for the new subscriber
     * @param password The password for the new subscriber
     * @param name     The name of the new subscriber
     * @param surname  The surname of the new subscriber
     */

    public Subscribe(String username,String password,String name,String surname) throws IOException {
        register(username,password,name,surname);
    }
    /***
     * Method that egisters a new subscriber with the provided credentials
     *
     * @param username The username for the new subscriber
     * @param password The password for the new subscriber
     * @param name     The name of the new subscriber
     * @param surname  The surname of the new subscriber
     * @return true if registration is successful , false if the username already exists
     */
    public boolean register(String username,String password,String name,String surname) throws IOException {

        String temp = "";
        if (!searchIfExists(username)){
            return false;
        }
        try (PrintWriter out = new PrintWriter(new FileWriter("subscribers_list",true))){
            out.println(username);
            out.println(password);
        }
        catch (IOException e){
            System.out.println("Δεν βρέθηκε αρχείο");
        }
        File file = new File("user_favorites",username);
        file.createNewFile();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user_favorites/"+ username))){
            Film film = new Movie("","",true,FilmCategory.ACTION,"","",0,0);
            ArrayList<Film> films=new ArrayList<>();
            films.add(film);
            oos.writeObject(films);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return true;


    }
}
