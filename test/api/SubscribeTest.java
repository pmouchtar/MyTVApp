package api;

import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import java.io.File;
import java.io.IOException;


public class SubscribeTest extends TestCase {

    @Before// Clean up the test data files before each test
    public void setUp() {
        new File("Χρήστες").delete();
        new File("Συνδρομητές").delete();
    }

    @Test
   public void testRegister() throws IOException {
        Subscribe subscribe = new Subscribe(" Χρήστης1", "Κωδικός", "Όνομα", "Επίθετο");

        assertTrue(subscribe.register("Χρήστης1", "Κωδικός", "Όνομα", "Επίθετο"));

    }
}