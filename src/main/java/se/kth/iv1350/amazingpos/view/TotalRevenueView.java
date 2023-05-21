package se.kth.iv1350.amazingpos.view;

import se.kth.iv1350.amazingpos.model.Amount;
import se.kth.iv1350.amazingpos.model.SaleObserver;

/**
 *
 * This class is responsible to print the total income of the store since
 * the program was started.
 */
public class TotalRevenueView implements SaleObserver {
    private Amount totalIncome = new Amount(0.0);
    
     /**
     * careate a constructor.
     */
    public TotalRevenueView(){
        
    }
    /**
     * add the amount of paid sale to the total inomce and print it out to user interface.
     * @param paidSale The paid Amount of the current sale.
     */
    @Override
    public void newSale(Amount paidSale) {
        totalIncome = totalIncome.add(paidSale);
        printTheTotalIncome();
        
    }
    private void printTheTotalIncome(){
        System.out.println("---- The total income for today------");
        System.out.println("Total income:\t\t" + totalIncome.getValue() + " SEK");
        System.out.println("\n");
        
    }
    
}
