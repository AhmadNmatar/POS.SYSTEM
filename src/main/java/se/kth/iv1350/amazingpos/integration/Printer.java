package se.kth.iv1350.amazingpos.integration;

import se.kth.iv1350.amazingpos.model.Receipt;

/**
 *
 * The Printer class represents a printer used to print receipts for sales.
 */
public class Printer {
    Receipt receipt;
    
    
    /**
     * This method prints a receipt using the provided Reciept object. 
     * The receipt includes information about the current sale, 
     * the paid amount, and the change amount. 
     * @param receipt the Reciept object to be printed.
     */
    public void printReceipt(Receipt receipt){
        receipt.printReceipt();
        
    }
    
}
