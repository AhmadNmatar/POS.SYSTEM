package se.kth.iv1350.amazingpos.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import se.kth.iv1350.amazingpos.model.Amount;
import se.kth.iv1350.amazingpos.model.SaleObserver;

/**
 *
 * write the total income to a file.
 */
public class TotalRevenueFileOutput implements SaleObserver{
    private static final String LOG_FILE_NAME = "TotalRevenue.txt";
    private PrintWriter totalRevenueFile;
    private Amount totalIncome = new Amount(0.0);
    
    public TotalRevenueFileOutput(){
        try{
        totalRevenueFile = new PrintWriter(new FileWriter(LOG_FILE_NAME, true),true);
        } catch(IOException exp){
            System.out.println("Could not create logger.");
            exp.printStackTrace();
        }
    }
    
    @Override
    public void newSale(Amount paidSale) {
         totalIncome = totalIncome.add(paidSale);
         writeTotalIncomeToFile();
    }
    
    /**
     * Writes the total income to the file.
     *
     * @param exp The exception that shall be logged.
     */
    private void writeTotalIncomeToFile() {
       StringBuilder logMsgBuilder = new StringBuilder();
        logMsgBuilder.append(createTime());
        logMsgBuilder.append("\t The total income for today:");
        totalRevenueFile.println(logMsgBuilder);
        printNewLine();
        totalRevenueFile.println("Total income:\t\t" + totalIncome + " SEK");
        printNewLine();
    }
    

    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.
                ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
    
    private void printNewLine(){
        totalRevenueFile.println("\n");
    }

    
}
    

