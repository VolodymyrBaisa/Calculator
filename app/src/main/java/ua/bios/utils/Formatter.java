package ua.bios.utils;

import java.text.DecimalFormat;

/**
 * Created by BIOS on 1/16/2017.
 */

public class Formatter {

    private Formatter() {}

    // Rename the parameter d
    // Rename the method name
    public static String doubleToString(double d) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(d);
    }
}
