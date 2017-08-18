import static org.junit.Assert.*;

import com.putnam.model.Bank;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

import com.putnam.model.User;

import java.util.ArrayList;

/**
 * JUnit Test class for the Bank Entity Object
 */
public class TestBankAccounts {

    public static User tuser;
    @BeforeClass
    public static void beforeTests(){
        //Eventually add logging into tests
        System.out.print("\n\n<<<<<<<<<< START Bank Account Entity Object Test Suite >>>>>>>>>>\n\n");
        tuser = new User(new ArrayList<Bank>(), "John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0);

    }

    @AfterClass
    public static void afterTests(){
        System.out.print("\n\n<<<<<<<<<< END Bank Account Entity Object Test Suite >>>>>>>>>>\n\n");
    }

    @Test
    public void testAcctNum(){
        Bank bank = new Bank("1234567890", "111222333", "Checking", tuser);

        assertEquals("1234567890", bank.getAcctnum());

        assertNotEquals("0987654321", bank.getAcctnum());

        bank.setAcctnum("1234554321");

        assertNotEquals("1234567890", bank.getAcctnum());

        assertEquals("1234554321", bank.getAcctnum());
    }

    @Test
    public void testRoutingNum() {
        Bank bank = new Bank("1234567890", "111222333", "Checking", tuser);

        assertEquals("111222333", bank.getRoutingnum());

        assertNotEquals("888555222", bank.getRoutingnum());

        bank.setRoutingnum("222111000");

        assertNotEquals("111222333", bank.getRoutingnum());

        assertEquals("222111000", bank.getRoutingnum());
    }

    @Test
    public void testAcctType() {
        Bank bank = new Bank("1234567890", "111222333", "Checking", tuser);

        assertEquals("Checking", bank.getAccttype());

        assertNotEquals("Savings", bank.getAccttype());

        bank.setAccttype("Savings");

        assertNotEquals("Checking", bank.getAccttype());

        assertEquals("Savings", bank.getAccttype());
    }

    @Test
    public void testUser() {
        Bank bank = new Bank("1234567890", "111222333", "Checking", tuser);

        User user = new User(new ArrayList<Bank>(), "John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0);
        User user2 = new User(new ArrayList<Bank>(), "Nancy", "Drew", "5082223409", "nancydrew@gmail.com", "password2", "022657788", "7766", "AAAAAAAAAAAAAAAA", "6 Main Street", "Boston", "MA", "02129", 200.0);

        bank.setUser(user);

        assertEquals(user, bank.getUser());

        assertNotEquals(user2,  bank.getUser());

        bank.setUser(user2);

        assertNotEquals(user, bank.getUser());

        assertEquals(user2, bank.getUser());
    }

    @Test
    public void testToString(){
        Bank bank = new Bank("1234567890", "111222333", "Checking", tuser);

        String obj = bank.toString();

        assertNotNull(obj);
    }
}
