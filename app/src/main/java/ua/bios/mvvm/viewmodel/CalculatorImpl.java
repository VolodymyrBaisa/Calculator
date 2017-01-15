package ua.bios.mvvm.viewmodel;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.LinkedList;

import ua.bios.display.CalcInterface;
import ua.bios.mvvm.model.Calculator;

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

    public void onClickEquals(View v) {
        equals();
    }

    private void setValue(String value) {
        ValidationArguments validationArguments = new ValidationArguments();
        LinkedList<String> expList = ExpressionParser.getPart(getText());

        if (!expList.isEmpty()) {
            if (validationArguments.isLeadingZeros(expList.getLast(), value)) {
                clear();
            }
        }

        if (validationArguments.validate(expList, value)) {
            addText(value);
        }
    }

    private void equals() {
        Calculator calculatorModel = new Calculator();
        LinkedList<String> expList = ExpressionParser.getExpressionPart(getText());
        String exp = "";
        for (String values : expList) {
            String result = calculatorModel.calculate(values);
            exp += values.concat("=").concat(result).concat("\n");
        }
        setText(exp);
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
