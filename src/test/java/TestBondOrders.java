import static org.junit.Assert.*;

import com.putnam.model.Bank;
import com.putnam.model.Bond;
import com.putnam.model.BondOrder;
import com.putnam.model.User;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Date;

/**
 * JUnit Test class for the User Entity Object
 */
public class TestBondOrders {

    public static User tuser;
    public static Bond tbond;

    @BeforeClass
    public static void beforeTests(){
        //Eventually add logging into tests
        System.out.print("\n\n<<<<<<<<<< START User Account Entity Object Test Suite >>>>>>>>>>\n\n");
        tuser = new User("John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0);
        tbond = new Bond("123456XY78", "Treasury", new Date(2017, 8, 16), "5YR", 4.30, new Date(2023, 8, 16), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);
    }

    @AfterClass
    public static void afterTests(){
        System.out.print("\n\n<<<<<<<<<< END User Account Entity Object Test Suite >>>>>>>>>>\n\n");
    }

    @Test
    public void testOrderTimeStamp() {
        Date ldt = new Date(2017, 8, 16);

        Date tradeDate = new Date(2017, 8, 10);
        //settlement one week after
        Date settlementDate = new Date(2017, 8, 17);

        BondOrder bondOrder = new BondOrder(ldt, tradeDate, settlementDate, 100.0, 5.0, 2, tbond, tuser);

        assertEquals(new Date(2017, 8, 16), bondOrder.getOrdertimestamp());

        assertNotEquals(new Date(2017, 8, 25), bondOrder.getOrdertimestamp());

        bondOrder.setOrdertimestamp(new Date(2017, 8, 25));

        assertNotEquals(new Date(2017, 8, 16), bondOrder.getOrdertimestamp());

        assertEquals(new Date(2017, 8, 25), bondOrder.getOrdertimestamp());
    }

    @Test
    public void testTradeDate() {
        Date ldt = new Date(2017, 8, 16);

        Date tradeDate = new Date(2017, 8, 10);
        Date tradeDate2 = new Date(2018, 3, 3);
        Date settlementDate = new Date(2025, 9, 1);

        BondOrder bondOrder = new BondOrder(ldt, tradeDate, settlementDate, 100.0, 5.0, 2, tbond, tuser);

        assertEquals(tradeDate, bondOrder.getTradedate());

        assertNotEquals(tradeDate2, bondOrder.getTradedate());

        bondOrder.setTradedate(tradeDate2);

        assertNotEquals(tradeDate, bondOrder.getTradedate());

        assertEquals(tradeDate2, bondOrder.getTradedate());
    }

    @Test
    public void testSettlementDate() {
        Date ldt = new Date(2017, 8, 16);

        Date tradeDate = new Date(2017, 8, 10);
        Date settlementDate = new Date(2025, 9, 1);
        Date settlementDate2 = new Date(20305, 3, 3);

        BondOrder bondOrder = new BondOrder(ldt, tradeDate, settlementDate, 100.0, 5.0, 2, tbond, tuser);

        assertEquals(settlementDate, bondOrder.getSettlementdate());

        assertNotEquals(settlementDate2, bondOrder.getSettlementdate());

        bondOrder.setSettlementdate(settlementDate2);

        assertNotEquals(settlementDate, bondOrder.getSettlementdate());

        assertEquals(settlementDate2, bondOrder.getSettlementdate());
    }

    @Test
    public void testTransactionAmt() {
        Date ldt = new Date(2017, 8, 16);

        Date tradeDate = new Date(2017, 8, 10);
        Date settlementDate = new Date(2025, 9, 1);

        BondOrder bondOrder = new BondOrder(ldt, tradeDate, settlementDate, 100.0, 5.0, 2, tbond, tuser);

        assertEquals(100.0, bondOrder.getTransactionamt(), 0.00);

        assertNotEquals(200.0, bondOrder.getTransactionamt(), 0.00);

        bondOrder.setTransactionamt(200.0);

        assertNotEquals(100.0, bondOrder.getTransactionamt(), 0.00);

        assertEquals(200.0, bondOrder.getTransactionamt(), 0.00);
    }

    @Test
    public void testAccruedInterest() {
        Date ldt = new Date(2017, 8, 16);

        Date tradeDate = new Date(2017, 8, 10);
        Date settlementDate = new Date(2025, 9, 1);

        BondOrder bondOrder = new BondOrder(ldt, tradeDate, settlementDate, 100.0, 5.0, 2, tbond, tuser);

        assertEquals(5.0, bondOrder.getAccruedinterest(), 0.00);

        assertNotEquals(2.3, bondOrder.getAccruedinterest(), 0.00);

        bondOrder.setAccruedinterest(2.3);

        assertNotEquals(5.0, bondOrder.getAccruedinterest(), 0.00);

        assertEquals(2.3, bondOrder.getAccruedinterest(), 0.00);
    }

    @Test
    public void testNumBondsPurchased() {
        Date ldt = new Date(2017, 8, 16);

        Date tradeDate = new Date(2017, 8, 10);
        Date settlementDate = new Date(2025, 9, 1);

        BondOrder bondOrder = new BondOrder(ldt, tradeDate, settlementDate, 100.0, 5.0, 2, tbond, tuser);

        assertEquals(2, bondOrder.getNumBondspurchased(), 0.00);

        assertNotEquals(10, bondOrder.getNumBondspurchased(), 0.00);

        bondOrder.setNumBondspurchased(10);

        assertNotEquals(2, bondOrder.getNumBondspurchased(), 0.00);

        assertEquals(10, bondOrder.getNumBondspurchased(), 0.00);
    }

    @Test
    public void testToString() {
        Date ldt = new Date(2017, 8, 16);
        Date tradeDate = new Date(2017, 8, 10);
        Date settlementDate = new Date(2025, 9, 1);
        BondOrder bondOrder = new BondOrder(ldt, tradeDate, settlementDate, 100.0, 5.0, 2, tbond, tuser);

        assertNotNull(bondOrder.toString());
    }

    @Test
    public void testBond() {
        Date ldt = new Date(2017, 8, 16);
        Date tradeDate = new Date(2017, 8, 10);
        Date settlementDate = new Date(2025, 9, 1);
        Date issueDate = new Date(2017, 8, 10);

        BondOrder bondOrder = new BondOrder(ldt, tradeDate, settlementDate, 100.0, 5.0, 2, tbond, tuser);

        Bond bond = new Bond("123456XY78", "Treasury", issueDate, "5YR", 4.30, settlementDate, 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);
        Bond bond2 = new Bond("123456XY78", "Treasury", issueDate, "5YR", 4.30, settlementDate, 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        bondOrder.setBond(bond);

        assertEquals(bond, bondOrder.getBond());

        assertNotEquals(bond2,  bondOrder.getBond());

        bondOrder.setBond(bond2);

        assertNotEquals(bond, bondOrder.getBond());

        assertEquals(bond2, bondOrder.getBond());
    }

    @Test
    public void testUser() {
        Date ldt = new Date(2017, 8, 16);
        Date tradeDate = new Date(2017, 8, 10);
        Date settlementDate = new Date(2025, 9, 1);

        BondOrder bondOrder = new BondOrder(ldt, tradeDate, settlementDate, 100.0, 5.0, 2, tbond, tuser);

        User user = new User("John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0);
        User user2 = new User("Nancy", "Drew", "5082223409", "nancydrew@gmail.com", "password2", "022657788", "7766", "AAAAAAAAAAAAAAAA", "6 Main Street", "Boston", "MA", "02129", 200.0);

        bondOrder.setUser(user);

        assertEquals(user, bondOrder.getUser());

        assertNotEquals(user2,  bondOrder.getUser());

        bondOrder.setUser(user2);

        assertNotEquals(user, bondOrder.getUser());

        assertEquals(user2, bondOrder.getUser());
    }


}
