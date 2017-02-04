package ua.bios.mvvm.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by BIOS on 1/26/2017.
 */

public class CalculatorViewModel extends BaseObservable {
    private static volatile CalculatorViewModel calculatorViewModel;
    private boolean isGTActivate = false;
    private boolean isMemoryActivate = false;
    private int memorySize = 0;
    private String taxRateValue = "0";

    private CalculatorViewModel() {
    }

    public static CalculatorViewModel getInstance() {
        if (calculatorViewModel == null) {
            synchronized (CalculatorViewModel.class) {
                return calculatorViewModel = new CalculatorViewModel();
            }
        } else {
            return calculatorViewModel;
        }
    }

    @Bindable
    public boolean isGTActivate() {
        return isGTActivate;
    }

    public void setGTActivate(boolean gTActivate) {
        this.isGTActivate = gTActivate;
        notifyPropertyChanged(BR.gTActivate);
    }

    @Bindable
    public boolean isMemoryActivate() {
        return isMemoryActivate;
    }

    public void setMemoryActivate(boolean memoryActivate) {
        isMemoryActivate = memoryActivate;
        notifyPropertyChanged(BR.memoryActivate);
    }

    @Bindable
    public int getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
        notifyPropertyChanged(BR.memorySize);
    }

    @Bindable
    public String getTaxRateValue() {
        return taxRateValue;
    }

    public void setTaxRateValue(String taxRateValue) {
        this.taxRateValue = taxRateValue;
        notifyPropertyChanged(BR.taxRateValue);
    }
}
