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

    public void onClickGrandTotal(View v){
         calculateGTResult(getGTExpression());
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


    @NonNull
    private StringBuilder getGTExpression() {
        ResultStorage resultStorage = ResultStorage.getInstance();
        StringBuilder summaryResults = new StringBuilder("0");
        for(int index = 0; ; index++){
            if(resultStorage.getSize() - 1 > index) {
                summaryResults.append(resultStorage.get(index)).append(Operators.PLUS.toString());
            } else {
                summaryResults.append(resultStorage.get(index));
                break;
            }
        }
        return summaryResults;
    }

    private void calculateGTResult(StringBuilder summaryResults) {
        Calculator calculator = new Calculator();
        String result = calculator.calculate(summaryResults.toString());

        CalculatorScreenCommunication calculatorScreenCommunication = CalculatorScreenCommunication.getInstance();
        calculatorScreenCommunication.addText(FunctionNames.getGrandTotal().toString().concat("=").concat(result).concat("\n"));
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
        String value = getStringPartAtCursorPosition(calculatorScreenCommunication);
        return validationArguments.validate(value, nextValue);
    }

    @NonNull
    private String getStringPartAtCursorPosition(CalculatorScreenCommunication calculatorScreenCommunication) {
        int cursorPosition = calculatorScreenCommunication.getCursorPosition();
        String value = "";
        if (cursorPosition != 0) {
            for(int indexCursorPosition = cursorPosition - 1; indexCursorPosition >= 0; indexCursorPosition--) {
                char charAt = calculatorScreenCommunication.getChatAt(indexCursorPosition);
                value += String.valueOf(calculatorScreenCommunication.getChatAt(indexCursorPosition));
                if(charAt == Operators.DIVIDE.toString().charAt(0)
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

        expression = cleanerErrorMessagesAndFunctionNames(expression);

        Calculator calculator = new Calculator();
        calculatorScreenCommunication.clear();

        calculateAllExpressionGroup(calculatorScreenCommunication, expression, calculator);
    }

    private void calculateAllExpressionGroup(CalculatorScreenCommunication calculatorScreenCommunication, String expression, Calculator calculator) {
        ResultStorage resultStorage = ResultStorage.getInstance();
        resultStorage.clear();

        LinkedList<String> expressionWithoutEquals = removeAllEqualFromExpression(expression);
        for (String expr : expressionWithoutEquals) {
            String result = String.valueOf(calculator.calculate(expr));
            calculatorScreenCommunication.addText(expr.concat("=").concat(result).concat("\n"));

            setResultsForGrandTotal(resultStorage, result);
        }
    }

    private void setResultsForGrandTotal(ResultStorage resultStorage, String result) {
        resultStorage.add(result);
        checkSizeOfResultStorageAndActivateGTScreen(resultStorage);
    }

    private void checkSizeOfResultStorageAndActivateGTScreen(ResultStorage resultStorage) {
        BindableGrandTotalScreen bindableGrandTotalScreen = BindableGrandTotalScreen.getInstance();
        if(resultStorage.getSize() > 0){
            bindableGrandTotalScreen.setActivate(true);
        } else {
            bindableGrandTotalScreen.setActivate(false);
        }
    }

    private LinkedList<String> removeAllEqualFromExpression(String expression) {
        LinkedList<String> groupedExpression = ExpressionParser.getExpressionAsGroupedList(expression);
        return ExpressionCleaner.removeAllEqualFromExpression(groupedExpression);
    }

    private String cleanerErrorMessagesAndFunctionNames(String expression) {
        expression = ExpressionCleaner.cleanerErrorMsg(expression, ErrorMessages.getError());
        expression = ExpressionCleaner.cleanerErrorMsg(expression, ErrorMessages.getErrorDivisionByZero());
        expression = ExpressionCleaner.extractGTExpression(expression, FunctionNames.getGrandTotal());
        return expression;
    }
}
