package se.kth.iv1350.amazingpos.integration;

import java.util.List;
import se.kth.iv1350.amazingpos.model.Amount;
import se.kth.iv1350.amazingpos.model.Sale;


/**
 *
 * contains method that print out item description to the system.out
 */
public class ItemDescription {
    private ItemDTO item;
    private Sale sale;
   
    
    public ItemDescription(){
        sale = new Sale();
        
    }
    /**
     * print out the item description to system.out
     * @param item 
     */
    public void printOutItemDescription(List<ItemDTO> sales) {
        
       // List <ItemDTO> scanedItems = sale.getSales();
        Amount runingTotal = new Amount(0.0);
        for(ItemDTO item : sales){
            System.out.println("item " + item.getName());
            System.out.println("quantity " + item.getQuantity());
            System.out.println("price " + item.getPrice());
            System.out.println("VAT " + item.getVat());
            Amount vat = item.getVat().add(new Amount(1));
            runingTotal = runingTotal.add(item.getPrice().multiply(vat).multiply(item.getQuantity()));
            System.out.println("\n");
        }
        System.out.println("Runing total: " + runingTotal);
        System.out.println("\n");
    }
}
    
    
   

//public void printOutItemDescription(ItemDTO item)