package ua.bios.display;

/**
 * Created by BIOS on 1/12/2017.
 */

public interface CalculatorInterface {
    String getText();

    void addText(String value);


    int getCursorPosition();

    char getChatAt(int i);

    CharSequence getSubSequence();

    void clear();

    void delete();
}
