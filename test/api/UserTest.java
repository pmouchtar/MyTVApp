package api;


import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;


public class UserTest extends TestCase {
    @Test
    public void testGetUsername() {
        User user = new User("Χρήστης1");
        assertEquals("Χρήστης1", user.getUsername());

        user.setUsername("Χρήστης2");
        assertEquals("Χρήστης2",user.getUsername());

    }
    @Test
    public void testLoginAdmin() {
        User user = new User();
        assertTrue(user.login("admin1", "password1"));
    }

    @Test
    public void testLoginSubscriber() {
        User user = new User();
        assertTrue(user.login("stef", "123456"));
    }
    @Test
    public void testSearchIfExists() {
        User user = new User();
        assertFalse(user.searchIfExists("stef"));
        assertTrue(user.searchIfExists("nonexist"));

    }




}