package ua.bios.utils;

import java.util.LinkedList;

/**
 * Created by BIOS on 1/21/2017.
 */

public class ArrayUtils {
    public static String arrayToString(LinkedList<String> arrays) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String array : arrays) {
            stringBuilder.append(array);
        }
        return stringBuilder.toString();
    }
}
