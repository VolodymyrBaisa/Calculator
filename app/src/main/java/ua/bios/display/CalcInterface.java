package ua.bios.display;

/**
 * Created by BIOS on 1/12/2017.
 */

public interface CalcInterface {
    String getText();

    void setText(String value);

    void addText(String value);

    void clear();

    void delete();
}
