package se.kth.iv1350.amazingpos.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import se.kth.iv1350.amazingpos.integration.AccountingSystem;
import se.kth.iv1350.amazingpos.integration.DiscountDB;
import se.kth.iv1350.amazingpos.integration.DiscountDTO;
import se.kth.iv1350.amazingpos.integration.InvalidItemIdentifierException;
import se.kth.iv1350.amazingpos.model.Amount;
import se.kth.iv1350.amazingpos.integration.InventorySystem;
import se.kth.iv1350.amazingpos.integration.ItemDTO;
import se.kth.iv1350.amazingpos.integration.ItemDescription;
import se.kth.iv1350.amazingpos.integration.NoDiscountFoundException;
import se.kth.iv1350.amazingpos.integration.Printer;
import se.kth.iv1350.amazingpos.model.Sale;
import se.kth.iv1350.amazingpos.model.Quantity;
import se.kth.iv1350.amazingpos.integration.SaleLog;
import se.kth.iv1350.amazingpos.model.CashPayment;
import se.kth.iv1350.amazingpos.model.SaleObserver;
import se.kth.iv1350.amazingpos.util.LogHandler;


/**
 * this is the application's only controller, all calls to model pass through here.
 *
 */
public class Controller {
    private Sale sale;
    private InventorySystem InvSystem;
    private ItemDescription description;
    private AccountingSystem accSystem;
    private Printer printer;
    private SaleLog saleLog;
    private DiscountDB discountDB;
    private LogHandler logger;
    private List<SaleObserver> saleObservers = new ArrayList<>();
    /**
     * This constructor creates a new Controller object with a specified Printer and SaleLog object.
     * @param printer the printer to be used for printing receipts
     * @param saleLog the sale log to keep track of sales
     */
    public Controller(Printer printer, SaleLog saleLog, DiscountDB discountDB){
        InvSystem = new InventorySystem();
        accSystem = new AccountingSystem();
        description = new ItemDescription();
        this.printer = printer;
        this.saleLog = saleLog;
        this.logger = new LogHandler();
        this.discountDB = discountDB;
    }
    
    /**
     * This method starts a new sale.
     */
    public void startSale(){
        sale = new Sale();
        sale.addSaleObserver(saleObservers);
    }
    /**
     * this method search for Item in data base.
     * @param itemIdentifier 
     * @param quantity how many item is sold.
     * @throws InvalidItemIdentifierException if item identifier not exist
     * @throws DataBaseHandlerException thrown when something wrong occur with databas
     * 
     * 
     */
    public void searchItem(String itemIdentifier, Quantity quantity) throws InvalidItemIdentifierException,
            DataBaseHandlerException{
       
        boolean itemExist = sale.checkIfItemExist(itemIdentifier);
        if(itemExist){
            sale.increaseQuantityOfExistingItem(itemIdentifier); 
        }
        else{
            try{
                ItemDTO itemFound = InvSystem.matchItem(itemIdentifier);
                itemFound.addQuantity(quantity);
                sale.saveSaleInformation(itemFound);
                description.printOutItemDescription(sale.getSales());
            } catch(SQLException DbEx){
                logger.writeTheExceptionToLogFile(DbEx);
                throw new  DataBaseHandlerException("Can not access data base");
            }
        }
    }
    /**
     * This method end the sale and present the total price for entier sale transaction
     * @return the total price including VAT
     */
    public Amount endSale(){
         sale.calculateTotalPrice();
         return sale.getTotalPrice();
    }
    /**
     * pay for the sale and the amount of change
     * @param paidAmount amount paid by the customer.
     * @return change the amount of change to be returned
     */
    public Amount pay(Amount paidAmount){
        Amount change = sale.pay(paidAmount);
        sale.printReceipt();
        return change;
    }     
    
    public void addSaleObserver(SaleObserver obs){
        saleObservers.add(obs);
        
    }
    
    public Sale getSale(){
        return this.sale;
    }
    /**
     * this method fetch discount corresponding to specified customer from discount data base and 
     * send it to sale for calculating.
     * @param customerID Id for the customer who's eligible for discount.
     */
    public void discountRequest(String customerID) throws NoDiscountFoundException  {
       List<DiscountDTO> availableDiscount = discountDB.fetchDiscountByCustomerID(customerID);
       sale.addDiscount(availableDiscount);
        
    }
}

