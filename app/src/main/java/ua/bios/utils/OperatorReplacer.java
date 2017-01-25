package ua.bios.utils;

import java.util.LinkedList;

import ua.bios.mvvm.model.Operators;

/**
 * Created by BIOS on 1/21/2017.
 */

public class OperatorReplacer {
    private OperatorReplacer() {
    }

    public static String replaseOperator(String expression) {
        if (expression.contains(Operators.DIVIDE.toString())) {
            expression = expression.replaceAll(Operators.DIVIDE.toString(), "/");
        }

        if (expression.contains(Operators.MULTIPLY.toString())) {
            expression = expression.replaceAll(Operators.MULTIPLY.toString(), "*");
        }

        if (expression.contains(Operators.PERCENTAGE.toString())) {
            expression = percentageReplacer(expression);
        }

        if (expression.contains(Operators.SQUARE_ROOT.toString())) {
            expression = squareRootReplacer(expression);
        }
        return expression;
    }

    private static String squareRootReplacer(String expression) {
        LinkedList<String> groupElement = Parser.findGroupElement(expression, "√(\\([\\d.\\D]+\\)|-[\\d.]+|[\\d.]+)");
        for (String value : groupElement) {
            expression = expression.replace("√".concat(value), "sqrt(".concat(value).concat(")"));
        }
        return expression;
    }

    private static String percentageReplacer(String expression) {
        String[] splitExpression = expression.split("(?<=%)(\\D)");
        for (String expr : splitExpression) {
            if (expr.contains("%")) {
                String[] splitExpressionWithPercentage = expr.split("\\D(?=[\\d.]+%)");
                expression = collectPctFunction(expression, splitExpressionWithPercentage);
            }

        }
        return expression;
    }

    private static String collectPctFunction(String expression, String[] splitExpressionWithPercentage) {
        StringBuilder pct = new StringBuilder("pct(");
        for (String value : splitExpressionWithPercentage) {
            if (!value.contains("%")) {
                String replaceParenthesis = value.replaceAll("\\(|\\)", "");
                pct.append(replaceParenthesis.concat(","));
            } else {
                String replacePercentage = value.replace("%", "");
                pct.append(replacePercentage).append(")");
                expression = expression.replace(value, pct);
            }
        }
        return expression;
    }
}
