package ua.bios.mvvm.viewmodel;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Arrays;
import java.util.LinkedList;

import ua.bios.display.CalcInterface;
import ua.bios.mvvm.model.Calculator;
import ua.bios.mvvm.model.ErrorMessages;
import ua.bios.utils.ArrayUtils;

/**
 * Created by BIOS on 1/10/2017.
 */

public class CalculatorImpl {
    private CalcInterface calcInterface;

    public CalculatorImpl(CalcInterface calculator) {
        calcInterface = calculator;
    }

    public void onClickButton(View v) {
        if (v instanceof Button) {
            setValue(((Button) v).getText().toString());
        } else if (v instanceof ImageButton) {
            setValue(v.getContentDescription().toString());
        }
    }

    public void onClickClear(View v) {
        clear();
        setValue("0");
    }

    public void onClickBackspace(View v) {
        delete();
    }

    public void onClickPercentage(View v){
        setValue("%");
    }

    public void onClickSquareRoot(View v){
        setValue("√");
    }

    public void onClickSquareOpposite(View v){

    }

    public void onClickEquals(View v) {
        equals();
    }

    private void setValue(String value) {
        ExpressionParser expressionParser = ExpressionParser.getInstance();
        expressionParser.clearErrorMsg(ErrorMessages.getError());
        String expression = getText();
        LinkedList<String> expList = expressionParser.getPart(expression);
        ValidationArguments validationArguments = new ValidationArguments();
        if (!expList.isEmpty()) {
            if (validationArguments.isLeadingZeros(ArrayUtils.arrayToString(expList), value)) {
                clear();
            }
        }

        if (validationArguments.validate(expList, value)) {
            addText(value);
        }
    }

    private void equals() {
        Calculator calculatorModel = new Calculator();
        ExpressionParser expressionParser = ExpressionParser.getInstance();
        //Get expression from calculator screen, split if find '\n', also remove '=' and result.
        String expressionScreen = getText();
        String expressionResult = "";
        LinkedList<String> expList = expressionParser.getExpressionPart(expressionScreen);
        for (String values : expList) {
            String result = calculatorModel.calculate(values);
            expressionResult += values.concat("=").concat(result).concat("\n");
        }
        setText(expressionResult);
    }

    private void addText(String value) {
        calcInterface.addText(value);
    }

    private void setText(String value) {
        calcInterface.setText(value);
    }

    private String getText() {
        return calcInterface.getText();
    }

    private void delete() {
        calcInterface.delete();
    }

    private void clear() {
        calcInterface.clear();
    }

}
