package ua.bios.calculatormethods;

/**
 * Created by BIOS on 1/31/2017.
 */

// Maybe ExpressionEvaluator or ExpressionValidator
public class ExpressionTest {
    private ExpressionTest(){} // For static do not need constructor

    public static boolean isExpression(String value){
        return !value.matches("[a-zA-Z]+|[a-zA-Z]+=|[a-zA-Z]+=[-\\d.\\D]+");
    }
}
