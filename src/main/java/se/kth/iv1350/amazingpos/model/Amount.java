package se.kth.iv1350.amazingpos.model;

import se.kth.iv1350.amazingpos.model.Quantity;

/**
 *The Amount class represents a monetary value. 
 *It encapsulates a double value, which represents the amount. 
 * 
 */
public class Amount {
    private double amount;
    
    /**
     * creat an new instance 
     * @param amount the amount to be set for the new object.
     */
    
    public Amount (double amount){
        this.amount = amount;
    }
    
    public double getValue(){
        return this.amount;
    }
    
    public Amount multiply (Amount vat){
        double result = (double) this.amount * vat.getValue();
        return new Amount(result);
    }
    public Amount multiply (Quantity quantity){
         double result = this.amount * quantity.getValue();
         return new Amount (result);
     }
    public Amount subtract(Amount amount){
        double result = this.amount - amount.getValue();
        return new Amount(result);
    }
    /**
    * Adds the specified Amount to this Amount and
    * returns the result as a new Amount object.
    * @param amount the Amount to be added to this Amount
    * @return a new Amount object representing 
    * the sum of this Amount and the specified Amount
    */
    public Amount add(Amount amount) {
        double result = this.amount + amount.getValue();
    return new Amount(result);
    }
    
    public String toString(){
        return String.format("%.2f", this.amount);
    }

}
