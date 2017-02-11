package ua.bios.mvvm.model;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;

import java.util.EmptyStackException;

import ua.bios.calculatormethods.OperatorAndSymbolsReplacer;
import ua.bios.utils.Formatter;

/**
 * Created by BIOS on 12/8/2016.
 */

public class Calculator {
    public String calculate(String expr) {
        String operatorsReplacedExpression = OperatorAndSymbolsReplacer.replaceOperatorAndSymbols(expr);
        try {
            Expression expression = new ExpressionBuilder(operatorsReplacedExpression).functions(getFunctionsArray()).build();
            if (expression.validate().isValid()) {
                try {
                    return Formatter.doubleToString(expression.evaluate());
                } catch (ArithmeticException e) {
                    return Messages.getErrorDivisionByZero();
                }
            } else {
                return Messages.getError();
            }
        } catch (IllegalArgumentException | EmptyStackException e) {
            return Messages.getError();
        }
    }

    private Function[] getFunctionsArray() {
        Function pctOneArg = new Function("pct", 1) {
            @Override
            public double apply(double... args) {
                double percent = args[0];
                if(percent < 0) {
                    return Math.abs(percent) / 100;
                } else {
                    return percent / 100;
                }
            }
        };

        Function pctTwoArgs = new Function("pct", 2) {
            @Override
            public double apply(double... args) {
                double value = args[0];
                double percent = args[1];
                if(percent < 0) {
                    return value * Math.abs(percent) / 100;
                } else {
                    return value * percent / 100;
                }
            }
        };

        return new Function[]{pctOneArg, pctTwoArgs};
    }
}
