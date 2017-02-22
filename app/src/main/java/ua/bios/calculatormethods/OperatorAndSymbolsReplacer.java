package ua.bios.calculatormethods;

import java.util.LinkedList;

import ua.bios.mvvm.model.MathSymbols;
import ua.bios.mvvm.model.Operators;
import ua.bios.mvvm.model.SettingsModel;
import ua.bios.utils.Parser;
import ua.bios.utils.StringMethods;

/**
 * Created by BIOS on 1/21/2017.
 */

public class OperatorAndSymbolsReplacer {
    private OperatorAndSymbolsReplacer() {
    }

    public static String replaceOperatorAndSymbols(String expression) {
        if (expression.contains(Operators.DIVIDE.toString())) {
            expression = expression.replaceAll(Operators.DIVIDE.toString(), "/");
        }

        if (expression.contains(Operators.MULTIPLY.toString())) {
            expression = expression.replaceAll(Operators.MULTIPLY.toString(), "*");
        }

        if (expression.contains(MathSymbols.PERCENTAGE.toString())) {
            expression = percentageReplacer(expression);
        }

        if (expression.contains(MathSymbols.SQUARE_ROOT.toString())) {
            expression = squareRootReplacer(expression);
        }
        return expression;
    }

    private static String squareRootReplacer(String expression) {
        LinkedList<String> groupElement = Parser.findGroupElement(expression, "√(\\([\\d.\\D]+\\)|-[\\d.]+|[\\d.]+)");
        for (String value : groupElement) {
            expression = expression.replace(MathSymbols.SQUARE_ROOT.toString().concat(value), "sqrt(".concat(value).concat(")"));
        }
        return expression;
    }

    private static String percentageReplacer(String expression) {
        String[] splitExpression = expression.split("(?<=%)(\\D)");
        for (String exprPiece : splitExpression) {
            if (exprPiece.contains(MathSymbols.PERCENTAGE.toString())) {
                String percentage = Parser.findElement(exprPiece, "[\\d.]+%").get(0);
                expression = collectPctFunction(expression, exprPiece, percentage);
            }

        }
        return expression;
    }

    private static String collectPctFunction(String expression, String exprPiece, String percentage) {
        if(exprPiece.contains(MathSymbols.PERCENTAGE.toString())) {
            String pct = createPctExpression(exprPiece, percentage);
            expression = expression.replace(exprPiece, pct);
        }
        return expression;
    }

    private static String createPctExpression(String exprPiece, String percentage) {
        StringBuilder pct = new StringBuilder();
        String exprPieceWithoutPercentage = exprPiece.replace(percentage, "");
        String percentageWithoutOperator = percentage.replace(MathSymbols.PERCENTAGE.toString(), "");
        pct.append(exprPieceWithoutPercentage).append("pct(")
        .append(StringMethods.deleteLastCharWithOperator(exprPieceWithoutPercentage))
        .append(",").append(percentageWithoutOperator).append(")");
        return pct.toString();
    }
}
