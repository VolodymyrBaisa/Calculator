package ua.bios.mvvm.viewmodel;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import ua.bios.R;
import ua.bios.display.CalculatorScreen;
import ua.bios.mvvm.model.Calculator;
import ua.bios.utils.Parser;


/**
 * Created by BIOS on 12/26/2016.
 */

public class ButtonHandlers {

    public void onClickButton(View v) {

        String value = "";
        if (v instanceof Button) {
            value = ((Button) v).getText().toString();
        } else if (v instanceof ImageButton) {
            value = (v.getContentDescription().toString());
        }

        CalculatorMethods calculatorMethods = CalculatorMethods.getInstance();
        calculatorMethods.validationEnterArguments(v, value);
    }

    public void onClickClear(View v) {
        CalculatorMethods calculatorMethods = CalculatorMethods.getInstance();
        calculatorMethods.clear();
    }

    public void onClickBackspace(View v) {
        CalculatorMethods calculatorMethods = CalculatorMethods.getInstance();
        calculatorMethods.backspace(v);
    }

    public void onClickEquals(View v) {
        CalculatorMethods calculatorMethods = CalculatorMethods.getInstance();
        calculatorMethods.equals(v);
    }
}
