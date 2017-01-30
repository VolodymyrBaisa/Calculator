package ua.bios.mvvm.viewmodel;

/**
 * Created by BIOS on 1/29/2017.
 */

public class TaxRateViewModel {
    private static volatile TaxRateViewModel taxRateViewModel;
    private String value = "0";

    private TaxRateViewModel(){}

    public static TaxRateViewModel getInstance(){
        if(taxRateViewModel == null){
            synchronized (TaxRateViewModel.class){
                return taxRateViewModel = new TaxRateViewModel();
            }
        } else {
            return taxRateViewModel;
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
