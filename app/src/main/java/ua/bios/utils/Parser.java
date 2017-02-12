package ua.bios.utils;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by BIOS on 12/11/2016.
 */

public class Parser {


    private Parser() {
    }

    // may be findElements()
    // rename parameter exp
    public static LinkedList<String> findElement(String exp, String regex) {
        LinkedList<String> elementList = new LinkedList<>();

        Matcher matcher = Pattern.compile(regex).matcher(exp);
        while (matcher.find()) {
            elementList.add(matcher.group());
        }
        return elementList;
    }

    // rename parameter exp
    public static LinkedList<String> findGroupElement(String exp, String regex) {
        LinkedList<String> elementList = new LinkedList<>();

        Matcher matcher = Pattern.compile(regex).matcher(exp);
        while (matcher.find()) {
            for (int index = 1; index <= matcher.groupCount(); index++) {
                elementList.add(matcher.group(index));
            }
        }
        return elementList;
    }
}
