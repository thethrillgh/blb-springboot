import static org.junit.Assert.*;

import com.putnam.model.Bond;
import com.putnam.model.BondHistory;
import com.putnam.model.BondOrder;
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
public class TestBondHistory {

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
    public void testTime(){
        Date time = new Date(2017, 8, 17, 12, 30);
        Date time2 = new Date(2019, 8, 20, 12, 30);

        BondHistory bondHistory = new BondHistory(time, 100.0, 145.0, 35.0, 45.0, 3.7, "123456XY78");

        assertEquals(time, bondHistory.getTime());

        assertNotEquals(time2, bondHistory.getTime());

        bondHistory.setTime(time2);

        assertNotEquals(time, bondHistory.getTime());

        assertEquals(time2, bondHistory.getTime());
    }

    @Test
    public void testBid(){
        Date time = new Date(2017, 8, 17, 12, 30);

        BondHistory bondHistory = new BondHistory(time, 100.0, 145.0, 35.0, 45.0, 3.7, "123456XY78");

        assertEquals(100.0, bondHistory.getBid(), 0.00);

        assertNotEquals(50.0, bondHistory.getBid() , 0.00);

        bondHistory.setBid(150.0);

        assertNotEquals(100.0, bondHistory.getBid(), 0.00);

        assertEquals(150.0, bondHistory.getBid(), 0.00);
    }

    @Test
    public void testAsk(){
        Date time = new Date(2017, 8, 17, 12, 30);

        BondHistory bondHistory = new BondHistory(time, 100.0, 145.0, 35.0, 45.0, 3.7, "123456XY78");

        assertEquals(145.0, bondHistory.getAsk(), 0.00);

        assertNotEquals(150.0, bondHistory.getAsk() , 0.00);

        bondHistory.setAsk(160.0);

        assertNotEquals(145.0, bondHistory.getAsk(), 0.00);

        assertEquals(160.0, bondHistory.getAsk(), 0.00);
    }

    @Test
    public void testYieldBid(){
        Date time = new Date(2017, 8, 17, 12, 30);

        BondHistory bondHistory = new BondHistory(time, 100.0, 145.0, 35.0, 45.0, 3.7, "123456XY78");

        assertEquals(35.0, bondHistory.getYieldbid(), 0.00);

        assertNotEquals(50.0, bondHistory.getYieldbid() , 0.00);

        bondHistory.setYieldbid(45.0);

        assertNotEquals(35.0, bondHistory.getYieldbid(), 0.00);

        assertEquals(45.0, bondHistory.getYieldbid(), 0.00);
    }

    @Test
    public void testYieldAsk(){
        Date time = new Date(2017, 8, 17, 12, 30);

        BondHistory bondHistory = new BondHistory(time, 100.0, 145.0, 35.0, 45.0, 3.7, "123456XY78");

        assertEquals(45.0, bondHistory.getYieldask(), 0.00);

        assertNotEquals(50.0, bondHistory.getYieldask() , 0.00);

        bondHistory.setYieldask(75.0);

        assertNotEquals(45.0, bondHistory.getYieldask(), 0.00);

        assertEquals(75.0, bondHistory.getYieldask(), 0.00);
    }

    @Test
    public void testChangePrice(){
        Date time = new Date(2017, 8, 17, 12, 30);

        BondHistory bondHistory = new BondHistory(time, 100.0, 145.0, 35.0, 45.0, 3.7, "123456XY78");

        assertEquals(3.7, bondHistory.getChangeprice(), 0.00);

        assertNotEquals(9.3, bondHistory.getChangeprice() , 0.00);

        bondHistory.setChangeprice(5.2);

        assertNotEquals(3.7, bondHistory.getChangeprice(), 0.00);

        assertEquals(5.2, bondHistory.getChangeprice(), 0.00);
    }

    @Test
    public void testCusip(){
        Date time = new Date(2017, 8, 17, 12, 30);

        BondHistory bondHistory = new BondHistory(time, 100.0, 145.0, 35.0, 45.0, 3.7, "123456XY78");

        assertEquals("123456XY78", bondHistory.getCusip());

        assertNotEquals("654321XY78", bondHistory.getCusip());

        bondHistory.setCusip("654321XY78");

        assertNotEquals("123456XY78", bondHistory.getCusip());

        assertEquals("654321XY78", bondHistory.getCusip());
    }

    @Test
    public void testBond() {
        Date time = new Date(2017, 8, 17, 12, 30);
        Date settlementDate = new Date(2023, 8, 10);
        Date issueDate = new Date(2017, 8, 10);

        Bond bond = new Bond("123456XY78", "Treasury", issueDate, "5YR", 4.30, settlementDate, 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);
        Bond bond2 = new Bond("123456XY78", "Treasury", issueDate, "5YR", 4.30, settlementDate, 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        BondHistory bondHistory = new BondHistory(time, 100.0, 145.0, 35.0, 45.0, 3.7, "123456XY78");

        bondHistory.setBond(bond);

        assertEquals(bond, bondHistory.getBond());

        assertNotEquals(bond2, bondHistory.getBond());

        bondHistory.setBond(bond2);

        assertNotEquals(bond, bondHistory.getBond());

        assertEquals(bond2, bondHistory.getBond());
    }

}
