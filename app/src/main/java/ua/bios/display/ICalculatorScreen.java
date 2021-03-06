package ua.bios.display;

/**
 * Created by BIOS on 1/12/2017.
 */

public interface ICalculatorScreen {

    void setScreenAlwaysOn(boolean flag);

    void setTextColor(String color);

    void setTextLines(int count);

    void setTextSize(float size);

    String getText();

    void addText(String value);

    void setText(String value);

    void insertText(int cursor, String value);

    int getCursorPosition();

    void setCursorPosition(int value);

    char getCharAt(int i);

    void clear();

    void delete();

    void delete(int start, int end);
}
