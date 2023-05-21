package se.kth.iv1350.amazingpos.model;

import java.util.List;
import se.kth.iv1350.amazingpos.integration.DiscountDTO;
import se.kth.iv1350.amazingpos.integration.ItemDTO;

/**
 *
 * @author ahmadmatar
 */
public class PriceCalculatorWithDiscount implements PriceCalculator {
    private List<DiscountDTO> discounts;
    private List<ItemDTO> items;
    /**
     * creat a new instance for calculating total price with discount
     * @param discounts include available dicount for a specific customer.
     * @param items include sold items
     */
    public PriceCalculatorWithDiscount(List<DiscountDTO> discounts, List<ItemDTO> items){
        this.discounts = discounts;
        this.items = items;
        
    }
    /**
     * calculating total price with discount
     * @return the total price after discount.
     */
    @Override
    public Amount calculateTotalPrice() {
        Amount totalPrice = calculatePrice(items);
        Amount totalPriceAfterDiscount = new Amount(0.0);
        Amount totalDiscount = new Amount(0.0);
        for (DiscountDTO discount : discounts) {
            totalDiscount = totalDiscount.add(discount.getDiscountPercentage());
        }

        totalPriceAfterDiscount = totalPrice.subtract(totalPrice.multiply(totalDiscount));
        return totalPriceAfterDiscount;
    }

    private Amount calculatePrice(List<ItemDTO> items) {
        Amount totalPrice = new Amount(0.0);
        for (ItemDTO item : items) {
            Quantity quantity = item.getQuantity();
            Amount vat = item.getVatForCalculatingPrice();
            totalPrice = totalPrice.add(item.getPrice().multiply(vat).multiply(quantity)); 
        }
        return totalPrice;
    }
}
