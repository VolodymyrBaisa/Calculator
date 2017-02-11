package ua.bios.mvvm.viewmodel;

import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import ua.bios.mvvm.model.GrandTotalData;

/**
 * Created by BIOS on 1/27/2017.
 */

public class CalculatorScreenHandler {
    public boolean onTouch(View v, MotionEvent event) {
        setCursorPosition((EditText) v, event);
        return true;
    }

    private void setCursorPosition(EditText v, MotionEvent event) {
        EditText screen = v;
        int click_position = screen.getOffsetForPosition(event.getX(), event.getY());
        screen.setSelection(click_position);
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        activateGTSign();
    }

    private void activateGTSign() {
        GrandTotalData grandTotalData = GrandTotalData.getInstance();
        CalculatorViewModel calculatorViewModel = CalculatorViewModel.getInstance();
        if (grandTotalData.getSize() > 0) {
            calculatorViewModel.setGTActivate(true);
        } else {
            calculatorViewModel.setGTActivate(false);
        }
    }
}
