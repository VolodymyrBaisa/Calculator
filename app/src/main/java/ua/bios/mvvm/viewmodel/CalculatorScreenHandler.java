package ua.bios.mvvm.viewmodel;

import ua.bios.mvvm.model.ResultStorage;

/**
 * Created by BIOS on 1/27/2017.
 */

public class CalculatorScreenHandler {
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        checkSizeOfResultStorageAndActivateGTScreen();
    }

    private void checkSizeOfResultStorageAndActivateGTScreen() {
        ResultStorage resultStorage = ResultStorage.getInstance();
        BindableGrandTotalScreen bindableGrandTotalScreen = BindableGrandTotalScreen.getInstance();
        if(resultStorage.getSize() > 0){
            bindableGrandTotalScreen.setActivate(true);
        } else {
            bindableGrandTotalScreen.setActivate(false);
        }
    }
}
