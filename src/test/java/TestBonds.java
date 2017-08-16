import static org.junit.Assert.*;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import com.putnam.model.Bond;

public class TestBonds {

    // Bond bond = new Bond("123456XY78", "Treasury", 2017-08-10), "5YR", 4.30, 2023-08-10), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);
    @Test
    public void testCusip(){
        Bond bond = new Bond("123456XY78", "Treasury", LocalDate.of(2017, Month.AUGUST, 10), "5YR", 4.30, LocalDate.of(2023, Month.AUGUST, 10), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        assertEquals("123456XY78", bond.getCusip());

        bond.setCusip("654321YX87");

        assertNotEquals("123456XY78", bond.getCusip());
    }

    @Test
    public void testIssuer(){
        Bond bond = new Bond("123456XY78", "Treasury", LocalDate.of(2017, Month.AUGUST, 10), "5YR", 4.30, LocalDate.of(2023, Month.AUGUST, 10), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        assertEquals("Treasury", bond.getIssuer());

        bond.setIssuer("Corporate");

        assertNotEquals("Treasury", bond.getIssuer());

    }

    @Test
    public void testIssueDate(){
        Bond bond = new Bond("123456XY78", "Treasury", LocalDate.of(2017, Month.AUGUST, 10), "5YR", 4.30, LocalDate.of(2023, Month.AUGUST, 10), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        LocalDate d = LocalDate.of(2017, Month.AUGUST, 10);

        assertEquals(d, bond.getIssuedate());

        LocalDate d2 = LocalDate.of(2017, Month.AUGUST, 15);

        bond.setIssuedate(d2);

        assertNotEquals(d, bond.getIssuedate());
    }

    @Test
    public void testType(){
        Bond bond = new Bond("123456XY78", "Treasury", LocalDate.of(2017, Month.AUGUST, 10), "5YR", 4.30, LocalDate.of(2023, Month.AUGUST, 10), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        assertEquals("5YR", bond.getType());

        bond.setType("10YR");

        assertNotEquals("5YR", bond.getType());
    }

    @Test
    public void testInterest(){
        Bond bond = new Bond("123456XY78", "Treasury", LocalDate.of(2017, Month.AUGUST, 10), "5YR", 4.30, LocalDate.of(2023, Month.AUGUST, 10), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        assertEquals(4.30, bond.getInterestrate(), 0.00);

        bond.setInterestrate(5.20);

        assertNotEquals(4.30, bond.getInterestrate(), 0.00);
    }

    @Test
    public void testMaturity(){
        Bond bond = new Bond("123456XY78", "Treasury", LocalDate.of(2017, Month.AUGUST, 10), "5YR", 4.30, LocalDate.of(2023, Month.AUGUST, 10), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        LocalDate d = LocalDate.of(2023, Month.AUGUST, 10);

        assertEquals(d, bond.getMaturitydate());

        LocalDate d2 = LocalDate.of(2023, Month.AUGUST, 15);

        bond.setMaturitydate(d2);

        assertNotEquals(d, bond.getMaturitydate());
    }

    @Test
    public void testquantity(){
        Bond bond = new Bond("123456XY78", "Treasury", LocalDate.of(2017, Month.AUGUST, 10), "5YR", 4.30, LocalDate.of(2023, Month.AUGUST, 10), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        assertEquals(10000000, bond.getQuantity(), 0.00);

        bond.setQuantity(10);

        assertNotEquals(10000000, bond.getQuantity(), 0.00);
    }

    @Test
    public void testCreditRate(){
        Bond bond = new Bond("123456XY78", "Treasury", LocalDate.of(2017, Month.AUGUST, 10), "5YR", 4.30, LocalDate.of(2023, Month.AUGUST, 10), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        assertEquals("AAA", bond.getCreditrating());

        bond.setCreditrating("C");

        assertNotEquals("AAA", bond.getCreditrating());
    }

    @Test
    public void testCallable(){
        Bond bond = new Bond("123456XY78", "Treasury", LocalDate.of(2017, Month.AUGUST, 10), "5YR", 4.30, LocalDate.of(2023, Month.AUGUST, 10), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        assertEquals("No", bond.getCallable());

        bond.setCallable("Yes");

        assertNotEquals("No", bond.getCallable());
    }

    @Test
    public void testCouponType(){
        Bond bond = new Bond("123456XY78", "Treasury", LocalDate.of(2017, Month.AUGUST, 10), "5YR", 4.30, LocalDate.of(2023, Month.AUGUST, 10), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        assertEquals("FIXED", bond.getCoupontype());

        bond.setCoupontype("VARIABLE");

        assertNotEquals("FIXED", bond.getCoupontype());
    }
    @Test
    public void testBid(){
        Bond bond = new Bond("123456XY78", "Treasury", LocalDate.of(2017, Month.AUGUST, 10), "5YR", 4.30, LocalDate.of(2023, Month.AUGUST, 10), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        assertEquals(100.778343, bond.getBid(),0.00);

        bond.setBid(101.4);

        assertNotEquals(100.778343, bond.getBid(),0.00);
    }
    @Test
    public void testAsk(){
        Bond bond = new Bond("123456XY78", "Treasury", LocalDate.of(2017, Month.AUGUST, 10), "5YR", 4.30, LocalDate.of(2023, Month.AUGUST, 10), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        assertEquals(101.4596284, bond.getAsk(),0.00);

        bond.setAsk(102.4);

        assertNotEquals(101.4596284, bond.getAsk(),0.00);
    }

    @Test
    public void testYieldBid(){
        Bond bond = new Bond("123456XY78", "Treasury", LocalDate.of(2017, Month.AUGUST, 10), "5YR", 4.30, LocalDate.of(2023, Month.AUGUST, 10), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

    }
    @Test
    public void testYieldAsk(){
        Bond bond = new Bond("123456XY78", "Treasury", LocalDate.of(2017, Month.AUGUST, 10), "5YR", 4.30, LocalDate.of(2023, Month.AUGUST, 10), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

    }
    @Test
    public void testMarketPrice(){
        Bond bond = new Bond("123456XY78", "Treasury", LocalDate.of(2017, Month.AUGUST, 10), "5YR", 4.30, LocalDate.of(2023, Month.AUGUST, 10), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

    }
    public void testMArketYield(){
        Bond bond = new Bond("123456XY78", "Treasury", LocalDate.of(2017, Month.AUGUST, 10), "5YR", 4.30, LocalDate.of(2023, Month.AUGUST, 10), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

    }
    @Test
    public void testFaceVal(){
        Bond bond = new Bond("123456XY78", "Treasury", LocalDate.of(2017, Month.AUGUST, 10), "5YR", 4.30, LocalDate.of(2023, Month.AUGUST, 10), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

    }
    @Test
    public void testToString(){
        Bond bond = new Bond("123456XY78", "Treasury", LocalDate.of(2017, Month.AUGUST, 10), "5YR", 4.30, LocalDate.of(2023, Month.AUGUST, 10), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        String obj = bond.toString();

        assertNotNull(obj);

    }
}
