package se.kth.iv1350.amazingpos.integration;


import se.kth.iv1350.amazingpos.model.Amount;

/**
 *
 * Discount DTO represent a discount object in discount data base.
 */
public class DiscountDTO {
    private Amount discountPercentage;
    private ItemDTO item;
    private String customerID;
    /**
     * create a new instance of discount.
     * @param discountPercentage the discount amount
     * @param item which has a dicount
     * @param customerID the customer who is eligible for discount.
     */
    public DiscountDTO(Amount discountPercentage, ItemDTO item, String customerID){
        this.discountPercentage = discountPercentage;
        this.item = item;
        this.customerID = customerID;
    }
    
    public Amount getDiscountPercentage(){
        return this.discountPercentage;
    }
    
    public ItemDTO getItem(){
        return this.item;
    }
    
    public String getCustomerID(){
        return this.customerID;
    }
}
