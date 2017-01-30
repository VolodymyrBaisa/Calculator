package ua.bios.mvvm.viewmodel;

import ua.bios.mvvm.model.Operators;

/**
 * Created by BIOS on 1/2/2017.
 */

public class ValidationArguments {
    public boolean validate(String value, String nextValue) {
        if (!value.isEmpty()) {

            if ((isContainsDot(value) || isContainsOperator(value)) && isContainsDot(nextValue)) {
                return false;
            }

            if (ifLastIndexIsDot(value) && isContainsOperator(nextValue)) {
                return false;
            }

            if (isContainsOperator(value) && isContainsOperator(nextValue)) {
                return false;
            }
        } else {
            if (isContainsSubtract(nextValue)) {
                return true;
            }

            if (isContainsOperator(nextValue) || isContainsDot(nextValue)) {
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

    public boolean isEqualsZero(String value) {
        return value.equals("0");
    }

    public boolean ifLastIndexIsDot(String value) {
        if (!value.isEmpty()) {
            return String.valueOf(value.charAt(value.length() - 1)).equals(".");
        }
        return false;
    }

    public boolean isFractional(String value) {
        return value.contains(".");
    }

    public boolean isContainsSubtract(String value) {
        String subtract = Operators.SUBTRACT.getOperator();
        return value.contains(subtract);
    }

    public boolean isLeadingZero(String value, String nextValue) {
        return isEqualsZero(value) && !isFractional(nextValue) && !isContainsOperator(nextValue);
    }
}
