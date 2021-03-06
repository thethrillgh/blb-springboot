import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Date;

import com.putnam.model.Bond;

public class TestBonds {

    // Bond bond = new Bond("123456XY78", "Treasury", 2017-08-10), "5YR", 4.30, 2023-08-10), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);
    @Test
    public void testCusip(){
        Bond bond = new Bond("123456XY78", "Treasury", new Date(2017, 8, 16), "5YR", 4.30, new Date(2023, 8, 16), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        assertEquals("123456XY78", bond.getCusip());

        bond.setCusip("654321YX87");

        assertNotEquals("123456XY78", bond.getCusip());
    }

    @Test
    public void testIssuer(){
        Bond bond = new Bond("123456XY78", "Treasury", new Date(2017, 8, 16), "5YR", 4.30, new Date(2023, 8, 16), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        assertEquals("Treasury", bond.getIssuer());

        bond.setIssuer("Corporate");

        assertNotEquals("Treasury", bond.getIssuer());

    }

    @Test
    public void testIssueDate(){
        Bond bond = new Bond("123456XY78", "Treasury", new Date(2017, 8, 16), "5YR", 4.30, new Date(2023, 8, 16), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        Date d = new Date(2017, 8, 16);

        assertEquals(d, bond.getIssuedate());

        Date d2 = new Date(2023, 8, 25);

        bond.setIssuedate(d2);

        assertEquals(new Date(2023, 8, 25), bond.getIssuedate());
    }

    @Test
    public void testType(){
        Bond bond = new Bond("123456XY78", "Treasury", new Date(2017, 8, 16), "5YR", 4.30, new Date(2023, 8, 16), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        assertEquals("5YR", bond.getType());

        bond.setType("10YR");

        assertNotEquals("5YR", bond.getType());
    }

    @Test
    public void testInterest(){
        Bond bond = new Bond("123456XY78", "Treasury", new Date(2017, 8, 16), "5YR", 4.30, new Date(2023, 8, 16), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        assertEquals(4.30, bond.getInterestrate(), 0.00);

        bond.setInterestrate(5.20);

        assertNotEquals(4.30, bond.getInterestrate(), 0.00);
    }

    @Test
    public void testMaturity(){
        Bond bond = new Bond("123456XY78", "Treasury", new Date(2017, 8, 16), "5YR", 4.30, new Date(2023, 8, 16), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        Date d = new Date(2023, 8, 16);

        assertEquals(d, bond.getMaturitydate());

        Date d2 = new Date(2023, 8, 25);

        bond.setMaturitydate(d2);

        assertEquals(new Date(2023, 8, 25), bond.getMaturitydate());
    }

    @Test
    public void testquantity(){
        Bond bond = new Bond("123456XY78", "Treasury", new Date(2017, 8, 16), "5YR", 4.30, new Date(2023, 8, 16), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        assertEquals(10000000, bond.getQuantity(), 0.00);

        bond.setQuantity(10);

        assertNotEquals(10000000, bond.getQuantity(), 0.00);
    }

    @Test
    public void testCreditRate(){
        Bond bond = new Bond("123456XY78", "Treasury", new Date(2017, 8, 16), "5YR", 4.30, new Date(2023, 8, 16), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        assertEquals("AAA", bond.getCreditrating());

        bond.setCreditrating("C");

        assertNotEquals("AAA", bond.getCreditrating());
    }

    @Test
    public void testCallable(){
        Bond bond = new Bond("123456XY78", "Treasury", new Date(2017, 8, 16), "5YR", 4.30, new Date(2023, 8, 16), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        assertEquals("No", bond.getCallable());

        bond.setCallable("Yes");

        assertNotEquals("No", bond.getCallable());
    }

    @Test
    public void testCouponType(){
        Bond bond = new Bond("123456XY78", "Treasury", new Date(2017, 8, 16), "5YR", 4.30, new Date(2023, 8, 16), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        assertEquals("FIXED", bond.getCoupontype());

        bond.setCoupontype("VARIABLE");

        assertNotEquals("FIXED", bond.getCoupontype());
    }

    @Test
    public void testBid(){
        Bond bond = new Bond("123456XY78", "Treasury", new Date(2017, 8, 16), "5YR", 4.30, new Date(2023, 8, 16), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        assertEquals(100.778343, bond.getBid(),0.00);

        bond.setBid(101.4);

        assertNotEquals(100.778343, bond.getBid(),0.00);
    }

    @Test
    public void testAsk(){
        Bond bond = new Bond("123456XY78", "Treasury", new Date(2017, 8, 16), "5YR", 4.30, new Date(2023, 8, 16), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        assertEquals(101.4596284, bond.getAsk(),0.00);

        bond.setAsk(102.4);

        assertNotEquals(101.4596284, bond.getAsk(),0.00);
    }

    @Test
    public void testYieldBid(){
        Bond bond = new Bond("123456XY78", "Treasury", new Date(2017, 8, 16), "5YR", 4.30, new Date(2023, 8, 16), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        assertEquals(6.102501603, bond.getYieldbid(),0.00);

        bond.setYieldbid(7.5);

        assertNotEquals(6.102501603, bond.getYieldbid(),0.00);
    }

    @Test
    public void testYieldAsk(){
        Bond bond = new Bond("123456XY78", "Treasury", new Date(2017, 8, 16), "5YR", 4.30, new Date(2023, 8, 16), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        assertEquals(6.061524272, bond.getYieldask(),0.00);

        bond.setYieldask(7.5);

        assertNotEquals(6.061524272, bond.getYieldask(),0.00);
    }

    @Test
    public void testMarketPrice(){
        Bond bond = new Bond("123456XY78", "Treasury", new Date(2017, 8, 16), "5YR", 4.30, new Date(2023, 8, 16), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        assertEquals(101.1189857, bond.getMarketprice(),0.00);

        bond.setMarketprice(103.1);

        assertNotEquals(101.1189857, bond.getMarketprice(),0.00);
    }

    @Test
    public void testMarketYield(){
        Bond bond = new Bond("123456XY78", "Treasury", new Date(2017, 8, 16), "5YR", 4.30, new Date(2023, 8, 16), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        assertEquals(6.082012938, bond.getMarketyield(),0.00);

        bond.setMarketyield(7.5);

        assertNotEquals(6.082012938, bond.getMarketyield(),0.00);
    }
    @Test
    public void testFaceVal(){
        Bond bond = new Bond("123456XY78", "Treasury", new Date(2017, 8, 16), "5YR", 4.30, new Date(2023, 8, 16), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        assertEquals(100, bond.getFacevalue(),0.00);

        bond.setFacevalue(200);

        assertNotEquals(100, bond.getFacevalue(),0.00);
    }

    @Test
    public void testToString(){
        Bond bond = new Bond("123456XY78", "Treasury", new Date(2017, 8, 16), "5YR", 4.30, new Date(2023, 8, 16), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        String obj = bond.toString();

        assertNotNull(obj);

    }

    @Test
    public void testIdNums(){
        Bond bond = new Bond("123456XY78", "Treasury", new Date(2017, 8, 16), "5YR", 4.30, new Date(2023, 8, 16), 10000000, "AAA", "No", "FIXED", 100.778343, 101.4596284, 6.102501603, 6.061524272, 101.1189857, 6.082012938, 100);

        long id = bond.getBondid();

        assertNotNull(id);

        bond.setBondid(100);

        assertEquals(100, bond.getBondid());
    }

}
