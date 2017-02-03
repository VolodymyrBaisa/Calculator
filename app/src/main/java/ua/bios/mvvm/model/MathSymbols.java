package ua.bios.mvvm.model;

/**
 * Created by BIOS on 1/26/2017.
 */

public enum MathSymbols {
    PERCENTAGE("%"), SQUARE_ROOT("√");

    private final String symbols;

    MathSymbols(String symbols){
        this.symbols = symbols;
    }

    @Override
    public String toString() {
        return symbols;
    }
}
