package ua.bios.mvvm.model;

/**
 * Created by BIOS on 1/8/2017.
 */

public class Messages {
    private final static String[] msg = new String[]{"Error", "Division by zero!", "NaN"};

    private Messages() {
    }

    public static String getError() {
        return msg[0];
    }

    public static String getErrorDivisionByZero() {
        return msg[1];
    }

    public static String getNaN(){
        return msg[2];
    }

    public static String[] getAllMessages(){
        return msg;
    }

}
