package se.kth.iv1350.amazingpos.model;


/**
 *
 * responsible for price calculating. this interface should be implemented by a class that
 * provid calculate algorithm.
 * 
 */
public interface PriceCalculator {
    /**
     * calculating total price for the sale.
     * @return the total price.
     * 
     */
    public Amount calculateTotalPrice();
    
}
