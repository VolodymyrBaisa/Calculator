package ua.bios.mvvm.viewmodel;

import java.util.Arrays;
import java.util.LinkedList;

import ua.bios.mvvm.model.ErrorMessages;
import ua.bios.utils.Parser;

/**
 * Created by BIOS on 1/14/2017.
 */

public class ExpressionParser {
    private static volatile ExpressionParser expressionParser;

    private ExpressionParser() {
    }

    public static ExpressionParser getInstance() {
        if (expressionParser == null) {
            synchronized (ExpressionParser.class) {
                return expressionParser = new ExpressionParser();
            }
        } else {
            return expressionParser;
        }
    }

    public LinkedList<String> getPart(String value) {
        String regex = "(^-[\\d.]+)|([\\d.%√]+)|([()+-÷×/^])";
        return Parser.find(value, regex);
    }

    public LinkedList<String> getExpressionPart(String exp) {
        LinkedList<String> expressionList = new LinkedList<>();
        LinkedList<String> splitList = Parser.find(exp, "[\\d.+-÷×()√%=]+");

        for (String value : splitList) {
            if (!value.matches("^.*=[-\\d.]+$") && !value.matches("^[-\\d.]+$")) {
                expressionList.addAll(Arrays.asList(value.split("=")));
            } else {
                expressionList.add(value.replaceAll("=[-\\d.]+$", ""));
            }
        }

        return expressionList;
    }

    public String clearErrorMsg(String exp) {
        return exp.replaceAll(ErrorMessages.getError(), "");
    }
}
