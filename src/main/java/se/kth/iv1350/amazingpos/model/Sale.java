package se.kth.iv1350.amazingpos.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.amazingpos.integration.DiscountDB;
import se.kth.iv1350.amazingpos.integration.DiscountDTO;
import se.kth.iv1350.amazingpos.integration.ItemDTO;
import se.kth.iv1350.amazingpos.integration.Printer;
import se.kth.iv1350.amazingpos.integration.SaleLog;
import se.kth.iv1350.amazingpos.view.TotalRevenueFileOutput;
import se.kth.iv1350.amazingpos.view.TotalRevenueView;
/**
 *
 * Represent one single sale made by one single customer and paid with one payment
 */
public class Sale {
    private List<SaleObserver> saleObservers = new ArrayList<>();
    private List<ItemDTO> sales;
    private LocalTime saleTime;
    private Receipt receipt;
    private CashPayment cashPayment;
    private SaleLog saleLog;
    private Amount totalPrice;
    private Amount totalPriceAfterDiscount;
    private Printer printer;
    private List<DiscountDTO> discounts;
    private PriceCalculator calculator;

    /**
     * creates a new instance, save the time of Sale and generate reciept
     */
    public Sale(){
        saleTime = LocalTime.now();
        receipt = new Receipt();
        totalPrice = new Amount(0.0);
        totalPriceAfterDiscount = new Amount(0.0);
        sales = new ArrayList<>();
        printer = new Printer();
        saleLog = new SaleLog();   
    }

    /**
     * save sold item
     * @param item the sold item
     * 
     */
    public void saveSaleInformation(ItemDTO item){
        sales.add(item);
    }
    /**
     * The methog check if item has been entred.
     * @param itemIdentifier item's identifier used for searching for item
     * @return the method true if item exists, eller false.
     */
    public boolean checkIfItemExist (String itemIdentifier){
        boolean itemExist = false;
        for(ItemDTO item : sales){
            if(item.getItemId().equals(itemIdentifier)){
                itemExist = true;
                break;
            }
        }
        return itemExist;
    }
     /**
     * This method increases the quantity of an existing item
     * @param itemIdentifier used for matching items.
     */
    public void increaseQuantityOfExistingItem(String itemIdentifier) {
       for(ItemDTO item : sales) {
            if (item.getItemId().equals(itemIdentifier)) {
                Quantity quantity = item.getQuantity();
                quantity.increaseQuantity();
                break;
            }
       }
    }
   /**
     * calculate the total price for the current sale.
     */
    public void calculateTotalPrice(){
        if(discounts == null){
            calculator = new PriceCalculatorWithoutDiscount(sales);
            totalPrice = calculator.calculateTotalPrice();   
        }
        else {
            calculator = new PriceCalculatorWithDiscount(discounts, sales);
            totalPriceAfterDiscount = calculator.calculateTotalPrice();
        }
    }
    /**
     * pay operation take the paid amount and return amount change
     * @param paidAmount the amount paid by the customer
     * @return amount that customer should recieve
     */
    public Amount pay(Amount paidAmount){
        cashPayment = new CashPayment(paidAmount);
        Amount change = cashPayment.calculateChange(this.totalPrice);
        saleLog.addSale(this);
        notifyObservers();
        return change;
    }
    
    private void notifyObservers() {
        for (SaleObserver obs : saleObservers) {
            if(totalPriceAfterDiscount.getValue() > 0.0){
                obs.newSale(totalPriceAfterDiscount);
            }
            else{
                obs.newSale(totalPrice);
            }
        }
    }
    public void addSaleObserver(SaleObserver obs){
        saleObservers.add(obs);
    }
    /**
     * All the specified observers will be notified when this rental has been paid.
     * 
     * @param observers The observers to notify. 
     */
    public void addSaleObserver(List<SaleObserver> observers){
        saleObservers.addAll(observers);
    }
    /**
     * This method prints a receipt using the provided Reciept object.
     * The receipt includes information about the current sale, the paid amount,
     * and the change amount.
     * @pram printer 
     * 
     */
    public void printReceipt(){
        receipt.setSale(this);
        printer.printReceipt(receipt);
    }
    /**
     * add availbe discount for the current sale
     * @param availableDiscount available discount for the current sale
     */
    public void addDiscount(List<DiscountDTO> availableDiscount){
        this.discounts = availableDiscount;
    }

    public List<ItemDTO> getSales() {
        return sales;
    }

    public LocalTime getSaleTime() {
        return saleTime;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public CashPayment getCashPayment() {
        return cashPayment;
    }

    public Amount getTotalPrice() {
        return totalPrice;
    }
    public List<DiscountDTO> getDiscounts(){
        return discounts;
    }
        
    public Amount getTotalPriceAfterDiscount(){
        return this.totalPriceAfterDiscount;
    }
}


