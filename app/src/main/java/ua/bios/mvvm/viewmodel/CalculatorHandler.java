package ua.bios.mvvm.viewmodel;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.LinkedList;

import ua.bios.mvvm.model.Calculator;
import ua.bios.mvvm.model.CalculatorScreenCommunication;
import ua.bios.mvvm.model.ErrorMessages;
import ua.bios.mvvm.model.FunctionNames;
import ua.bios.mvvm.model.Operators;
import ua.bios.mvvm.model.ResultStorage;
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

    public void onClickGrandTotal(View v) { grandTotal(); }

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
        changeOperatorToOpposite();
    }

    public void onClickEquals(View v) {
        equals();
    }

    //============================SetValue==========================================================
    private void setValue(String value) {
        CalculatorScreenCommunication calculatorScreenCommunication = CalculatorScreenCommunication.getInstance();

        ValidationArguments validationArguments = new ValidationArguments();
        ifLandingZero(validationArguments, calculatorScreenCommunication, value);

        if (checkSyntaxErrorOnAlgebraicExpression(validationArguments, calculatorScreenCommunication, value)) {
            calculatorScreenCommunication.addText(value);
        }
    }

    private void ifLandingZero(ValidationArguments validationArguments, CalculatorScreenCommunication calculatorScreenCommunication, String nextValue) {
        if (!calculatorScreenCommunication.isEmpty()) {
            if (validationArguments.isLeadingZero(calculatorScreenCommunication.toString(), nextValue)) {
                calculatorScreenCommunication.clear();
            }
        }
    }

    @NonNull
    private boolean checkSyntaxErrorOnAlgebraicExpression(ValidationArguments validationArguments, CalculatorScreenCommunication calculatorScreenCommunication, String nextValue) {
        String value = getStringPartAtCursorPosition(calculatorScreenCommunication);
        return validationArguments.validate(value, nextValue);
    }

    @NonNull
    private String getStringPartAtCursorPosition(CalculatorScreenCommunication calculatorScreenCommunication) {
        int cursorPosition = calculatorScreenCommunication.getCursorPosition();
        String value = "";
        if (cursorPosition != 0) {
            for (int indexCursorPosition = cursorPosition - 1; indexCursorPosition >= 0; indexCursorPosition--) {
                char charAt = calculatorScreenCommunication.getChatAt(indexCursorPosition);
                value += String.valueOf(calculatorScreenCommunication.getChatAt(indexCursorPosition));
                if (charAt == Operators.DIVIDE.toString().charAt(0)
                        || charAt == Operators.MULTIPLY.toString().charAt(0)
                        || charAt == Operators.PLUS.toString().charAt(0)
                        || charAt == Operators.SUBTRACT.toString().charAt(0)
                        || charAt == '='
                        || charAt == '\n') {
                    break;
                }
            }
        }
        return value;
    }

    //==============================================================================================

    //============================GrandTotal========================================================
    private void grandTotal() {
        StringBuilder expression = getGTExpression();
        calculateGTResult(expression);
    }

    @NonNull
    private StringBuilder getGTExpression() {
        ResultStorage resultStorage = ResultStorage.getInstance();
        StringBuilder summaryResults = new StringBuilder("0");
        for (int index = 0; ; index++) {
            if (resultStorage.getSize() > 0) {
                if (resultStorage.getSize() - 1 > index) {
                    summaryResults.append(resultStorage.get(index)).append(Operators.PLUS.toString());
                } else {
                    summaryResults.append(resultStorage.get(index));
                    break;
                }
            } else {
                break;
            }
        }
        resultStorage.clear();
        return summaryResults;
    }

    private void calculateGTResult(StringBuilder summaryResults) {
        Calculator calculator = new Calculator();
        String result = calculator.calculate(summaryResults.toString());

        CalculatorScreenCommunication calculatorScreenCommunication = CalculatorScreenCommunication.getInstance();
        calculatorScreenCommunication.addText(FunctionNames.getGrandTotal().concat("=").concat(result).concat("\n"));
    }

    //==============================================================================================

    //============================Clear=============================================================
    private void clear() {
        clearResultStorage();
        clearResultAndReset();
    }

    private void clearResultAndReset() {
        CalculatorScreenCommunication calculatorScreenCommunication = CalculatorScreenCommunication.getInstance();
        calculatorScreenCommunication.clear();
        setValue("0");
    }

    private void clearResultStorage() {
        ResultStorage resultStorage = ResultStorage.getInstance();
        resultStorage.clear();
    }

    //==============================================================================================

    //============================Backspace=========================================================
    private void Backspace() {
        CalculatorScreenCommunication calculatorScreenCommunication = CalculatorScreenCommunication.getInstance();
        calculatorScreenCommunication.delete();
    }

    //==============================================================================================

    //============================OperatorToOpposite================================================
    private void changeOperatorToOpposite() {
        CalculatorScreenCommunication calculatorScreenCommunication = CalculatorScreenCommunication.getInstance();
        int cursorPosition = calculatorScreenCommunication.getCursorPosition();
        if (cursorPosition != 0) {
            for (int indexCursorPosition = cursorPosition - 1; indexCursorPosition >= 0; indexCursorPosition--) {
                char charAt = calculatorScreenCommunication.getChatAt(indexCursorPosition);

                if (charAt == Operators.DIVIDE.toString().charAt(0)) {
                    operatorReplacer(calculatorScreenCommunication, indexCursorPosition, indexCursorPosition + 1, Operators.MULTIPLY.toString());
                    break;
                } else if (charAt == Operators.MULTIPLY.toString().charAt(0)) {
                    operatorReplacer(calculatorScreenCommunication, indexCursorPosition, indexCursorPosition + 1, Operators.DIVIDE.toString());
                    break;
                } else if (charAt == Operators.SUBTRACT.toString().charAt(0)) {
                    operatorReplacer(calculatorScreenCommunication, indexCursorPosition, indexCursorPosition + 1, Operators.PLUS.toString());
                    break;
                } else if (charAt == Operators.PLUS.toString().charAt(0)) {
                    operatorReplacer(calculatorScreenCommunication, indexCursorPosition, indexCursorPosition + 1, Operators.SUBTRACT.toString());
                    break;
                }
            }
        }
    }

    private void operatorReplacer(CalculatorScreenCommunication calculatorScreenCommunication, int indexCursorPosition, int end, String value) {
        calculatorScreenCommunication.delete(indexCursorPosition, end);
        calculatorScreenCommunication.insertText(indexCursorPosition, value);
    }
    //==============================================================================================

    //============================Equals============================================================

    private void equals() {
        CalculatorScreenCommunication calculatorScreenCommunication = CalculatorScreenCommunication.getInstance();

        String expression = calculatorScreenCommunication.toString();
        expression = cleanerErrorMessagesAndFunctionNames(expression);
        calculatorScreenCommunication.clear();

        Calculator calculator = new Calculator();
        StringBuilder expressionWithResults = calculateAllExpressionGroup(expression, calculator);
        clearResultStorage();
        setResultsForGrandTotal(expressionWithResults.toString());
        calculatorScreenCommunication.addText(expressionWithResults.toString());
    }

    private StringBuilder calculateAllExpressionGroup(String expression, Calculator calculator) {
        LinkedList<String> expressionWithoutEquals = removeAllEqualFromExpression(expression);
        StringBuilder expressionWithResults = new StringBuilder();
        for (String expr : expressionWithoutEquals) {
            String result = String.valueOf(calculator.calculate(expr));
            expressionWithResults.append(expr).append("=").append(result).append("\n");
        }
        return expressionWithResults;
    }

    private void setResultsForGrandTotal(String expression) {
        ResultStorage resultStorage = ResultStorage.getInstance();
        LinkedList<String> results = ExpressionParser.getResultsList(expression);
        for (String result : results) {
            resultStorage.add(result);
        }
    }

    private LinkedList<String> removeAllEqualFromExpression(String expression) {
        LinkedList<String> groupedExpression = ExpressionParser.getExpressionAsGroupedList(expression);
        return ExpressionCleaner.removeAllEqualFromExpression(groupedExpression);
    }

    private String cleanerErrorMessagesAndFunctionNames(String expression) {
        expression = ExpressionCleaner.cleanerErrorMsg(expression, ErrorMessages.getError());
        expression = ExpressionCleaner.cleanerErrorMsg(expression, ErrorMessages.getErrorDivisionByZero());
        expression = ExpressionCleaner.cleanerErrorMsg(expression, ErrorMessages.getErrorNaN());
        expression = ExpressionCleaner.extractGTExpression(expression, FunctionNames.getGrandTotal());
        return expression;
    }
    //==============================================================================================
}
