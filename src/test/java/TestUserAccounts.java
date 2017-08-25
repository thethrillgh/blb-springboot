import static org.junit.Assert.*;

import com.putnam.model.Bank;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

import com.putnam.model.User;

import java.util.ArrayList;
import java.util.List;

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
        User user = new User( "John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0, 90.0);

        assertEquals("John", user.getFirstname());

        assertNotEquals("Alex", user.getFirstname());

        user.setFirstname("James");

        assertNotEquals("John", user.getFirstname());

        assertEquals("James", user.getFirstname());
    }

    @Test
    public void testLastName(){
        User user = new User( "John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0, 90.0);

        assertEquals("Doe", user.getLastname());

        assertNotEquals("Smith", user.getLastname());

        user.setLastname("Johnson");

        assertNotEquals("Doe", user.getLastname());

        assertEquals("Johnson", user.getLastname());
    }

    @Test
    public void testPhoneNum(){
        User user = new User( "John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0, 90.0);

        assertEquals("5089993453", user.getPhonenum());

        assertNotEquals("5089990000", user.getPhonenum());

        user.setPhonenum("6177779898");

        assertNotEquals("5089993453", user.getPhonenum());

        assertEquals("6177779898", user.getPhonenum());
    }

    @Test
    public void testAcctEmail(){
        User user = new User( "John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0, 90.0);

        assertEquals("johndoe@gmail.com", user.getAcctemail());

        assertNotEquals("johndoe@yahoo.com", user.getAcctemail());

        user.setAcctemail("johndoe@yahoo.com");

        assertNotEquals("johndoe@gmail.com", user.getAcctemail());

        assertEquals("johndoe@yahoo.com", user.getAcctemail());
    }

    @Test
    public void testAcctPass(){
        User user = new User( "John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0, 90.0);

        assertEquals("password1", user.getAcctpass());

        assertNotEquals("somepassword", user.getAcctpass());

        user.setAcctpass("somepassword");

        assertNotEquals("password1", user.getAcctpass());

        assertEquals("somepassword", user.getAcctpass());
    }

    @Test
    public void testAcctSSN(){
        User user = new User( "John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0, 90.0);

        assertEquals("022657766", user.getAcctssn());

        assertNotEquals("111223333", user.getAcctssn());

        user.setAcctssn("111223333");

        assertNotEquals("022657766", user.getAcctssn());

        assertEquals("111223333", user.getAcctssn());
    }

    @Test
    public void testAcctSSNLastFour(){
        User user = new User( "John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0, 90.0);

        assertEquals("7766", user.getSsnlastfour());

        assertNotEquals("3333", user.getSsnlastfour());

        user.setSsnlastfour("3333");

        assertNotEquals("7766", user.getSsnlastfour());

        assertEquals("3333", user.getSsnlastfour());
    }

    @Test
    public void testPassSalt(){
        User user = new User( "John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0, 90.0);

        assertNotNull(user.getPasssalt());

    }

    @Test
    public void testStreetAddress(){
        User user = new User( "John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0, 90.0);

        assertEquals("4 Main Street", user.getStreetaddress());

        assertNotEquals("10 School Street", user.getStreetaddress());

        user.setStreetaddress("10 School Street");

        assertNotEquals("4 Main Street", user.getStreetaddress());

        assertEquals("10 School Street", user.getStreetaddress());
    }

    @Test
    public void testCity(){
        User user = new User( "John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0, 90.0);

        assertEquals("Boston", user.getCity());

        assertNotEquals("Cambridge", user.getCity());

        user.setCity("Cambridge");

        assertNotEquals("Boston", user.getCity());

        assertEquals("Cambridge", user.getCity());
    }

    @Test
    public void testState(){
        User user = new User( "John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0, 90.0);

        assertEquals("MA", user.getState());

        assertNotEquals("CA", user.getState());

        user.setState("CA");

        assertNotEquals("MA", user.getState());

        assertEquals("CA", user.getState());
    }

    @Test
    public void testPostalCode(){
        User user = new User( "John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0, 90.0);

        assertEquals("02129", user.getPostalcode());

        assertNotEquals("01752", user.getPostalcode());

        user.setPostalcode("01752");

        assertNotEquals("02129", user.getPostalcode());

        assertEquals("01752", user.getPostalcode());
    }

    @Test
    public void testAcctBal(){
        User user = new User( "John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0, 90.0);

        assertEquals(100.0, user.getAcctbalance(), 0.00);

        assertNotEquals(50.0, user.getAcctbalance() , 0.00);

        user.setAcctbalance(50.0);

        assertNotEquals(100.0, user.getAcctbalance(), 0.00);

        assertEquals(50.0, user.getAcctbalance(), 0.00);
    }

    @Test
    public void testUserId(){
        User user = new User( "John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0, 90.0);

        assertNotNull( (Long) user.getUserid());

        user.setUserid(10);

        assertEquals(10, user.getUserid(), 0.00);

    }

    @Test
    public void testToString(){
        User user = new User( "John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0, 90.0);

        String obj = user.toString();

        assertNotNull(obj);
    }
}
