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

    public static LinkedList<String> find(String exp, String regex) {
        LinkedList<String> list = new LinkedList<>();

        Matcher matcher = Pattern.compile(regex).matcher(exp);
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }

    public static LinkedList<String> groupSplitter(String exp, String regex) {
        LinkedList<String> list = new LinkedList<>();

        Matcher matcher = Pattern.compile(regex).matcher(exp);
        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                list.add(matcher.group(i));
            }
        }
        return list;
    }
}
