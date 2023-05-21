package se.kth.iv1350.amazingpos.controller;

/**
 *
 * Thrown when access faild to data base
 */
public class DataBaseHandlerException extends Exception {
    
    public DataBaseHandlerException(String msg){
        super(msg);
    }
    
}
