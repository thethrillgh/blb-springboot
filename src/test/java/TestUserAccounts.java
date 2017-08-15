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
    public void beforeTests(){
        //Eventually add logging into tests
        System.out.print("\n\n<<<<<<<<<< START User Account Entity Object Test Suite >>>>>>>>>>\n\n");
    }

    @AfterClass
    public void afterTests(){
        System.out.print("\n\n<<<<<<<<<< END User Account Entity Object Test Suite >>>>>>>>>>\n\n");
    }

    @Test
    public void testUserVars(){
        User user = new User("John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0);

        assertEquals("John", user.getFirstname());

        assertNotEquals("Alex", user.getFirstname());
    }

}
