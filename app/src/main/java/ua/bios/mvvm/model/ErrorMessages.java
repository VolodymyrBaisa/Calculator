package ua.bios.mvvm.model;

/**
 * Created by BIOS on 1/8/2017.
 */

public class ErrorMessages {
    private final static String error = "Error";
    private final static String errorDivisionByZero = "Division by zero!";

    private ErrorMessages() {
    }

    public static String getError(){
        return error;
    }

    public static String getErrorDivisionByZero(){
        return errorDivisionByZero;
    }
}
