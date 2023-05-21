package se.kth.iv1350.amazingpos.integration;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import se.kth.iv1350.amazingpos.model.Amount;

/**
 *
 * Works as a data base, contains all items in the store.
 */
public class ItemRegistry {
    private final Map<String,ItemDTO> items = new HashMap<>();
    
    
    /**
     * creats a new instance
     */
    public ItemRegistry(){
        ItemDTO item1 = new ItemDTO("123", "Apple", new Amount(100.0), new Amount(0.25));
        ItemDTO item2 = new ItemDTO("567", "Banan", new Amount(50.0), new Amount(0.12));
        ItemDTO item3 = new ItemDTO("890", "Orange", new Amount(40.0), new Amount(0.06));
        ItemDTO item4 = new ItemDTO("111", "Apelsin", new Amount(40.0), new Amount(0.06));
        items.put(item1.getItemId(), item1);
        items.put(item2.getItemId(), item2);
        items.put(item3.getItemId(), item3);
        items.put(item4.getItemId(), item4);  
    }
    /**
     * This method retrieves an item from the items Map based on the provided item identifier.
     * @param itemIdentifier the identifier of the item to retrieve
     * @return a new ItemDTO object containing the details of the retrieved item,
     * or null if no item with the provided identifier is found.
     * @throws InvalidItemIdentifierException when searching for an unexisted item/ or entring an invalid item identifier.
     * @throws SQLException when it faild to access data base.
     */
    public ItemDTO getScanedItem(String itemIdentifier) throws InvalidItemIdentifierException, SQLException {
        if(itemIdentifier.equals("111")){
                    throw new SQLException("Failed to Access data base");
        } 
        else{
            for(Map.Entry<String, ItemDTO> entry : items.entrySet()){
                if(entry.getKey().equals(itemIdentifier)){
                    ItemDTO item = entry.getValue();
                    return new ItemDTO(item.getItemId(), item.getName(),item.getPrice(), item.getVat());
                }   
            }
        }
       throw new InvalidItemIdentifierException("Invalid item identifier");   
    }

    
}
