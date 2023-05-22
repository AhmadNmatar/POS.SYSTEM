
package se.kth.iv1350.amazingpos.startup;

import se.kth.iv1350.amazingpos.controller.Controller;
import se.kth.iv1350.amazingpos.integration.DiscountDB;
import se.kth.iv1350.amazingpos.integration.Printer;
import se.kth.iv1350.amazingpos.integration.SaleLog;
import se.kth.iv1350.amazingpos.view.View;

/**
 *
 * Starts the entier application, contain the main method used to start the application.
 */
public class Main {
    /**
     * contain the main method used to start the application
     * @param args the application does not take any parameters
     */

    public static void main(String[] args)  {
        DiscountDB discountDB = DiscountDB.getInstance();
        Printer printer = new Printer();
        SaleLog saleLog = new SaleLog();
        Controller control = new Controller(printer, saleLog, discountDB);
        View view = new View(control);
        view.runFakeExcution();
        view.runFakeExcutionWithDiscount();
    }
}
