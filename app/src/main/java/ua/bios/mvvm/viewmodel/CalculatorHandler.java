package ua.bios.mvvm.viewmodel;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.LinkedList;

import ua.bios.mvvm.model.Calculator;
import ua.bios.mvvm.model.CalculatorScreenCommunication;
import ua.bios.mvvm.model.Messages;
import ua.bios.mvvm.model.Operators;
import ua.bios.mvvm.model.ResultData;
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

    public void onClickGrandTotal(View v) {
        grandTotal();
    }

    public void onClickRate(View v) {
        setRate();
    }

    public void onClickTaxPlus(View v){
        taxPlus();
    }

    public void onClickTaxMinus(View v){
        taxMinus();
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
        String value = getNumberAtCursorPosition(calculatorScreenCommunication);
        String twoChar = getTwoCharAtCursorPosition(calculatorScreenCommunication);
        return validationArguments.validate(value, nextValue) && validationArguments.validate(twoChar, nextValue);
    }

    @NonNull
    private String getNumberAtCursorPosition(CalculatorScreenCommunication calculatorScreenCommunication) {
        int cursorPosition = calculatorScreenCommunication.getCursorPosition();
        String value = "";
        if (cursorPosition != 0) {
            int indexCursorPosition = cursorPosition - 1;
            for (; indexCursorPosition >= 0; indexCursorPosition--) {
                char charAt = calculatorScreenCommunication.getCharAt(indexCursorPosition);
                if (isBarrier(charAt)) break;
            }

            for (int index = indexCursorPosition + 1; index < calculatorScreenCommunication.getSize(); index++) {
                char charAt = calculatorScreenCommunication.getCharAt(index);
                if (isBarrier(charAt)) break;
                value += String.valueOf(charAt);
            }
        }
        return value;
    }

    private String getTwoCharAtCursorPosition(CalculatorScreenCommunication calculatorScreenCommunication){
        int cursorPosition = calculatorScreenCommunication.getCursorPosition();
        String value="";
        if (cursorPosition != 0) {
            if (cursorPosition == calculatorScreenCommunication.getSize()) {
                value = String.valueOf(calculatorScreenCommunication.getCharAt(cursorPosition - 1));
            } else {
                char charA = calculatorScreenCommunication.getCharAt(cursorPosition - 1);
                char charB = calculatorScreenCommunication.getCharAt(cursorPosition);
                value = String.valueOf(charA + "" + charB);
            }
        }
        return value;
    }

    private boolean isBarrier(char charAt) {
        if (charAt == Operators.DIVIDE.toString().charAt(0)
                || charAt == Operators.MULTIPLY.toString().charAt(0)
                || charAt == Operators.PLUS.toString().charAt(0)
                || charAt == Operators.SUBTRACT.toString().charAt(0)
                || charAt == '='
                || charAt == '\n') {
            return true;
        }
        return false;
    }

    //==============================================================================================

    //============================TAX===============================================================
    //RATE
    private void setRate() {
        CalculatorScreenCommunication calculatorScreenCommunication = CalculatorScreenCommunication.getInstance();
        TaxRateViewModel taxRateViewModel = TaxRateViewModel.getInstance();
        taxRateViewModel.setValue(getNumberAtCursorPosition(calculatorScreenCommunication));
    }

    //Tax+
    private void taxPlus(){

    }

    //Tax-
    private void taxMinus(){

    }
    //==============================================================================================

    //============================GrandTotal========================================================
    private void grandTotal() {
        StringBuilder expression = getGTExpression();
        setCursorToEndOfLine();
        calculateGTResult(expression);
    }

    @NonNull
    private StringBuilder getGTExpression() {
        ResultData resultData = ResultData.getInstance();
        StringBuilder summaryResults = new StringBuilder("0");
        for (int index = 0; ; index++) {
            if (resultData.getSize() > 0) {
                if (resultData.getSize() - 1 > index) {
                    summaryResults.append(resultData.get(index)).append(Operators.PLUS.toString());
                } else {
                    summaryResults.append(resultData.get(index));
                    break;
                }
            } else {
                break;
            }
        }
        resultData.clear();
        return summaryResults;
    }

    private void calculateGTResult(StringBuilder summaryResults) {
        Calculator calculator = new Calculator();
        String result = calculator.calculate(summaryResults.toString());

        CalculatorScreenCommunication calculatorScreenCommunication = CalculatorScreenCommunication.getInstance();
        calculatorScreenCommunication.addText("GT=".concat(result).concat("\n"));
    }

    private void setCursorToEndOfLine() {
        CalculatorScreenCommunication calculatorScreenCommunication = CalculatorScreenCommunication.getInstance();
        calculatorScreenCommunication.setCursorPosition(calculatorScreenCommunication.getSize());
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
        ResultData resultData = ResultData.getInstance();
        resultData.clear();
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
                char charAt = calculatorScreenCommunication.getCharAt(indexCursorPosition);

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
        ResultData resultData = ResultData.getInstance();
        LinkedList<String> results = ExpressionParser.getResultsList(expression);
        for (String result : results) {
            resultData.add(result);
        }
    }

    private LinkedList<String> removeAllEqualFromExpression(String expression) {
        LinkedList<String> groupedExpression = ExpressionParser.getExpressionAsGroupedList(expression);
        return ExpressionCleaner.removeAllEqualFromExpression(groupedExpression);
    }

    private String cleanerErrorMessagesAndFunctionNames(String expression) {
        expression = ExpressionCleaner.cleanerErrorMsg(expression, Messages.getError());
        expression = ExpressionCleaner.cleanerErrorMsg(expression, Messages.getErrorDivisionByZero());
        expression = ExpressionCleaner.cleanerErrorMsg(expression, Messages.getErrorNaN());
        expression = ExpressionCleaner.extractGTExpression(expression, "GT");
        return expression;
    }
    //==============================================================================================
}
