package ua.bios.mvvm.model;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;

import ua.bios.utils.Formatter;
import ua.bios.utils.OperatorReplacer;

/**
 * Created by BIOS on 12/8/2016.
 */

public class Calculator {
    public String calculate(String expr) {
        String operatorsReplacedExpression = OperatorReplacer.replaseOperator(expr);
        try {
            Expression expression = new ExpressionBuilder(operatorsReplacedExpression).functions(getFunctionsArray()).build();
            if (expression.validate().isValid()) {
                try {
                    return Formatter.doubleToString(expression.evaluate());
                } catch (ArithmeticException e) {
                    return ErrorMessages.getErrorDivisionByZero();
                }
            } else {
                return ErrorMessages.getError();
            }
        } catch (IllegalArgumentException e) {
            return ErrorMessages.getError();
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
