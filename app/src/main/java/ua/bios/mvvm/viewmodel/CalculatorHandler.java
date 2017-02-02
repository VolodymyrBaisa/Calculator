package ua.bios.mvvm.viewmodel;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.LinkedList;

import ua.bios.mvvm.model.Calculator;
import ua.bios.mvvm.model.CalculatorScreenCommunication;
import ua.bios.mvvm.model.GrandTotalData;
import ua.bios.mvvm.model.MemoryData;
import ua.bios.mvvm.model.Operators;
import ua.bios.mvvm.model.TaxRateData;
import ua.bios.utils.ExpressionCleaner;
import ua.bios.utils.ExpressionParser;
import ua.bios.utils.ExpressionTest;

/**
 * Created by BIOS on 1/10/2017.
 */

public class CalculatorHandler {

    public void onClickButton(View v) {
        if (v instanceof Button) {
            addAndCheckValue(((Button) v).getText().toString());
        } else if (v instanceof ImageButton) {
            addAndCheckValue(v.getContentDescription().toString());
        }
    }

    public void onClickGrandTotal(View v) {
        grandTotal();
    }

    public void onClickRate(View v) {
        setRate();
    }

    public void onClickTaxPlus(View v) {
        taxPlus();
    }

    public void onClickTaxMinus(View v) {
        taxMinus();
    }

    public void onClickClear(View v) {
        clear();
    }

    public void onClickBackspace(View v) {
        Backspace();
    }

    public void onClickPercentage(View v) {
        addAndCheckValue("%");
    }

    public void onClickSquareRoot(View v) {
        addAndCheckValue("√");
    }

    public void onClickMemoryClear(View v) {
        memoryClear();
    }

    public void onClickMemoryRecall(View v) {
        memoryRecall();
    }

    public void onClickMemorySubtract(View v) {
        memorySubtract();
    }

    public void onClickMemoryAdd(View v) {
        memoryAdd();
    }

    public void onClickOpposite(View v) {
        changeOperatorToOpposite();
    }

    public void onClickEquals(View v) {
        equals();
    }

    //============================Set Validated Value===============================================
    private void addAndCheckValue(String value) {
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

    //==============================================================================================

    //============================TAX===============================================================
    private void setRate() {
        CalculatorScreenCommunication calculatorScreenCommunication = CalculatorScreenCommunication.getInstance();

        String taxRate = getNumberAtCursorPosition(calculatorScreenCommunication);
        taxRate = taxRate != "" ? taxRate : "0";

        TaxRateViewModel taxRateViewModel = TaxRateViewModel.getInstance();
        taxRateViewModel.setValue(taxRate);

        TaxRateData taxRateData = TaxRateData.getInstance();
        taxRateData.setTax(taxRate);
    }

    private void taxPlus() {
        String taxPercentage = getTaxRate();

        CalculatorScreenCommunication calculatorScreenCommunication = CalculatorScreenCommunication.getInstance();
        String value = getResultAtCursorPosition(calculatorScreenCommunication);
        Calculator calculator = new Calculator();
        String total = calculator.calculate(value.concat("+").concat(taxPercentage).concat("%"));

        String tax = calculator.calculate(total.concat("-").concat(value));

        printTax(calculatorScreenCommunication, total, tax);
    }

    private void taxMinus() {
        String taxPercentage = getTaxRate();

        CalculatorScreenCommunication calculatorScreenCommunication = CalculatorScreenCommunication.getInstance();
        String value = getResultAtCursorPosition(calculatorScreenCommunication);
        Calculator calculator = new Calculator();
        String total = calculator.calculate(value.concat("/").concat("(1+").concat(taxPercentage).concat("/100)"));

        String tax = calculator.calculate(value.concat("-").concat(total));

        printTax(calculatorScreenCommunication, total, tax);
    }

    private String getTaxRate() {
        equals();
        TaxRateData taxRateData = TaxRateData.getInstance();
        return taxRateData.getTax();
    }

    private void printTax(CalculatorScreenCommunication calculatorScreenCommunication, String total, String tax) {
        calculatorScreenCommunication.addText("Tax=".concat(tax).concat("\n"));
        calculatorScreenCommunication.addText("Total=".concat(total).concat("\n"));
    }
    //==============================================================================================

    //============================Grand Total=======================================================
    private void grandTotal() {
        StringBuilder expression = getGTExpression();
        setCursorToEndOfLine();
        calculateGTResult(expression);
    }

    @NonNull
    private StringBuilder getGTExpression() {
        GrandTotalData grandTotalData = GrandTotalData.getInstance();
        StringBuilder summaryResults = new StringBuilder("0");
        for (int index = 0; ; index++) {
            if (grandTotalData.getSize() > 0) {
                if (grandTotalData.getSize() - 1 > index) {
                    summaryResults.append(grandTotalData.get(index)).append(Operators.PLUS.toString());
                } else {
                    summaryResults.append(grandTotalData.get(index));
                    break;
                }
            } else {
                break;
            }
        }
        grandTotalData.clear();
        return summaryResults;
    }

    private void calculateGTResult(StringBuilder summaryResults) {
        Calculator calculator = new Calculator();
        String result = calculator.calculate(summaryResults.toString());

        CalculatorScreenCommunication calculatorScreenCommunication = CalculatorScreenCommunication.getInstance();
        calculatorScreenCommunication.addText("GT=".concat(result).concat("\n"));
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
        addAndCheckValue("0");
    }

    private void clearResultStorage() {
        GrandTotalData grandTotalData = GrandTotalData.getInstance();
        grandTotalData.clear();
    }

    //==============================================================================================

    //============================Backspace=========================================================
    private void Backspace() {
        CalculatorScreenCommunication calculatorScreenCommunication = CalculatorScreenCommunication.getInstance();
        calculatorScreenCommunication.delete();
    }

    //==============================================================================================

    //============================Memory============================================================
    private void memoryClear() {
        MemoryData memoryData = MemoryData.getInstance();
        memoryData.clear();
    }

    private void memoryRecall() {

    }

    private void memorySubtract() {

    }

    private void memoryAdd() {

    }

    //==============================================================================================

    //============================Operator To Opposite==============================================
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
        calculatorScreenCommunication.clear();

        expression = clearMessageAfterEqual(expression);

        Calculator calculator = new Calculator();
        StringBuilder expressionWithResults = calculateAllExpressionGroup(expression, calculator);
        clearResultStorage();
        setResultsForGrandTotal(expressionWithResults.toString());
        calculatorScreenCommunication.addText(expressionWithResults.toString());
    }

    private StringBuilder calculateAllExpressionGroup(String expression, Calculator calculator) {
        LinkedList<String> cleanedExpression = removeResultFromExpression(expression);
        StringBuilder processedExpression = new StringBuilder();
        for (String expr : cleanedExpression) {
            if (ExpressionTest.isExpression(expr)) {
                String result = String.valueOf(calculator.calculate(expr));
                processedExpression.append(expr).append("=").append(result).append("\n");
            } else {
                processedExpression.append(expr).append("\n");
            }
        }
        return processedExpression;
    }

    private void setResultsForGrandTotal(String expression) {
        GrandTotalData grandTotalData = GrandTotalData.getInstance();
        LinkedList<String> results = ExpressionParser.getResultsList(expression);
        for (String result : results) {
            grandTotalData.add(result);
        }
    }

    private LinkedList<String> removeResultFromExpression(String expression) {
        LinkedList<String> groupedExpression = ExpressionParser.getExpressionAsGroupedList(expression);
        return ExpressionCleaner.removeResultFromExpression(groupedExpression);
    }

    private String clearMessageAfterEqual(String expression) {
        return ExpressionCleaner.clearMessageAfterEqual(expression);
    }
    //==============================================================================================

    //============================Auxiliary Working Methods With Calculator Screen==================
    private String getResultAtCursorPosition(CalculatorScreenCommunication calculatorScreenCommunication) {
        int cursorPosition = calculatorScreenCommunication.getCursorPosition();
        String value = "";
        int indexCursorPosition = cursorPosition - 2;

        for (; indexCursorPosition > 0; indexCursorPosition--) {
            char charAt = calculatorScreenCommunication.getCharAt(indexCursorPosition);
            if (charAt == '\n') break;
        }

        boolean wasEqual = false;
        for (indexCursorPosition += 1; indexCursorPosition < calculatorScreenCommunication.getSize(); indexCursorPosition++) {
            char charAt = calculatorScreenCommunication.getCharAt(indexCursorPosition);
            if (wasEqual) {
                value += String.valueOf(charAt);
                if (charAt == '\n') break;
            }

            if (charAt == '=') {
                wasEqual = true;
            }

        }

        return value;
    }

    private void setCursorToEndOfLine() {
        CalculatorScreenCommunication calculatorScreenCommunication = CalculatorScreenCommunication.getInstance();
        calculatorScreenCommunication.setCursorPosition(calculatorScreenCommunication.getSize());
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

    private String getTwoCharAtCursorPosition(CalculatorScreenCommunication calculatorScreenCommunication) {
        int cursorPosition = calculatorScreenCommunication.getCursorPosition();
        String value = "";
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
}
