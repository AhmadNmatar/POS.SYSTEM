package se.kth.iv1350.amazingpos.model;

import se.kth.iv1350.amazingpos.model.Amount;

/**
 *
 * This interface responsilbles for obeserving the sale class.
 */
public interface SaleObserver {
    /**
     * invoked when a sale has been paid.
     * @param paidSale amount has been paid.
     */
    void newSale(Amount paidSale); 
}
