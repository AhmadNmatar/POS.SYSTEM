package se.kth.iv1350.amazingpos.view;

import java.util.logging.Level;
import java.util.logging.Logger;
import se.kth.iv1350.amazingpos.controller.Controller;
import se.kth.iv1350.amazingpos.controller.DataBaseHandlerException;
import se.kth.iv1350.amazingpos.integration.InvalidItemIdentifierException;
import se.kth.iv1350.amazingpos.integration.NoDiscountFoundException;
import se.kth.iv1350.amazingpos.model.Amount;
import se.kth.iv1350.amazingpos.model.Quantity;
import se.kth.iv1350.amazingpos.util.LogHandler;

/**
 *
 * This is the placeholder for the real view. It contains a hardcoded excution with calls to all
 * system operation in controller.
 */
public class View {
    private Controller control; 
    private ErrorMessegeHandler message = new ErrorMessegeHandler();
    private LogHandler logger;
    /**
     *  Create a new instance, that uses the specified controller for all calls to other layers
     * @param control the controller used for all calls to other layers
     */
    public View (Controller control){
        this.control = control;
        control.addSaleObserver(new TotalRevenueView());
        control.addSaleObserver(new TotalRevenueFileOutput());
        this.logger = new LogHandler();
    }
    /**
     * Preform a fake sale by calling all system operations in the controller.
     * 
     * 
     * 
     */
    public void runFakeExcution(){
        control.startSale();
        System.out.println("********* A new Sale has been Started ***********");
        try{
            control.searchItem("123", new Quantity(3)); 
            control.searchItem("567", new Quantity(3));
            control.searchItem("890", new Quantity(3));
            try{
                System.out.println("Trying to find a non-exsited item.");
                control.searchItem("999", new Quantity(1));
            } catch(InvalidItemIdentifierException ex){
                message.displayErrorMsg("Item does not exist");    
            }
            control.searchItem("111", new Quantity(1));
        } catch(InvalidItemIdentifierException ex){
            message.displayErrorMsg("Item does not exist");
        } catch(DataBaseHandlerException ex){
            writeToScreenAndLogFile("Can not access the data base. Check the connection.", ex);
        }
        Amount totalPrice = control.endSale();
        System.out.println("TotalPrice: " + totalPrice);
        printNewLine();
        try{
            control.discountRequest("19960101");
            totalPrice = control.endSale();
        } catch(NoDiscountFoundException ex){
             message.displayErrorMsg("No discount corresponds to the given customer ID.");
        }
        Amount paidAmound = new Amount(1400.0);
        
        Amount change = control.pay(paidAmound);
        System.out.println("The amount of change:\t\t " + change + "kr");
        printNewLine();
        System.out.println("-------- End of receipt ----------------------");
        
    }
    private void writeToScreenAndLogFile(String msg, Exception ex){
        logger.writeTheExceptionToLogFile(ex);
        message.displayErrorMsg(msg);
    }
    private void printNewLine(){
        System.out.println("\n");
    }
}
