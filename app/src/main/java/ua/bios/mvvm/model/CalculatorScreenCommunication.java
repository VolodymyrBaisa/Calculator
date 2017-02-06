package ua.bios.mvvm.model;

import ua.bios.display.ICalculator;

/**
 * Created by BIOS on 1/21/2017.
 */

public class CalculatorScreenCommunication {
    private static volatile CalculatorScreenCommunication calculatorScreenCommunication;
    private final ICalculator iCalculator;

    private CalculatorScreenCommunication(ICalculator iCalculator) {
        this.iCalculator = iCalculator;
    }

    public static CalculatorScreenCommunication getInstance() {
        return calculatorScreenCommunication;
    }

    public static void init(ICalculator iCalculator) {
        synchronized (CalculatorScreenCommunication.class) {
            calculatorScreenCommunication = new CalculatorScreenCommunication(iCalculator);
        }
    }

    @Override
    public String toString() {
        return iCalculator.getText();
    }

    public void setTextColor(String color) {
        iCalculator.setTextColor(color);
    }

    public void setTextSize(float size) {
        iCalculator.setTextSize(size);
    }

    public void addText(String value) {
        iCalculator.addText(value);
    }

    public void setText(String value) {
        iCalculator.setText(value);
    }

    public String getText() {
        return iCalculator.getText();
    }

    public void insertText(int cursor, String value) {
        iCalculator.insertText(cursor, value);
    }

    public int getSize() {
        return iCalculator.getText().length();
    }

    public boolean isEmpty() {
        return iCalculator.getText().isEmpty();
    }

    public int getCursorPosition() {
        return iCalculator.getCursorPosition();
    }

    public void setCursorPosition(int value) {
        iCalculator.setCursorPosition(value);
    }

    public char getCharAt(int i) {
        return iCalculator.getCharAt(i);
    }

    public void delete() {
        iCalculator.delete();
    }

    public void delete(int start, int end) {
        iCalculator.delete(start, end);
    }

    public void clear() {
        iCalculator.clear();
    }
}
