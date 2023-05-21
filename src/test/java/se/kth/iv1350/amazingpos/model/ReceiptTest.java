package se.kth.iv1350.amazingpos.model;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.amazingpos.integration.ItemDTO;

public class ReceiptTest {

    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;
    private Sale sale;
    private Receipt receipt;

    @BeforeEach
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        sale = new Sale();
        ItemDTO item1 = new ItemDTO("123", "Apple", new Amount(100.0), new Amount(0.25));
        ItemDTO item2 = new ItemDTO("567", "Banan", new Amount(50.0), new Amount(0.12));
        sale.getSales().add(item1);
        sale.getSales().add(item2);
        sale.pay(new Amount(200.0));
        receipt = new Receipt(sale);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testPrintReceipt() {
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        sale = new Sale();
        ItemDTO item1 = new ItemDTO("123", "Apple", new Amount(100.0), new Amount(0.25));
        ItemDTO item2 = new ItemDTO("567", "Banan", new Amount(50.0), new Amount(0.12));
        sale.getSales().add(item1);
        sale.getSales().add(item2);
        sale.pay(new Amount(200.0));
        receipt = new Receipt(sale);
        sale.calculateTotalPrice();
        assertNotNull(receipt, "Receipt object is null.");
        receipt.printReceipt();
        String expectedOutput = "Time of sale: ";
        String result = outContent.toString();
        assertTrue(result.contains(expectedOutput), "Receipt printing failed. ");
        expectedOutput = "item ";
        assertTrue(result.contains(expectedOutput), "Receipt printing failed. ");
        expectedOutput = "item Banan\nquantity: 1";
        assertTrue(result.contains(expectedOutput), "Receipt printing failed. ");
        expectedOutput = "TotalPrice:";
        assertTrue(result.contains(expectedOutput), "Receipt printing failed. " );
        expectedOutput = "VAT for Entire Sale:";
        assertTrue(result.contains(expectedOutput), "Receipt printing failed. ");
        expectedOutput = "The paid amount";
        assertTrue(result.contains(expectedOutput), "Receipt printing failed. ");
        expectedOutput = "TotalPrice:";
        assertTrue(result.contains(expectedOutput), "Receipt printing failed. ");
    }
}



