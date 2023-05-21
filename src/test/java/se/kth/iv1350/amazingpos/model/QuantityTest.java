package se.kth.iv1350.amazingpos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class QuantityTest {

    @Test
    public void testGetValue() {
        Quantity instance = new Quantity(4);
        int expResult = 4;
        int result = instance.getValue();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testIncreaseQuantity() {
        Quantity instance = new Quantity(4);
        instance.increaseQuantity();
        int expResult = 5;
        int result = instance.getValue();
        assertEquals(expResult, result);
    }

    @Test
    public void testToString() {
        
        Quantity instance = new Quantity(4);
        String expResult = "4";
        String result = instance.toString();
        assertEquals(expResult, result);
        
    }
    
}
