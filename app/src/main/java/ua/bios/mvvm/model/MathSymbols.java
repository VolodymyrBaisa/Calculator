package ua.bios.mvvm.model;

/**
 * Created by BIOS on 1/26/2017.
 */

public enum MathSymbols {
    PERCENTAGE("%"), SQUARE_ROOT("√");

    private String symbols;

    MathSymbols(String symbols){
        this.symbols = symbols;
    }

    public String getMathSymbols() {
        return symbols;
    }

    @Override
    public String toString() {
        return symbols;
    }
}
