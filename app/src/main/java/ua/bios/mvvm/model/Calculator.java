package ua.bios.mvvm.model;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Locale;

/**
 * Created by BIOS on 12/8/2016.
 */

public class Calculator {
    public String calculate(String exp) {
        try {
            exp = autoReplaseOperators(exp);
            Expression expression = new ExpressionBuilder(exp).build();
            return format(expression.evaluate());
        } catch (IllegalArgumentException e){
            return ErrorMessages.getError();
        }
    }

    private String format(double d)
    {
        if(d == (long) d)
            return String.format(Locale.US, "%d",(long)d);
        else
            return String.format("%s", d);
    }

    private String autoReplaseOperators(String exp){
        if(exp.contains(Operators.DIVIDE.toString())){
           exp = exp.replaceAll(Operators.DIVIDE.toString(), "/");
        }

        if(exp.contains(Operators.MULTIPLY.toString())){
            exp = exp.replaceAll(Operators.MULTIPLY.toString(), "*");
        }
            return exp;
    }
}
