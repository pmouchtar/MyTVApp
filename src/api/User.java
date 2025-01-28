package api;

import gui.AdminInterface;
import gui.SubscriberInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.String;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

import static java.lang.String.*;
/***
 * Represents a user, either an admin or a subscriber
 */
public class User {
    String username;

    ArrayList<Film> favouriteFilms;
    /***
     * Constructs a User object and attempts to log in with the provided credentials
     *
     * @param username The username for the user
     */
    public User(String username) {
        this.username = username;
    }
    /***
     * Constructs an empty User object
     */
    public User() {
    }
    /***
     * Method that returns the username of the user
     *
     * @return The username of the user
     */
    public String getUsername() {
        return username;
    }
    /***
     * * Method sets the username of the user
     *
     * @param username The username to set for the user
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /***
     * Method that attempts to log in with the provided username and password
     *
     * @param username The username for the login attempt
     * @param password The password for the login attempt
     * @return The username if the login is successful
     */

    public boolean login(String username,String password){
        String temp = "";
        try (BufferedReader in = new BufferedReader(new FileReader("admin_list"))) {
            temp = in.readLine();
            while (temp != null) {
                if (temp.equals(username)) {
                    temp = in.readLine();
                    if (temp.equals(password)) {
                        //access
                        AdminInterface adminInterface = new AdminInterface(username);
                        return true;
                    }
                } else {
                    in.readLine();
                }
                temp = in.readLine();
            }
        } catch (IOException e) {
            System.out.println("Δεν βρέθηκε το αρχείο");
        }
        if (temp == null) {
            try (BufferedReader in = new BufferedReader(new FileReader("subscribers_list"))) {
                temp = in.readLine();
                while (temp != null) {
                    if (temp.equals(username)) {
                        temp = in.readLine();
                        if (temp.equals(password)) {
                            //access
                            SubscriberInterface subscriberInterface = new SubscriberInterface(username);
                            return true;
                        }
                    } else {
                        in.readLine();
                    }
                    temp = in.readLine();
                }
                if (temp == null) {
                    //System.out.println("Λάθος όνομα ή κωδικός");
                }
            } catch (IOException e) {
                System.out.println("Δεν βρέθηκε το αρχείο");
            }
        }
        return false;
    }
    /***
     * Method that checks if the provided username already exists in the subscribers list
     *
     * @param username The username to check for existence
     * @return true if the username exits, otherwise false
     */
    public boolean searchIfExists(String username) {
        String temp;
        try (BufferedReader in = new BufferedReader(new FileReader("subscribers_list"))) {
            temp = in.readLine();
            while (temp!= null){
                if (temp.equals(username)){
                    return false;
                }
                temp = in.readLine();
            }
        }
        catch (IOException e){
            System.out.println("Δεν βρέθηκε αρχείο");
        }
        return true;
    }
}
