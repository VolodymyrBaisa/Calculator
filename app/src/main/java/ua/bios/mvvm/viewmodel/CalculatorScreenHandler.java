package ua.bios.mvvm.viewmodel;

import ua.bios.mvvm.model.GrandTotalData;

/**
 * Created by BIOS on 1/27/2017.
 */

public class CalculatorScreenHandler {
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
