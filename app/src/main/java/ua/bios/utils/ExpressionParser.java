package ua.bios.utils;

import java.util.LinkedList;

/**
 * Created by BIOS on 1/23/2017.
 */

public class ExpressionParser {
    private ExpressionParser(){}

    public static LinkedList<String> getExpressionAsGroupedList(String expression) {
        return Parser.findElement(expression, "[\\d.+-÷×()√%=]+");
    }

    public static LinkedList<String> getResultsList(String expression) {
        return Parser.findGroupElement(expression, "=([-\\d.]+)");
    }
}
