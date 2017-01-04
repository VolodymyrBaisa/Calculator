package ua.bios.mvvm.viewmodel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by BIOS on 1/2/2017.
 */

public class Formatter {
    private static final String regex = "[-]?[0-9]*\\.?[0-9]+([eE][-]?[0-9]+)?";

    private Formatter() {
    }

    public static String stringFormat(String exp) {
        StringBuilder expression = new StringBuilder();
        expression.append(exp);

        if (expression.length() != 0) {
            replace(",", "", expression);
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(expression.toString());
            while (m.find()) {
                String oldChar = m.group();
                String newChar = doubleToString(Double.parseDouble(oldChar));

                if (!isFractional(oldChar)) {
                    replace(oldChar, newChar, expression);
                }
            }
        }
        return expression.toString();
    }

    private static void replace(String target, String replacement, StringBuilder builder) {
        for (int index = builder.indexOf(target); index >= 0; index = builder.indexOf(target, index + 1)) {
            builder.replace(index, index + target.length(), replacement);
        }
    }

    private static boolean isFractional(String text) {
        if (text.contains(".")) {
            return true;
        }
        return false;
    }

    public static String doubleToString(double d) {
        final DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(',');

        final DecimalFormat formatter = new DecimalFormat();
        formatter.setMaximumFractionDigits(12);
        formatter.setDecimalFormatSymbols(symbols);
        formatter.setGroupingUsed(true);
        return formatter.format(d);
    }
}
