package se.kth.iv1350.amazingpos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CashPaymentTest {

    @Test
    public void testCalculateChange() {
        Amount totalPrice = new Amount(50.0);
        CashPayment paidAmount = new CashPayment(new Amount(70.0));
        Amount expResult = new Amount(20);
        Amount result = paidAmount.calculateChange(totalPrice);
        assertEquals(expResult.getValue(), result.getValue());
    }

    @Test
    public void testGetValue() {
        CashPayment instance =new CashPayment(new Amount(70.0));
        Amount expResult = new Amount(70.0);
        Amount result = instance.getValue();
        assertEquals(expResult.getValue(), result.getValue());
    }
    
}
