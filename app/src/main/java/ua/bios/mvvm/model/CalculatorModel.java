package ua.bios.mvvm.model;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 * Created by BIOS on 12/8/2016.
 */

public class CalculatorModel {
    public Double calculate(String exp) {
        Expression e = new ExpressionBuilder(exp).build();

        return e.evaluate();
    }
}
