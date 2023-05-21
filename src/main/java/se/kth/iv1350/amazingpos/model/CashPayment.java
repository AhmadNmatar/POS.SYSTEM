
package se.kth.iv1350.amazingpos.model;

/**
 *
 *The Amount class represents a monetary value. 
 *It encapsulates a double value, which represents the amount.
 */
public class CashPayment {
    Amount paidAmount;
    
    /**
     * creat a new instance of
     */
    public CashPayment (Amount paidAmount){
        this.paidAmount = paidAmount;
    }
    /**
     * calculate the amount of change.
     * @param paidAmount
     * @return 
     */
    public Amount calculateChange(Amount totalPrice){
        Amount change = paidAmount.subtract(totalPrice);
        return change;
    }
    
   public Amount getValue(){
       return paidAmount;
   }
    
}
