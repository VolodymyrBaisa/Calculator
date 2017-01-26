package ua.bios.mvvm.viewmodel;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.LinkedList;

import ua.bios.mvvm.model.Calculator;
import ua.bios.mvvm.model.CalculatorScreenCommunication;
import ua.bios.mvvm.model.ErrorMessages;
import ua.bios.mvvm.model.Operators;
import ua.bios.utils.ExpressionCleaner;
import ua.bios.utils.ExpressionParser;

/**
 * Created by BIOS on 1/10/2017.
 */

public class CalculatorHandler {

    public void onClickButton(View v) {
        if (v instanceof Button) {
            setValue(((Button) v).getText().toString());
        } else if (v instanceof ImageButton) {
            setValue(v.getContentDescription().toString());
        }
    }

    public void onClickClear(View v) {
        clear();
    }

    public void onClickBackspace(View v) {
        Backspace();
    }

    public void onClickPercentage(View v) {
        setValue("%");
    }

    public void onClickSquareRoot(View v) {
        setValue("√");
    }

    public void onClickOpposite(View v) {
        opposite();
    }

    public void onClickEquals(View v) {
        equals();
    }

    private void clear() {
        CalculatorScreenCommunication calculatorScreenCommunication = CalculatorScreenCommunication.getInstance();
        calculatorScreenCommunication.clear();
        setValue("0");
    }

    private void Backspace() {
        CalculatorScreenCommunication calculatorScreenCommunication = CalculatorScreenCommunication.getInstance();
        calculatorScreenCommunication.delete();
    }

    private void opposite() {
        CalculatorScreenCommunication calculatorScreenCommunication = CalculatorScreenCommunication.getInstance();
        int cursorPosition = calculatorScreenCommunication.getCursorPosition();
        if (cursorPosition != 0) {
            for (int indexCursorPosition = cursorPosition - 1; indexCursorPosition >= 0; indexCursorPosition--) {
                char charAt = calculatorScreenCommunication.getChatAt(indexCursorPosition);

                if (charAt == Operators.DIVIDE.toString().charAt(0)) {
                    oppositeOperators(calculatorScreenCommunication, indexCursorPosition, indexCursorPosition + 1, Operators.MULTIPLY.toString());
                    break;
                } else if (charAt == Operators.MULTIPLY.toString().charAt(0)) {
                    oppositeOperators(calculatorScreenCommunication, indexCursorPosition, indexCursorPosition + 1, Operators.DIVIDE.toString());
                    break;
                } else if (charAt == Operators.SUBTRACT.toString().charAt(0)) {
                    oppositeOperators(calculatorScreenCommunication, indexCursorPosition, indexCursorPosition + 1, Operators.PLUS.toString());
                    break;
                } else if (charAt == Operators.PLUS.toString().charAt(0)) {
                    oppositeOperators(calculatorScreenCommunication, indexCursorPosition, indexCursorPosition + 1, Operators.SUBTRACT.toString());
                    break;
                }
            }
        }
    }

    private void oppositeOperators(CalculatorScreenCommunication calculatorScreenCommunication, int indexCursorPosition, int end, String value) {
        calculatorScreenCommunication.delete(indexCursorPosition, end);
        calculatorScreenCommunication.insertText(indexCursorPosition, value);
    }

    private void setValue(String value) {
        CalculatorScreenCommunication calculatorScreenCommunication = CalculatorScreenCommunication.getInstance();

        ValidationArguments validationArguments = new ValidationArguments();
        ifLandingZero(validationArguments, calculatorScreenCommunication, value);

        if (checkSyntaxErrorOnAlgebraicExpression(validationArguments, calculatorScreenCommunication, value)) {
            calculatorScreenCommunication.addText(value);
        }
    }

    @NonNull
    private boolean checkSyntaxErrorOnAlgebraicExpression(ValidationArguments validationArguments, CalculatorScreenCommunication calculatorScreenCommunication, String nextValue) {
        return validationArguments.validate(ExpressionParser.getExpressionAsGroupedList(calculatorScreenCommunication.toString()), nextValue);
    }

    private void ifLandingZero(ValidationArguments validationArguments, CalculatorScreenCommunication calculatorScreenCommunication, String nextValue) {
        if (!calculatorScreenCommunication.isEmpty()) {
            if (validationArguments.isLeadingZero(calculatorScreenCommunication.toString(), nextValue)) {
                calculatorScreenCommunication.clear();
            }
        }
    }

    private void equals() {
        CalculatorScreenCommunication calculatorScreenCommunication = CalculatorScreenCommunication.getInstance();
        String expression = calculatorScreenCommunication.toString();

        expression = cleanerErrorMessages(expression);

        Calculator calculator = new Calculator();
        calculatorScreenCommunication.clear();

        calculateAllExpressionGroup(calculatorScreenCommunication, expression, calculator);
    }

    private void calculateAllExpressionGroup(CalculatorScreenCommunication calculatorScreenCommunication, String expression, Calculator calculator) {
        LinkedList<String> expressionWithoutEquals = removeAllEqualFromExpression(expression);
        for (String expr : expressionWithoutEquals) {
            String result = String.valueOf(calculator.calculate(expr));
            calculatorScreenCommunication.addText(expr.concat("=").concat(result).concat("\n"));
        }
    }

    private LinkedList<String> removeAllEqualFromExpression(String expression) {
        LinkedList<String> groupedExpression = ExpressionParser.getExpressionAsGroupedList(expression);
        return ExpressionCleaner.removeAllEqualFromExpression(groupedExpression);
    }

    private String cleanerErrorMessages(String expression) {
        expression = ExpressionCleaner.cleanerErrorMsg(expression, ErrorMessages.getError());
        expression = ExpressionCleaner.cleanerErrorMsg(expression, ErrorMessages.getErrorDivisionByZero());
        return expression;
    }
}
