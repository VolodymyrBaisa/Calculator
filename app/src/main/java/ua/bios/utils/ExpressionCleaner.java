package ua.bios.utils;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by BIOS on 1/22/2017.
 */

public class ExpressionCleaner {
    private ExpressionCleaner() {
    }

    public static LinkedList<String> removeResultFromExpression(LinkedList<String> groupedExpression) {
        LinkedList<String> clearExpressionOfEqual = new LinkedList<>();
        for (String value : groupedExpression) {
            if (ExpressionTest.isExpression(value)) {
                if (!value.matches("^.*=[-\\d.\\D]+$") && !value.matches("^[-\\d.\\D]+$")) {
                    clearExpressionOfEqual.addAll(Arrays.asList(value.split("=")));
                } else {
                    clearExpressionOfEqual.add(value.replaceAll("=[-\\d.\\D]+$", ""));
                }
            } else {
                clearExpressionOfEqual.add(value);
            }
        }
        return clearExpressionOfEqual;
    }
}
