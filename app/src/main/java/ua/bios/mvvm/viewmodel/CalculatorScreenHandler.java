package ua.bios.mvvm.viewmodel;

import ua.bios.mvvm.model.GrandTotalData;

/**
 * Created by BIOS on 1/27/2017.
 */

public class CalculatorScreenHandler {
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        checkSizeOfResultStorageAndActivateGTScreen();
    }

    private void checkSizeOfResultStorageAndActivateGTScreen() {
        GrandTotalData grandTotalData = GrandTotalData.getInstance();
        GrandTotalViewModel grandTotalViewModel = GrandTotalViewModel.getInstance();
        if(grandTotalData.getSize() > 0){
            grandTotalViewModel.setActivate(true);
        } else {
            grandTotalViewModel.setActivate(false);
        }
    }
}
