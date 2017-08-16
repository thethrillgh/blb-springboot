import static org.junit.Assert.*;

import com.putnam.model.BondOrder;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
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
    public void testUserId(){

        LocalDateTime ldt = LocalDateTime.of(2017, Month.AUGUST, 16, 12, 0);

        Date tradeDate = new Date(2017, 8, 10);
        Date settlementDate = new Date(2025, 9, 1);

        BondOrder bondOrder = new BondOrder(ldt, tradeDate, settlementDate, 100.0, 5.0, 2);

        assertNotNull( (Long) bondOrder.getUserid());

        bondOrder.setUserid(10);

        assertEquals(10, bondOrder.getUserid(), 0.00);
    }

    @Test
    public void testBondId(){

        LocalDateTime ldt = LocalDateTime.of(2017, Month.AUGUST, 16, 12, 0);

        Date tradeDate = new Date(2017, 8, 10);
        Date settlementDate = new Date(2025, 9, 1);

        BondOrder bondOrder = new BondOrder(ldt, tradeDate, settlementDate, 100.0, 5.0, 2);

        assertNotNull( (Long) bondOrder.getBondid());

        bondOrder.setBondid(10);

        assertEquals(10, bondOrder.getBondid(), 0.00);
    }
}
