
package se.kth.iv1350.amazingpos.model;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.amazingpos.integration.ItemDTO;
import se.kth.iv1350.amazingpos.integration.Printer;
import se.kth.iv1350.amazingpos.integration.SaleLog;

/**
 *
 * @author ahmadmatar
 */
public class SaleTest {
    private ByteArrayOutputStream outContent;
    private PrintStream originalSysOut;
    private List<ItemDTO> sales;
    private Receipt receipt;
    private CashPayment cashPayment;
    private SaleLog saleLog;
    private Amount totalPrice;
    private Printer printer;
    private ItemDTO item1;
    private ItemDTO item2;
    private Sale instance;
    
    @BeforeEach
    public void setUp() {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        instance = new Sale();
        receipt = new Receipt();
        totalPrice = new Amount(0.0);
        sales = new ArrayList<>();
        item1 = new ItemDTO("123", "Apple", new Amount(100.0), new Amount(0.25));
        item2 = new ItemDTO("567", "Banan", new Amount(50.0), new Amount(0.12));
        printer = new Printer();
    }
    
    @AfterEach
    public void tearDown() {
        outContent = null;
        System.setOut(originalSysOut);
        receipt = null;
        totalPrice = null;
        sales = null;
        printer = null;
    }

    @Test
    public void testSaveSaleInformation() {
        instance = new Sale();
        sales = instance.getSales();
        instance.saveSaleInformation(item2);
        int result = sales.size();
        int expResult = 1;
        assertEquals(expResult, result);
       
    }

    @Test
    public void testCheckIfItemExist() {
        sales = instance.getSales();
        sales.add(item2);
        String itemIdentifier = "567";
        boolean expResult = true;
        boolean result = instance.checkIfItemExist(itemIdentifier);
        assertEquals(expResult, result);
       
    }

    @Test
    public void testIncreaseQuantityOfExistingItem() {
        sales = instance.getSales();
        sales.add(item2);
        String itemIdentifier = "567";
        instance.increaseQuantityOfExistingItem(itemIdentifier);
        int result = item2.getQuantity().getValue();
        int expResult = 2;
        assertEquals(expResult, result);
        
       
    }

    @Test
    public void testCalculateTotalPrice() {
        instance.saveSaleInformation(item2);
        Amount expResult = new Amount(56.0);
        instance.calculateTotalPrice();
        assertEquals(expResult.getValue(), instance.getTotalPrice().getValue(), 0.00001);
    }

    @Test
    public void testPay() {
        Amount paidAmount = new Amount(200.00);
        instance.saveSaleInformation(item1);
        instance.calculateTotalPrice();
        Amount vat1 = item1.getVatForCalculatingPrice();
        totalPrice = totalPrice.add(item1.getPrice().multiply(vat1.multiply(item1.getQuantity())));
        Amount expectedChange = paidAmount.subtract(totalPrice);
        Amount result = instance.pay(paidAmount);
        assertEquals(expectedChange.getValue(), result.getValue(), 0.0001);
    }

    @Test
    public void testPrintReceipt() {
        instance = new Sale();
        Amount paidAmount = new Amount(200.0);
        cashPayment = new CashPayment(paidAmount);
        instance.saveSaleInformation(item2);
        instance.calculateTotalPrice();
        Amount change = instance.pay(paidAmount);
        instance.printReceipt();
        LocalDateTime saleTime = LocalDateTime.now();
        String expResult = "Total Price: " + totalPrice.getValue();
        String result = outContent.toString();
        assertTrue(result.contains("Banan"), "Wrong printout.");
        assertTrue(result.contains(item2.getName()), "Wrong printout.");
        assertTrue(result.contains(Integer.toString(saleTime.getYear())), "Wrong rental year.");
        assertTrue(result.contains(Integer.toString(saleTime.getMonthValue())),"Wrong rental month.");
        assertTrue(result.contains(Integer.toString(saleTime.getDayOfMonth())),"Wrong rental day.");
        assertTrue(result.contains(Integer.toString(saleTime.getHour())), "Wrong rental hour.");
        assertTrue(result.contains(Integer.toString(saleTime.getMinute())), "Wrong rental minute.");
    }

    
}
