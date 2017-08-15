import static org.junit.Assert.*;

import com.putnam.model.Bank;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

import com.putnam.model.User;

/**
 * JUnit Test class for the User Entity Object
 */
public class TestBankAccounts {

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
    public void testAcctNum(){
        Bank bank = new Bank("1234567890", "111222333", "Checking");

        assertEquals("1234567890", bank.getAcctnum());

        assertNotEquals("0987654321", bank.getAcctnum());

        bank.setAcctnum("1234554321");

        assertNotEquals("1234567890", bank.getAcctnum());

        assertEquals("1234554321", bank.getAcctnum());
    }

    @Test
    public void testRoutingNum() {
        Bank bank = new Bank("1234567890", "111222333", "Checking");

        assertEquals("111222333", bank.getRoutingnum());

        assertNotEquals("888555222", bank.getRoutingnum());

        bank.setRoutingnum("222111000");

        assertNotEquals("111222333", bank.getRoutingnum());

        assertEquals("222111000", bank.getRoutingnum());
    }

    @Test
    public void testAcctType() {
        Bank bank = new Bank("1234567890", "111222333", "Checking");

        assertEquals("Checking", bank.getAccttype());

        assertNotEquals("Savings", bank.getAccttype());

        bank.setAccttype("Savings");

        assertNotEquals("Checking", bank.getAccttype());

        assertEquals("Savings", bank.getAccttype());
    }
}
