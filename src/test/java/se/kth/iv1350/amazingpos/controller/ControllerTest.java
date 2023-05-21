package se.kth.iv1350.amazingpos.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.amazingpos.integration.AccountingSystem;
import se.kth.iv1350.amazingpos.integration.InventorySystem;
import se.kth.iv1350.amazingpos.integration.ItemDTO;
import se.kth.iv1350.amazingpos.integration.ItemDescription;
import se.kth.iv1350.amazingpos.integration.Printer;
import se.kth.iv1350.amazingpos.integration.SaleLog;
import se.kth.iv1350.amazingpos.model.Amount;
import se.kth.iv1350.amazingpos.model.Quantity;
import se.kth.iv1350.amazingpos.util.LogHandler;

import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.amazingpos.controller.Controller;
import se.kth.iv1350.amazingpos.controller.DataBaseHandlerException;
import se.kth.iv1350.amazingpos.integration.DiscountDB;
import se.kth.iv1350.amazingpos.integration.InvalidItemIdentifierException;
import se.kth.iv1350.amazingpos.model.Sale;

public class ControllerTest {
    private Controller instance;
    private Printer printer;
    private SaleLog saleLog;
    private InventorySystem inventorySystem;
    private AccountingSystem accountingSystem;
    private ItemDescription itemDescription;
    private LogHandler logHandler;
    private Sale sale;
    private DiscountDB discountDB;

   
    @BeforeEach
    public void setUp() {
        printer = new Printer();
        saleLog = new SaleLog();
        discountDB = DiscountDB.getInstance();
        itemDescription = new ItemDescription();
        logHandler = new LogHandler();
        inventorySystem = new InventorySystem();
        accountingSystem = new AccountingSystem();
        instance = new Controller(printer, saleLog, discountDB);
         
    }
   
    @AfterEach
    public void tearDown(){
        instance = null;
        
    }

    @Test
    public void testStartSale(){
        instance.startSale();
        assertNotNull(instance.getSale());
    }

    @Test
    public void testSearchItemExistingItem() throws InvalidItemIdentifierException, DataBaseHandlerException {
        instance.startSale();
        sale = instance.getSale();
        ItemDTO item = new ItemDTO("123", "Apple", new Amount(100.0), new Amount(0.25));
        sale.saveSaleInformation(item);
        instance.searchItem("123", new Quantity(1));
        int result = sale.getSales().get(0).getQuantity().getValue();
        int expResult = 2;
        assertEquals(expResult,result);
    }

    @Test
    public void testSearchItemNewItem() throws InvalidItemIdentifierException, DataBaseHandlerException {
        instance.startSale();
        sale = instance.getSale();
        instance.searchItem("567", new Quantity(1));
        boolean itemFound = sale.checkIfItemExist("567");
        
        assertEquals(true,itemFound);
    }

    @Test
    public void testSearchItemInvalidItemIdentifier() {
        instance.startSale();
        sale = instance.getSale();
        assertThrows(InvalidItemIdentifierException.class, () -> {instance.searchItem("999", new Quantity(1));});
    }

    @Test
    public void testSearchItemDataBaseHandlerException() throws SQLException {
        instance.startSale();
        sale = instance.getSale();
        assertThrows(DataBaseHandlerException.class, () -> {instance.searchItem("111", new Quantity(1));});
    }

    @Test
    public void testEndSale() throws InvalidItemIdentifierException, DataBaseHandlerException {
        instance.startSale();
        instance.searchItem("123", new Quantity(1));
        instance.searchItem("567", new Quantity(1));
        sale = instance.getSale();
        Amount totalPrice = instance.endSale();
        ItemDTO item1 = new ItemDTO("123", "Apple", new Amount(100.0), new Amount(0.25));
        ItemDTO item2 = new ItemDTO("567", "Banan", new Amount(50.0), new Amount(0.12));
        Amount total1 = item1.getPrice().multiply(item1.getVatForCalculatingPrice()).multiply(item1.getQuantity());
        Amount total2 = item2.getPrice().multiply(item2.getVatForCalculatingPrice()).multiply(item2.getQuantity());
        Amount expResult = total1.add(total2);
        assertNotNull(totalPrice);
        assertEquals(totalPrice.getValue(),expResult.getValue(),"different values");
    }

    @Test
    public void testPay() throws InvalidItemIdentifierException, DataBaseHandlerException {
        instance.startSale();
        instance.searchItem("123", new Quantity(1));
        instance.searchItem("567", new Quantity(1));
        Amount paidAmount = new Amount(300.0);
        Amount change = instance.pay(paidAmount);
        assertNotNull(change);
        assertTrue(change.getValue() >= 0);
    }
}
