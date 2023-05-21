package se.kth.iv1350.amazingpos.integration;

import java.sql.SQLException;

/**
 *
 *This class contains all calls to the data store with item that may be sold.
 */
public class InventorySystem {
    private ItemRegistry item;
    
    public InventorySystem(){
        item = new ItemRegistry();
    }
    
    /**
     * match the entred item's identifier with Item available item in data base.
     * @param itemIdentifier used to find item
     * @return item that match item's identifire.
     * @throws InvalidItemIdentifierException when searching for an unexisted item or entring an invalid item identifier..
     * @throws SQLException when system failed to access the data base.
     */
    public ItemDTO matchItem (String itemIdentifier) throws InvalidItemIdentifierException, SQLException {
        ItemDTO itemFound = item.getScanedItem(itemIdentifier);
        return itemFound;
        
    }
}
