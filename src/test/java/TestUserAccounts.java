import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

import com.putnam.model.User;

/**
 * JUnit Test class for the User Entity Object
 */
public class TestUserAccounts {

    @BeforeClass
    public static void beforeTests(){
        //Eventually add logging into tests
        System.out.print("\n\n<<<<<<<<<< START User Account Entity Object Test Suite >>>>>>>>>>\n\n");
    }

    @AfterClass
    public static void afterTests(){
        System.out.print("\n\n<<<<<<<<<< END User Account Entity Object Test Suite >>>>>>>>>>\n\n");
    }

    @Test
    public void testFirstName(){
        User user = new User("John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0);

        assertEquals("John", user.getFirstname());

        assertNotEquals("Alex", user.getFirstname());

        user.setFirstname("James");

        assertNotEquals("John", user.getFirstname());

        assertEquals("James", user.getFirstname());
    }

    @Test
    public void testLastName(){
        User user = new User("John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0);

        assertEquals("Doe", user.getLastname());

        assertNotEquals("Smith", user.getLastname());

        user.setLastname("Johnson");

        assertNotEquals("Doe", user.getLastname());

        assertEquals("Johnson", user.getLastname());
    }

    @Test
    public void testPhoneNum(){
        User user = new User("John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0);

        assertEquals("5089993453", user.getPhonenum());

        assertNotEquals("5089990000", user.getPhonenum());

        user.setPhonenum("6177779898");

        assertNotEquals("5089993453", user.getPhonenum());

        assertEquals("6177779898", user.getPhonenum());
    }

    @Test
    public void testAcctEmail(){
        User user = new User("John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0);

        assertEquals("johndoe@gmail.com", user.getAcctemail());

        assertNotEquals("johndoe@yahoo.com", user.getAcctemail());

        user.setAcctemail("johndoe@yahoo.com");

        assertNotEquals("johndoe@gmail.com", user.getAcctemail());

        assertEquals("johndoe@yahoo.com", user.getAcctemail());
    }

    @Test
    public void testAcctPass(){
        User user = new User("John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0);

        assertEquals("password1", user.getAcctpass());

        assertNotEquals("somepassword", user.getAcctpass());

        user.setAcctpass("somepassword");

        assertNotEquals("password1", user.getAcctpass());

        assertEquals("somepassword", user.getAcctpass());
    }

    @Test
    public void testAcctSSN(){
        User user = new User("John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0);

        assertEquals("022657766", user.getAcctssn());

        assertNotEquals("111223333", user.getAcctssn());

        user.setAcctssn("111223333");

        assertNotEquals("022657766", user.getAcctssn());

        assertEquals("111223333", user.getAcctssn());
    }
}
