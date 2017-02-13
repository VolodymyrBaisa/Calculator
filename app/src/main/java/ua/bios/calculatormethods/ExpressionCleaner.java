package ua.bios.calculatormethods;

import java.util.Arrays;
import java.util.LinkedList;

import ua.bios.mvvm.model.Messages;

/**
 * Created by BIOS on 1/22/2017.
 */

public class ExpressionCleaner {

    // Because Everything is static, you do not need the constructor below
    private ExpressionCleaner() {
    }

    // The method name is not telling its intention
    public static LinkedList<String> removeResultFromExpression(LinkedList<String> groupedExpression) {

        // Hard to understand clearExpressionOfEqual, may be cleanExpressionOfEqual
        LinkedList<String> clearExpressionOfEqual = new LinkedList<>();
        for (String value : groupedExpression) {

            // ExpressionTest.isExpression should be in this class only if this class is using ExpressionTest.isExpression
            if (ExpressionTest.isExpression(value)) {
                if (!value.matches("^.*=[\\d.]+$") && !value.matches("^[-\\d.]+$")) {
                    clearExpressionOfEqual.addAll(Arrays.asList(value.split("=")));
                } else {
                    clearExpressionOfEqual.add(value.replaceAll("=[-\\d.]+$", ""));
                }
            } else {
                clearExpressionOfEqual.add(value);
            }
        }
        return clearExpressionOfEqual;
    }

    public static String clearMessageAfterEqual(String expression){
        String[] allMessages = Messages.getAllMessages();
        for(String message : allMessages){
            expression = expression.replaceAll(message, "");
        }
        return expression;
    }
}
