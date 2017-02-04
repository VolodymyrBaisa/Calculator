package ua.bios.calculatormethods;

/**
 * Created by BIOS on 1/31/2017.
 */

public class ExpressionTest {
    private ExpressionTest(){}

    public static boolean isExpression(String value){
        return !value.matches("[a-zA-Z]+|[a-zA-Z]+=|[a-zA-Z]+=[-\\d.\\D]+");
    }
}
