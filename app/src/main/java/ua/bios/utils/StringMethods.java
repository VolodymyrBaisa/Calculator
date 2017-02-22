package ua.bios.utils;

/**
 * Created by BIOS on 2/21/2017.
 */

public class StringMethods {
    private StringMethods(){}

    public static String deleteLastCharWithOperator(String str) {
        if (str != null && str.length() > 0 && (str.charAt(str.length()-1)=='-'
                                                || str.charAt(str.length()-1)=='+'
                                                || str.charAt(str.length()-1)=='*'
                                                || str.charAt(str.length()-1)=='/')) {
            str = str.substring(0, str.length()-1);
        }
        return str;
    }
}
