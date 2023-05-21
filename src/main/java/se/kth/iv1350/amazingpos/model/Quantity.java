
package se.kth.iv1350.amazingpos.model;

/**
 *
 * Quantity class represents a quantity of an item in the sale.
 * It provides methods to get and increase the quantity.
*/
 
public class Quantity {
    private int quantity;
    /**
     * Constructor to initialize Quantity object with given quantity
     * @param quantity the quantity of the item in the sale
     */
    public Quantity (int quantity){
        this.quantity = quantity;
    }
    /**
     * Returns the quantity of the item in the sale
     * @return the quantity of the item in the sale.
     */
    public int getValue(){
        return this.quantity;
    }
    /**
     * Increases the quantity of the item in the sale by 1
     */
    public void increaseQuantity(){
        quantity++;
    }
    public String toString() {
        return Integer.toString(quantity);
    }
}
