package ua.bios.mvvm.viewmodel;

import ua.bios.mvvm.model.GrandTotalData;
import ua.bios.mvvm.model.MemoryData;

/**
 * Created by BIOS on 1/27/2017.
 */

public class CalculatorScreenHandler {
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        activateGTSign();
        activateMemorySign();
    }

    private void activateMemorySign(){
        MemoryData memoryData = MemoryData.getInstance();
        MemoryViewModel memoryViewModel = MemoryViewModel.getInstance();
        if(!memoryData.isEmpty()){
            memoryViewModel.setActivate(true);
        } else {
            memoryViewModel.setActivate(false);
        }
    }

    private void activateGTSign() {
        GrandTotalData grandTotalData = GrandTotalData.getInstance();
        GrandTotalViewModel grandTotalViewModel = GrandTotalViewModel.getInstance();
        if(grandTotalData.getSize() > 0){
            grandTotalViewModel.setActivate(true);
        } else {
            grandTotalViewModel.setActivate(false);
        }
    }
}
