package ua.bios.calculatormethods;

import java.util.Arrays;
import java.util.LinkedList;

import ua.bios.calculatormethods.ExpressionTest;
import ua.bios.mvvm.model.Messages;

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
