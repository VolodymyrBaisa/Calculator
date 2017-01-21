package ua.bios.mvvm.model;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;

import java.util.LinkedList;

import ua.bios.utils.Formatter;
import ua.bios.utils.Parser;

/**
 * Created by BIOS on 12/8/2016.
 */

public class Calculator {
    public String calculate(String exp) {
        exp = autoReplaseOperators(exp);

        Function pctOneArg = new Function("pctOneArg", 1) {
            @Override
            public double apply(double... args) {
                return args[0] / 100;
            }
        };

        Function pctTwoArgs = new Function("pctTwoArgs", 2) {
            @Override
            public double apply(double... args) {
                return args[0] * args[1] / 100;
            }
        };

        Function[] functions = new Function[]{pctOneArg, pctTwoArgs};

        Expression expression = new ExpressionBuilder(exp).functions(functions).build();
        if (expression.validate().isValid()) {
            return Formatter.doubleToString(expression.evaluate());
        } else {
            return ErrorMessages.getError();
        }
    }

    private String autoReplaseOperators(String exp) {
        if (exp.contains(Operators.DIVIDE.toString())) {
            exp = exp.replaceAll(Operators.DIVIDE.toString(), "/");
        }

        if (exp.contains(Operators.MULTIPLY.toString())) {
            exp = exp.replaceAll(Operators.MULTIPLY.toString(), "*");
        }

        if (exp.contains(Operators.PERCENTAGE.toString())) {
            exp = parseExpAndGatherPctFunction(exp);
        }

        if (exp.contains(Operators.SQUARE_ROOT.toString())){

        }
        return exp;
    }

    private String parseExpAndGatherPctFunction(String exp) {
        StringBuilder result = new StringBuilder();
        LinkedList<String> expressionList = Parser.find(exp, "[-*/+\\d.()√]+[%()]+");
        for (String expression : expressionList) {

            LinkedList<String> valuesList = Parser.groupSplitter(expression,
                    "[-+*/]([-+*/\\d]+)[-+*/](\\d+%)|([-+*/\\d]+)[-+*/](\\d+%)|(\\d+%)");

            String number, percentage;
            if ((number = valuesList.get(0)) != null && (percentage = valuesList.get(1)) != null
                    || (number = valuesList.get(2)) != null && (percentage = valuesList.get(3)) != null) {
                String pct = "pctTwoArgs(".concat(number).concat(", ").concat(percentage.replaceAll("%", "")).concat(")");
                result.append(expression.replaceAll("\\d+%", pct));
            } else if ((percentage = valuesList.get(4)) != null) {
                String pct = "pctOneArg(".concat(percentage.replaceAll("%", "")).concat(")");
                result.append(expression.replaceAll("\\d+%", pct));
            }

        }
        return result.length() != 0 ? result.toString() : ErrorMessages.getError();
    }
}
