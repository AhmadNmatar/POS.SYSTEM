package se.kth.iv1350.amazingpos.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.amazingpos.model.Amount;

/**
 *
 *The DiscountDB class represents a singleton database for managing discounts.
 * It provides methods to fetch discounts based on customer ID and ensures that
 * only one instance of the DiscountDB class is created.
 */
public class DiscountDB {
    private static final DiscountDB discountDB = new DiscountDB();
    private List<DiscountDTO> discounts = new ArrayList<>();
    /**
     * creat a new instance of discounts
     */
    private DiscountDB(){
        discounts.add(new DiscountDTO(new Amount(0.1), new ItemDTO("123", "Apple", new Amount(100.0), new Amount(0.25)),"19960101"));
        discounts.add(new DiscountDTO(new Amount(0.1), new ItemDTO("567", "Banan", new Amount(50.0), new Amount(0.12)),"19960101"));
    }
    /**
     * Method to get the singleton instance
     * @return the only instance of this class.
     */
    public static DiscountDB getInstance(){
        return discountDB;
    }
    /**
     * fetch discount information by customer ID
     * @param customerID for the costumer who is eligible for dicount
     * @return a list containing all discounts corresponding to the given customer id.
     * @throws NoDiscountFoundException when no discount corresponds to the given customer ID.
     */
    public List<DiscountDTO> fetchDiscountByCustomerID(String customerID) throws NoDiscountFoundException{
        
        if(checkIfCustomerIsEligibleForDiscount(customerID)){
           List<DiscountDTO> customerDiscounts = new ArrayList<>();
           for (DiscountDTO discount : discounts) {
                if (discount.getCustomerID().equals(customerID)) {
                    customerDiscounts.add(discount);
                }
            }
           return customerDiscounts;
        }
        throw new NoDiscountFoundException("No discount corresponds to the given customer ID.");

    }
    
    private boolean checkIfCustomerIsEligibleForDiscount(String customerID){
        boolean customerIsEligible = false;
        for(DiscountDTO discount : discounts){
            if (discount.getCustomerID().equals(customerID)){
               return customerIsEligible = true;
            }
        }
        return customerIsEligible;
    }
}
