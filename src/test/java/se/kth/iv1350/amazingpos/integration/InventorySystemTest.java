package se.kth.iv1350.amazingpos.integration;

import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InventorySystemTest {

    @Test
    public void testMatchItemEqualNull() throws InvalidItemIdentifierException, SQLException {
       
        String itemIdentifier = "";
        InventorySystem instance = new InventorySystem();
        ItemDTO expResult = null;
        ItemDTO result = instance.matchItem(itemIdentifier);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testMatchItem() throws InvalidItemIdentifierException, SQLException {
        String itemIdentifier = "123";
        ItemRegistry items = new ItemRegistry();
        InventorySystem instance = new InventorySystem();
        String expResult = items.getScanedItem(itemIdentifier).getName();
        ItemDTO result = instance.matchItem(itemIdentifier);
        assertEquals(expResult, result.getName());
    }
    
    
}
