package ua.bios.mvvm.model;

/**
 * Created by BIOS on 1/8/2017.
 */

public class ErrorMessages {
    private final static String ERROR = "Error";
    private final static String ERROR_DIVISION_BY_ZERO = "Division by zero!";
    private final static String NAN = "NaN";

    private ErrorMessages() {
    }

    public static String getError(){
        return ERROR;
    }

    public static String getErrorDivisionByZero(){
        return ERROR_DIVISION_BY_ZERO;
    }

    public static String getErrorNaN(){
        return NAN;
    }
}
