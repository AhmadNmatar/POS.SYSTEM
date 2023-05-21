package se.kth.iv1350.amazingpos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class AmountTest {
   
    @Test
    public void testGetValue() {
        Amount instance = new Amount(3.00);
        double expResult = 3.00;
        double result = instance.getValue();
        assertEquals(expResult, result, 0);
        
    }

    @Test
    public void testMultiplyWithAmount() {
        Amount amount = new Amount(2.0); 
        Amount instance = new Amount(3.0);
        Amount expResult = new Amount(6.00);
        Amount result = instance.multiply(amount);
        assertEquals(expResult.getValue(), result.getValue());
    }
    
     @Test
    public void testMultiplyWithQuantity() {
        Amount instance = new Amount(3.0);
        Quantity quantity = new Quantity(3); 
        Amount expResult = new Amount(9.00);
        Amount result = instance.multiply(quantity);
        assertEquals(expResult.getValue(), result.getValue());
    }
    

    @Test
    public void testSubtract() {
        Amount amount = new Amount(2.0); 
        Amount instance = new Amount(3.0);
        Amount expResult = new Amount(1.00);
        Amount result = instance.subtract(amount);
        assertEquals(expResult.getValue(), result.getValue());

    }

    @Test
    public void testAdd() {
        Amount amount = new Amount(2.0); 
        Amount instance = new Amount(3.0);
        Amount expResult = new Amount(5.0);
        Amount result = instance.add(amount);
        assertEquals(expResult.getValue(), result.getValue());

    }

    @Test
    public void testToString() { 
        Amount instance = new Amount(3.0);
        String expResult = "3,00";
        String result = instance.toString();
        assertEquals(expResult, result);

    }
    
}
