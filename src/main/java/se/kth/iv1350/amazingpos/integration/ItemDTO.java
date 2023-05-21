package se.kth.iv1350.amazingpos.integration;

import se.kth.iv1350.amazingpos.model.Amount;
import se.kth.iv1350.amazingpos.model.Quantity;

/**
 *
 * The ItemDTO class represents an item in the inventory system.
 */
public class ItemDTO {
    private String itemId;
    private String name;
    private Amount price;
    private Amount vat;
    private Quantity quantity = new Quantity(1);
    
    /**
     * creates an new instance of an item.
     * @param itemId is itemIdentifier
     * @param name item's name
     * @param price the price of an item
     * @param vat Vat rate of an item
     */
    public ItemDTO (String itemId, String name, Amount price, Amount vat){
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.vat = vat;
       
    }
    /**
     * add number of item
     * @param quantity number of item determined by user
     */
    public void addQuantity(Quantity quantity){
        this.quantity = quantity;
    }
    /**
     * gets item's identifier
     * @return item's identification number
     */
    public String getItemId(){
            return itemId;
        }
    
    public String getName (){
        return name;
    }
    public Amount getPrice(){
        return price;
    }
    public Amount getVat(){
        return vat;
    }
    public Amount getVatForCalculatingPrice(){
        return this.vat.add(new Amount(1));
    }
    public Quantity getQuantity(){
        return quantity;
    }
    
}
