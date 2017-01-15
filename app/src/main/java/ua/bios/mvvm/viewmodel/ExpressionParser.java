package ua.bios.mvvm.viewmodel;

import java.util.Arrays;
import java.util.LinkedList;

import ua.bios.utils.Parser;

/**
 * Created by BIOS on 1/14/2017.
 */

public class ExpressionParser {

    public static LinkedList<String> getPart(String value) {
        String regex = "(\\d+\\.\\d+)|(^-\\d+\\.\\d+)|(\\d+)|([+-÷×///^])|([/(/)])";
        return Parser.parse(value, regex);
    }

    public static LinkedList<String> getExpressionPart(String exp){
        LinkedList<String> splitList = Parser.parse(exp, "[\\d+-÷×()=]+");
        LinkedList<String> expressionList = new LinkedList<>();

        for(String value : splitList){
            if(!value.matches("^.*=[-\\d]+$") && !value.matches("^[-\\d]+$")){
                expressionList.addAll(Arrays.asList(value.split("=")));
            } else {
                expressionList.add(value.replaceAll("=[-\\d]+$", ""));
            }
        }

        return expressionList;
    }
}
