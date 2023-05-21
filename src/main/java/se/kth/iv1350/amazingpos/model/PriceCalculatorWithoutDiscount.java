package se.kth.iv1350.amazingpos.model;

import java.util.List;
import se.kth.iv1350.amazingpos.integration.ItemDTO;

/**
 *
 * this class is responsible for calculating total pice without discount.
 */
public class PriceCalculatorWithoutDiscount implements PriceCalculator {
     private List<ItemDTO> items;
    
    public PriceCalculatorWithoutDiscount(List<ItemDTO> items){
        this.items = items;
    }
    /**
     * calculating total price for the sale.
     * @return the total price.
     * 
     */
    @Override
    public Amount calculateTotalPrice() {
        Amount totalPrice = new Amount(0.0);
        for(ItemDTO item : items) {
            Quantity quantity = item.getQuantity();
            Amount vat = item.getVatForCalculatingPrice();
            totalPrice = totalPrice.add(item.getPrice().multiply(vat).multiply(quantity));
        }
        return totalPrice;
    }   
}
