package se.kth.iv1350.amazingpos.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 *
 * This class is responsible for the log.
 */
public class LogHandler {
    private static final String LOG_FILE_NAME = "POS System-Log.txt";
    private PrintWriter logFile;
    
    /**
     * creates a constructor.
     * 
     */
    public LogHandler() {
        try{
        logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME, true), true);
        } catch(IOException exp){
            System.out.println("Could not create logger.");
            exp.printStackTrace();
        }
    }

    /**
     * Writes a log entry describing a thrown exception.
     *
     * @param exp The exception that shall be logged.
     */
    public void writeTheExceptionToLogFile(Exception exp) {
       StringBuilder logMsgBuilder = new StringBuilder();
        logMsgBuilder.append(createTime());
        logMsgBuilder.append(", Exception was thrown: ");
        logMsgBuilder.append(exp.getMessage());
        logFile.println(logMsgBuilder);
        exp.printStackTrace(logFile);
        logFile.println("\n");
    }

    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.
                ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);

    }
    
}
