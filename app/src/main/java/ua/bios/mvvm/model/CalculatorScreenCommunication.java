package ua.bios.mvvm.model;

import ua.bios.display.CalculatorInterface;

/**
 * Created by BIOS on 1/21/2017.
 */

public class CalculatorScreenCommunication {
    private static volatile CalculatorScreenCommunication calculatorScreenCommunication;
    private final CalculatorInterface calculatorInterface;

    private CalculatorScreenCommunication(CalculatorInterface calculatorInterface) {
        this.calculatorInterface = calculatorInterface;
    }

    public static CalculatorScreenCommunication getInstance() {
        return calculatorScreenCommunication;
    }

    public static void init(CalculatorInterface calculatorInterface) {
        synchronized (CalculatorScreenCommunication.class) {
            calculatorScreenCommunication = new CalculatorScreenCommunication(calculatorInterface);
        }
    }

    @Override
    public String toString() {
        return calculatorInterface.getText();
    }

    public void addText(String value) {
        calculatorInterface.addText(value);
    }

    public void insertText(int cursor, String value) {
        calculatorInterface.insertText(cursor, value);
    }

    public int getSize() {
        return calculatorInterface.getText().length();
    }

    public boolean isEmpty() {
        return calculatorInterface.getText().isEmpty();
    }

    public int getCursorPosition() {
        return calculatorInterface.getCursorPosition();
    }

    public void setCursorPosition(int value) {
        calculatorInterface.setCursorPosition(value);
    }

    public char getCharAt(int i) {
        return calculatorInterface.getCharAt(i);
    }

    public void delete() {
        calculatorInterface.delete();
    }

    public void delete(int start, int end) {
        calculatorInterface.delete(start, end);
    }

    public void clear() {
        calculatorInterface.clear();
    }
}
