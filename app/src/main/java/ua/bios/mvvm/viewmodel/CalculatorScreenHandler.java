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
        GrandTotalViewModel grandTotalViewModel = GrandTotalViewModel.getInstance();
        if (grandTotalData.getSize() > 0) {
            grandTotalViewModel.setGTActivate(true);
        } else {
            grandTotalViewModel.setGTActivate(false);
        }
    }
}
