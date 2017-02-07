package ua.bios.mvvm.model;

import ua.bios.display.ICalculatorScreen;

/**
 * Created by BIOS on 1/21/2017.
 */

public class CalculatorScreenCommunication {
    private static volatile CalculatorScreenCommunication calculatorScreenCommunication;
    private final ICalculatorScreen iCalculatorScreen;

    private CalculatorScreenCommunication(ICalculatorScreen iCalculatorScreen) {
        this.iCalculatorScreen = iCalculatorScreen;
    }

    public static CalculatorScreenCommunication getInstance() {
        return calculatorScreenCommunication;
    }

    public static void init(ICalculatorScreen iCalculatorScreen) {
        synchronized (CalculatorScreenCommunication.class) {
            calculatorScreenCommunication = new CalculatorScreenCommunication(iCalculatorScreen);
        }
    }

    @Override
    public String toString() {
        return iCalculatorScreen.getText();
    }

    public void setScreenAlwaysOn(boolean flag) {
        iCalculatorScreen.setScreenAlwaysOn(flag);
    }

    public void setTextColor(String color) {
        iCalculatorScreen.setTextColor(color);
    }

    public void setTextLines(int count){
        iCalculatorScreen.setTextLines(count);
    }

    public void setTextSize(float size) {
        iCalculatorScreen.setTextSize(size);
    }

    public void addText(String value) {
        iCalculatorScreen.addText(value);
    }

    public void setText(String value) {
        iCalculatorScreen.setText(value);
    }

    public String getText() {
        return iCalculatorScreen.getText();
    }

    public void insertText(int cursor, String value) {
        iCalculatorScreen.insertText(cursor, value);
    }

    public int getSize() {
        return iCalculatorScreen.getText().length();
    }

    public boolean isEmpty() {
        return iCalculatorScreen.getText().isEmpty();
    }

    public int getCursorPosition() {
        return iCalculatorScreen.getCursorPosition();
    }

    public void setCursorPosition(int value) {
        iCalculatorScreen.setCursorPosition(value);
    }

    public char getCharAt(int i) {
        return iCalculatorScreen.getCharAt(i);
    }

    public void delete() {
        iCalculatorScreen.delete();
    }

    public void delete(int start, int end) {
        iCalculatorScreen.delete(start, end);
    }

    public void clear() {
        iCalculatorScreen.clear();
    }
}
