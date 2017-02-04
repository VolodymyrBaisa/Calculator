package ua.bios.calculatormethods;

import ua.bios.mvvm.model.Operators;

/**
 * Created by BIOS on 1/2/2017.
 */

public class ValidationArguments {
    public boolean validate(String value, String nextValue) {
        if (!value.isEmpty()) {

            if ((isContainsDot(value) || isEqualsOperator(value)) && isContainsDot(nextValue)) {
                return false;
            }

            if (ifLastIndexIsDot(value) && isEqualsOperator(nextValue)) {
                return false;
            }

            if (isEqualsOperator(value) && isEqualsOperator(nextValue)) {
                return false;
            }
        } else {
            if (isContainsSubtract(nextValue)) {
                return true;
            }

            if (isEqualsOperator(nextValue) || isContainsDot(nextValue)) {
                return false;
            }
        }
        return true;
    }

    private boolean isContainsDot(String value) {
        String dot = ".";
        return value.contains(dot);
    }

    private boolean isEqualsOperator(String value) {
        String divide = Operators.DIVIDE.getOperator();
        String multiply = Operators.MULTIPLY.getOperator();
        String subtract = Operators.SUBTRACT.getOperator();
        String plus = Operators.PLUS.getOperator();

        return (value.contains(divide) || value.contains(multiply) ||
                value.contains(subtract) || value.contains(plus));
    }

    private boolean isEqualsZero(String value) {
        return value.equals("0");
    }

    private boolean ifLastIndexIsDot(String value) {
        return !value.isEmpty() && String.valueOf(value.charAt(value.length() - 1)).equals(".");
    }

    private boolean isFractional(String value) {
        return value.contains(".");
    }

    private boolean isContainsSubtract(String value) {
        String subtract = Operators.SUBTRACT.getOperator();
        return value.contains(subtract);
    }

    public boolean isLeadingZero(String value, String nextValue) {
        return isEqualsZero(value) && !isFractional(nextValue) && !isEqualsOperator(nextValue);
    }
}
