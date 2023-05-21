package se.kth.iv1350.amazingpos.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.amazingpos.model.Sale;

/**
 *
 * SaleLog stores the sales made in the point of sale system.
 */
public class SaleLog {
    private List<Sale> paidSale;
    
    public SaleLog(){
        paidSale = new ArrayList<>();
        
    }
    /**
     * add sale to the sale log
     * @param sale that has been paid
     */
    public void addSale(Sale sale){
        paidSale.add(sale);
    }
    
}
