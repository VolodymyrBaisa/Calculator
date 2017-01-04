package ua.bios.mvvm.viewmodel;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by BIOS on 1/2/2017.
 */

public class Parser {
    private static final String regex = "(\\d+\\.\\d+)|(^-\\d+\\.\\d+)|(\\d+)|([+-÷×///^])|([/(/)])";

    private Parser() {
    }

    public static LinkedList<String> parse(String exp) {
        LinkedList<String> list = new LinkedList<>();

        Matcher matcher = Pattern.compile(regex).matcher(exp);
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }
}
