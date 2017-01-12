package ua.bios.mvvm.viewmodel;

import android.view.View;

import java.util.LinkedList;

import ua.bios.R;
import ua.bios.display.CalculatorScreen;
import ua.bios.mvvm.model.Calculator;
import ua.bios.utils.Parser;

/**
 * Created by BIOS on 1/10/2017.
 */

public class CalculatorMethods {
    private static volatile CalculatorMethods calculatorMethods;

    private CalculatorMethods() {
    }

    public static CalculatorMethods getInstance() {
        if (calculatorMethods == null) {
            synchronized (CalculatorMethods.class) {
                return calculatorMethods = new CalculatorMethods();
            }
        } else {
            return calculatorMethods;
        }
    }

    public void clear() {
        Display display = Display.getInstance();
        display.zero.set("0");
        display.zero.notifyChange();
    }

    public void backspace(View v) {
        CalculatorScreen calculatorScreen = (CalculatorScreen) v.getRootView().findViewById(R.id.screen);
        int cursor = calculatorScreen.getSelectionStart();
        if (cursor > 0) {
            calculatorScreen.delete(cursor - 1, cursor);
        }
    }

    public void equals(View v) {
        CalculatorScreen calculatorScreen = (CalculatorScreen) v.getRootView().findViewById(R.id.screen);
        String value = calculatorScreen.getText().toString();

        Calculator calculatorModel = new Calculator();
        String result = calculatorModel.calculate(value);

        Display display = Display.getInstance();
        display.value.set("=".concat(result).concat("\n"));
    }

    public void validationEnterArguments(View v, String value){
        ValidationArguments validationArguments = ValidationArguments.getInstance();
        CalculatorScreen calculatorScreen = (CalculatorScreen) v.getRootView().findViewById(R.id.screen);
        LinkedList<String> expList = Parser.parse(calculatorScreen.getText().toString());
        Formatter formatter = Formatter.getInstance();
        formatter.stringFormat(expList);
        if (validationArguments.validate(expList, value)) {
            display(value);
        }
    }

    private void display(String value) {
        Display display = Display.getInstance();
        display.value.set(value);
        display.value.notifyChange();
    }
}
