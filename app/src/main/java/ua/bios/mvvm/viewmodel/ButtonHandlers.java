package ua.bios.mvvm.viewmodel;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import ua.bios.CalculatorScreen;
import ua.bios.R;


/**
 * Created by BIOS on 12/26/2016.
 */

public class ButtonHandlers {

    public void onClickButton(View v) {

        if (v instanceof Button) {
            display(((Button) v).getText().toString());
        } else if (v instanceof ImageButton) {
            display(v.getContentDescription().toString());
        }
    }

    public void onClickClear(View v) {
        Display display = Display.getInstance();
        display.zero.set("0");
        display.zero.notifyChange();
    }

    public void onClickBackspace(View v) {
        CalculatorScreen calculatorScreen = (CalculatorScreen) v.getRootView().findViewById(R.id.screen);
        int cursor = calculatorScreen.getSelectionStart();
        if (cursor > 0) {
            calculatorScreen.delete(cursor - 1, cursor);
        }
    }

    public void onClickEquals(View v){

    }

    private void display(String value) {
        Display display = Display.getInstance();
        display.value.set(value);
        display.value.notifyChange();
    }
}
