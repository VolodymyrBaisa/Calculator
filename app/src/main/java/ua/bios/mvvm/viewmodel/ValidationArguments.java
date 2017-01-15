package ua.bios.mvvm.viewmodel;

import java.util.LinkedList;

import ua.bios.mvvm.model.Operators;

/**
 * Created by BIOS on 1/2/2017.
 */

public class ValidationArguments {
    public boolean validate(LinkedList<String> exp, String nextValue) {
        if (!exp.isEmpty()) {
            String value = exp.getLast();

            if ((isContainsDot(value) || isContainsOperator(value)) && isContainsDot(nextValue)) {
                return false;
            }

            if (ifLastIndexIsDot(value) && isContainsOperator(nextValue)) {
                return false;
            }

            if (isContainsOperator(value) && isContainsOperator(nextValue)) {
                return false;
            }

            if (isMatchesZero(value) && isMatchesZero(nextValue)) {
                return false;
            }
        }

        return true;
    }

    public boolean isContainsDot(String value) {
        String dot = ".";
        return value.contains(dot);
    }

    public boolean isContainsOperator(String value) {
        String divide = Operators.DIVIDE.getOperator();
        String multiply = Operators.MULTIPLY.getOperator();
        String subtract = Operators.SUBTRACT.getOperator();
        String plus = Operators.PLUS.getOperator();

        if (value.contains(divide) || value.contains(multiply) ||
                value.contains(subtract) || value.contains(plus)) {
            return true;
        }
        return false;
    }

    public boolean isMatchesZero(String value) {
        return value.matches("^0+");
    }

    public boolean ifLastIndexIsDot(String value) {
        if(!value.isEmpty()) {
            return String.valueOf(value.charAt(value.length() - 1)).equals(".");
        }
        return false;
    }

    public boolean isFractional(String value) {
        return value.contains(".");
    }

    public boolean isContainsEquals(String value) {
        return value.contains("=");
    }

    public boolean isLeadingZeros(String value, String nextValue){
        return isMatchesZero(value) && !isFractional(nextValue);
    }
}
