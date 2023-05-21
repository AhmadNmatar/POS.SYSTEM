package se.kth.iv1350.amazingpos.model;

import java.time.LocalDateTime;
import java.util.List;
import se.kth.iv1350.amazingpos.integration.DiscountDTO;
import se.kth.iv1350.amazingpos.integration.ItemDTO;

/**
 *
 * Represent one reciept, which proves the payment of one sale
 */
public class Receipt {
    private Sale paidSale;
    
    /**
     * creates an new instance
     */
    public Receipt(){
        
    }
    /**
     * creat a new instance 
     * @param paidSale contains sale's information
     */
    public Receipt(Sale paidSale){
        this.paidSale = paidSale;
        
    }
    /*Sets the paid sale for the current transaction.
     *@param paidSale the sale object containing details of the items purchased
     *and the total cost of the transaction.
     *This method does not return anything.
    */
    public void setSale(Sale paidSale){
        this.paidSale = paidSale;
    }
    /**
     * This method prints the receipt of the transaction to the console output.
     */
    public void printReceipt(){
        System.out.println("----------------- Receipt follows --------------");
        LocalDateTime saleTime = LocalDateTime.now();
        System.out.println("Time of sale: " + saleTime.toString());
        System.out.println("\n");
        List<ItemDTO> sales = paidSale.getSales();
        for(ItemDTO item : sales){
            System.out.println("Item:\t\t\t\t " + item.getName());
            System.out.println("Quantity:\t\t\t " + item.getQuantity());
            System.out.println("Price:\t\t\t\t " + item.getPrice() + "kr");
            System.out.println("VAT:\t\t\t\t " + item.getVat().multiply(new Amount(100.00)) + "%");
            System.out.println("\n");
        }
        Amount totalPrice = paidSale.getTotalPrice();
        System.out.println("Total price:\t\t\t " + totalPrice + "kr");
        if(paidSale.getDiscounts() != null){
            Amount totalDiscount = totalDiscount(paidSale.getDiscounts());
            System.out.println("Total discount:\t\t\t " + totalDiscount.getValue() + "%");
            Amount totalPriceAfterDiscount = paidSale.getTotalPriceAfterDiscount();
            System.out.println("Total price after Discount:\t " + totalPriceAfterDiscount);
        }
        Amount vatForEntireSale = totalVat(sales);
        System.out.println("VAT for Entire Sale:\t\t " + vatForEntireSale.multiply(new Amount(100.00)) + "%");
        Amount paidAmount = paidSale.getCashPayment().getValue();
        System.out.println("The paid amount:\t\t " + paidAmount + "kr");
        
    }
    
    
    private Amount totalVat(List<ItemDTO> sales){
        Amount vatForEntireSale = new Amount(0.0);
        for(ItemDTO item : sales){
           vatForEntireSale = vatForEntireSale.add(item.getVat());
        }
        return vatForEntireSale;
    }
    
    private Amount totalDiscount (List<DiscountDTO> discounts){
        Amount totalDiscount = new Amount(0.0);
        for(DiscountDTO discount : discounts){
            totalDiscount = totalDiscount.add(discount.getDiscountPercentage().multiply(new Amount(100.00)));
        }
        return totalDiscount;
    }
}
