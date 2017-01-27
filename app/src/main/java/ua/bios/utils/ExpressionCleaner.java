package ua.bios.utils;

import java.util.Arrays;
import java.util.LinkedList;

import ua.bios.mvvm.model.FunctionNames;
import ua.bios.mvvm.model.MathSymbols;

/**
 * Created by BIOS on 1/22/2017.
 */

public class ExpressionCleaner {
    private ExpressionCleaner() {
    }

    public static LinkedList<String> removeAllEqualFromExpression(LinkedList<String> groupedExpression) {
        LinkedList<String> clearExpressionOfEqual = new LinkedList<>();
        for (String value : groupedExpression) {
            if (!value.matches("^.*=[-\\d.]+$") && !value.matches("^[-\\d.]+$")) {
                clearExpressionOfEqual.addAll(Arrays.asList(value.split("=")));
            } else {
                clearExpressionOfEqual.add(value.replaceAll("=[-\\d.]+$", ""));
            }
        }
        return clearExpressionOfEqual;
    }

    public static String cleanerErrorMsg(String expression, String error) {
        if (expression.contains(error)) {
            return expression.replaceAll("=".concat(error), "");
        }
        return expression;
    }

    public static String extractGTExpression(String expression, String gtExpression){
        if(expression.contains(gtExpression)){
            return expression.replaceAll(FunctionNames.getGrandTotal().concat("=[-\\d.]+"),"");
        }
        return expression;
    }
}
