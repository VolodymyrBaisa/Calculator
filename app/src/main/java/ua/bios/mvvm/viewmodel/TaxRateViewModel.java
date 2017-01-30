package ua.bios.mvvm.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import ua.bios.BR;

/**
 * Created by BIOS on 1/29/2017.
 */

public class TaxRateViewModel extends BaseObservable {
    private static volatile TaxRateViewModel taxRateViewModel;
    private String value = "0";

    private TaxRateViewModel() {
    }

    public static TaxRateViewModel getInstance() {
        if (taxRateViewModel == null) {
            synchronized (TaxRateViewModel.class) {
                return taxRateViewModel = new TaxRateViewModel();
            }
        } else {
            return taxRateViewModel;
        }
    }

    @Bindable
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        notifyPropertyChanged(BR.value);
    }
}
