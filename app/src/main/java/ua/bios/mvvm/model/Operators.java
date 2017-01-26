package ua.bios.mvvm.model;

/**
 * Created by BIOS on 1/2/2017.
 */

public enum Operators {
    PLUS("+"), DIVIDE("÷"), MULTIPLY("×"), SUBTRACT("-"), PERCENTAGE("%"), SQUARE_ROOT("√"), EQUAL("="), NEW_LINE("\n");

    private String operator;

    Operators(String operator){
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return operator;
    }
}
