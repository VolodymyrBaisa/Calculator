package ua.bios.mvvm.model;

import ua.bios.display.CalculatorInterface;

/**
 * Created by BIOS on 1/21/2017.
 */

public class CalculatorScreenCommunication {
    private static volatile CalculatorScreenCommunication calculatorScreenCommunication;
    private CalculatorInterface calculatorInterface;

    private CalculatorScreenCommunication(CalculatorInterface calculatorInterface) {
        this.calculatorInterface = calculatorInterface;
    }

    public static CalculatorScreenCommunication getInstance() {
        return calculatorScreenCommunication;
    }

    public static void init(CalculatorInterface calculatorInterface) {
        if (calculatorScreenCommunication == null) {
            synchronized (CalculatorScreenCommunication.class) {
                calculatorScreenCommunication = new CalculatorScreenCommunication(calculatorInterface);
            }
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

    public boolean isContains(String value) {
        return calculatorInterface.getText().contains(value);
    }

    public int getCursorPosition() {
        return calculatorInterface.getCursorPosition();
    }

    public char getChatAt(int i) {
        return calculatorInterface.getChatAt(i);
    }

    public CharSequence getSubSequence() {
        return calculatorInterface.getSubSequence();
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
