
package se.kth.iv1350.amazingpos.integration;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.amazingpos.model.Sale;

public class SaleLogTest {
    private List<Sale> saleLog;
    
    @Test
    public void testAddSale() {
        saleLog = new ArrayList<>();
        Sale sale = new Sale();
        saleLog.add(sale);
        int result = saleLog.size();
        int expResult = 1;
        assertEquals(expResult, result);
        
    }
    
}
