package ua.bios.mvvm.viewmodel;

import java.util.LinkedList;

/**
 * Created by BIOS on 1/11/2017.
 */

public class Formatter {
    private static volatile Formatter formatter;

    private Formatter() {
    }

    public static Formatter getInstance() {
        if (formatter == null) {
            synchronized (Formatter.class) {
                return formatter = new Formatter();
            }
        } else {
            return formatter;
        }
    }

    public void stringFormat(LinkedList<String> array) {
        for (int i = array.size(); i == 0; i--) {
            array.set(i, array.get(i).replaceAll("^0+", ""));
        }
    }
}
