package se.kth.iv1350.amazingpos.integration;


import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.amazingpos.model.Amount;

public class ItemRegistryTest {
  
    @Test
    public void testGetScanedItem() throws InvalidItemIdentifierException, SQLException {
        String itemIdentifier = "567";
        ItemRegistry instance = new ItemRegistry();
        ItemDTO expResult = new ItemDTO("567", "Banan", new Amount(50.0), new Amount(0.12));
        ItemDTO result = instance.getScanedItem(itemIdentifier);
        
        assertEquals(expResult.getItemId(), result.getItemId());
        assertEquals(expResult.getName(), result.getName());
        assertEquals(expResult.getVat().toString(), result.getVat().toString());
    }
    
    @Test
    public void testGetScanedItem_InvalidID() throws InvalidItemIdentifierException, SQLException {
        String itemIdentifier = "999";
        ItemRegistry instance = new ItemRegistry();
        
        assertThrows(InvalidItemIdentifierException.class, () -> {instance.getScanedItem(itemIdentifier);});
    }
    
     @Test
    public void testGetScanedItem_DataBaseError() throws InvalidItemIdentifierException, SQLException {
        String itemIdentifier = "111";
        ItemRegistry instance = new ItemRegistry();
        
        assertThrows(SQLException.class, () -> {instance.getScanedItem(itemIdentifier);});
    }  
}
    
