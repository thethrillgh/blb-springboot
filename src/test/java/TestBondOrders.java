import static org.junit.Assert.*;

import com.putnam.model.Bond;
import com.putnam.model.BondOrder;
import com.putnam.model.User;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;

/**
 * JUnit Test class for the User Entity Object
 */
public class TestBondOrders {

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
    public void testOrderTimeStamp() {
        LocalDateTime ldt = LocalDateTime.of(2017, Month.AUGUST, 16, 12, 0);

        Date tradeDate = new Date(2017, 8, 10);
        //settlement one week after
        Date settlementDate = new Date(2017, 8, 17);

        BondOrder bondOrder = new BondOrder(ldt, tradeDate, settlementDate, 100.0, 5.0, 2);

        assertEquals(LocalDateTime.of(2017, Month.AUGUST, 16, 12, 0), bondOrder.getOrdertimestamp());

        assertNotEquals(LocalDateTime.of(2017, Month.AUGUST, 25, 12, 0), bondOrder.getOrdertimestamp());

        bondOrder.setOrdertimestamp(LocalDateTime.of(2017, Month.AUGUST, 25, 12, 0));

        assertNotEquals(LocalDateTime.of(2017, Month.AUGUST, 16, 12, 0), bondOrder.getOrdertimestamp());

        assertEquals(LocalDateTime.of(2017, Month.AUGUST, 25, 12, 0), bondOrder.getOrdertimestamp());
    }

    @Test
    public void testTradeDate() {
        LocalDateTime ldt = LocalDateTime.of(2017, Month.AUGUST, 16, 12, 0);

        Date tradeDate = new Date(2017, 8, 10);
        Date tradeDate2 = new Date(2018, 3, 3);
        Date settlementDate = new Date(2025, 9, 1);

        BondOrder bondOrder = new BondOrder(ldt, tradeDate, settlementDate, 100.0, 5.0, 2);

        assertEquals(tradeDate, bondOrder.getTradedate());

        assertNotEquals(tradeDate2, bondOrder.getTradedate());

        bondOrder.setTradedate(tradeDate2);

        assertNotEquals(tradeDate, bondOrder.getTradedate());

        assertEquals(tradeDate2, bondOrder.getTradedate());
    }

    @Test
    public void testSettlementDate() {
        LocalDateTime ldt = LocalDateTime.of(2017, Month.AUGUST, 16, 12, 0);

        Date tradeDate = new Date(2017, 8, 10);
        Date settlementDate = new Date(2025, 9, 1);
        Date settlementDate2 = new Date(20305, 3, 3);

        BondOrder bondOrder = new BondOrder(ldt, tradeDate, settlementDate, 100.0, 5.0, 2);

        assertEquals(settlementDate, bondOrder.getSettlementdate());

        assertNotEquals(settlementDate2, bondOrder.getSettlementdate());

        bondOrder.setSettlementdate(settlementDate2);

        assertNotEquals(settlementDate, bondOrder.getSettlementdate());

        assertEquals(settlementDate2, bondOrder.getSettlementdate());
    }

    @Test
    public void testTransactionAmt() {
        LocalDateTime ldt = LocalDateTime.of(2017, Month.AUGUST, 16, 12, 0);

        Date tradeDate = new Date(2017, 8, 10);
        Date settlementDate = new Date(2025, 9, 1);

        BondOrder bondOrder = new BondOrder(ldt, tradeDate, settlementDate, 100.0, 5.0, 2);

        assertEquals(100.0, bondOrder.getTransactionamt(), 0.00);

        assertNotEquals(200.0, bondOrder.getTransactionamt(), 0.00);

        bondOrder.setTransactionamt(200.0);

        assertNotEquals(100.0, bondOrder.getTransactionamt(), 0.00);

        assertEquals(200.0, bondOrder.getTransactionamt(), 0.00);
    }

    @Test
    public void testAccruedInterest() {
        LocalDateTime ldt = LocalDateTime.of(2017, Month.AUGUST, 16, 12, 0);

        Date tradeDate = new Date(2017, 8, 10);
        Date settlementDate = new Date(2025, 9, 1);

        BondOrder bondOrder = new BondOrder(ldt, tradeDate, settlementDate, 100.0, 5.0, 2);

        assertEquals(5.0, bondOrder.getAccruedinterest(), 0.00);

        assertNotEquals(2.3, bondOrder.getAccruedinterest(), 0.00);

        bondOrder.setAccruedinterest(2.3);

        assertNotEquals(5.0, bondOrder.getAccruedinterest(), 0.00);

        assertEquals(2.3, bondOrder.getAccruedinterest(), 0.00);
    }

    @Test
    public void testNumBondsPurchased() {
        LocalDateTime ldt = LocalDateTime.of(2017, Month.AUGUST, 16, 12, 0);

        Date tradeDate = new Date(2017, 8, 10);
        Date settlementDate = new Date(2025, 9, 1);

        BondOrder bondOrder = new BondOrder(ldt, tradeDate, settlementDate, 100.0, 5.0, 2);

        assertEquals(2, bondOrder.getNumBondspurchased(), 0.00);

        assertNotEquals(10, bondOrder.getNumBondspurchased(), 0.00);

        bondOrder.setNumBondspurchased(10);

        assertNotEquals(2, bondOrder.getNumBondspurchased(), 0.00);

        assertEquals(10, bondOrder.getNumBondspurchased(), 0.00);
    }


    @Test
    public void testUserId() {
        LocalDateTime ldt = LocalDateTime.of(2017, Month.AUGUST, 16, 12, 0);

        Date tradeDate = new Date(2017, 8, 10);
        Date settlementDate = new Date(2025, 9, 1);

        BondOrder bondOrder = new BondOrder(ldt, tradeDate, settlementDate, 100.0, 5.0, 2);

        assertNotNull( (Long) bondOrder.getUserid());

        bondOrder.setUserid(10);

        assertEquals(10, bondOrder.getUserid(), 0.00);
    }

    @Test
    public void testBondId() {
        LocalDateTime ldt = LocalDateTime.of(2017, Month.AUGUST, 16, 12, 0);

        Date tradeDate = new Date(2017, 8, 10);
        Date settlementDate = new Date(2025, 9, 1);

        BondOrder bondOrder = new BondOrder(ldt, tradeDate, settlementDate, 100.0, 5.0, 2);

        assertNotNull( (Long) bondOrder.getBondid());

        bondOrder.setBondid(10);

        assertEquals(10, bondOrder.getBondid(), 0.00);
    }

    @Test
    public void testBond() {
        LocalDateTime ldt = LocalDateTime.of(2017, Month.AUGUST, 16, 12, 0);

        Date tradeDate = new Date(2017, 8, 10);
        Date settlementDate = new Date(2025, 9, 1);

        BondOrder bondOrder = new BondOrder(ldt, tradeDate, settlementDate, 100.0, 5.0, 2);

        Bond bond = new Bond("123456XY78", "Treasury", LocalDate.of(2017, Month.AUGUST, 10), "5YR", 4.30, LocalDate.of(2023, Month.AUGUST, 10), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);
        Bond bond2 = new Bond("654321XY78", "Treasury", LocalDate.of(2019, Month.AUGUST, 13), "5YR", 4.30, LocalDate.of(2025, Month.AUGUST, 10), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        bondOrder.setBond(bond);

        assertEquals(bond, bondOrder.getBond());

        assertNotEquals(bond2, bondOrder.getBond());

        bondOrder.setBond(bond2);

        assertNotEquals(bond, bondOrder.getBond());

        assertEquals(bond2, bondOrder.getBond());
    }

    @Test
    public void testUser() {
        LocalDateTime ldt = LocalDateTime.of(2017, Month.AUGUST, 16, 12, 0);

        Date tradeDate = new Date(2017, 8, 10);
        Date settlementDate = new Date(2025, 9, 1);

        BondOrder bondOrder = new BondOrder(ldt, tradeDate, settlementDate, 100.0, 5.0, 2);

        User user = new User("John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0);
        User user2 = new User("Nancy", "Drew", "5089993455", "nancydrew@gmail.com", "password2", "022657788", "7788", "AAAAAAAAAAAAAAAA", "5 Main Street", "Boston", "MA", "02129", 200.0);

        bondOrder.setUser(user);

        assertEquals(user, bondOrder.getUser());

        assertNotEquals(user2, bondOrder.getUser());

        bondOrder.setUser(user2);

        assertNotEquals(user, bondOrder.getUser());

        assertEquals(user2, bondOrder.getUser());
    }

    @Test
    public void testID() {
        LocalDateTime ldt = LocalDateTime.of(2017, Month.AUGUST, 16, 12, 0);

        Date tradeDate = new Date(2017, 8, 10);
        Date settlementDate = new Date(2025, 9, 1);

        BondOrder bondOrder = new BondOrder(ldt, tradeDate, settlementDate, 100.0, 5.0, 2);

        Long id = -3009157732242241606L;

        bondOrder.setID(id);

        assertNotNull( (Long) bondOrder.getID());

        assertEquals(-3009157732242241606L, bondOrder.getID(), 0.00);
    }

    @Test
    public void testToString() {
        LocalDateTime ldt = LocalDateTime.of(2017, Month.AUGUST, 16, 12, 0);

        Date tradeDate = new Date(2017, 8, 10);
        Date settlementDate = new Date(2025, 9, 1);

        BondOrder bondOrder = new BondOrder(ldt, tradeDate, settlementDate, 100.0, 5.0, 2);

        Bond bond = new Bond("123456XY78", "Treasury", LocalDate.of(2017, Month.AUGUST, 10), "5YR", 4.30, LocalDate.of(2023, Month.AUGUST, 10), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);
        User user = new User("John", "Doe", "5089993453", "johndoe@gmail.com", "password1", "022657766", "7766", "AAAAAAAAAAAAAAAA", "4 Main Street", "Boston", "MA", "02129", 100.0);
        Long id = -3009157732242241606L;
        LocalDateTime orderTimeStamp = LocalDateTime.of(2017, Month.AUGUST, 16, 12, 0);
        Double transactionAmt = 100.0;
        Double accruedInterest = 3.2;
        Integer numBondsPurchased = 5;

        String bondString = "BondOrder{" +
                "bond=" + bond +
                ", user=" + user +
                ", id=" + id +
                ", ordertimestamp=" + orderTimeStamp +
                ", tradedate=" + tradeDate +
                ", settlementdate=" + settlementDate +
                ", transactionamt=" + transactionAmt +
                ", accruedinterest=" + accruedInterest +
                ", numbondspurchased=" + numBondsPurchased +
                '}';

//        System.out.print("\n\n" + bondString + "\n\n");

        assertNotNull( (String) bondOrder.toString());

        assertNotEquals(bondString, bondOrder.toString());
    }
}
