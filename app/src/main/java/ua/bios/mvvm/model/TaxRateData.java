package ua.bios.mvvm.model;

/**
 * Created by BIOS on 1/29/2017.
 */

public class TaxRateData {
    private static volatile TaxRateData taxRateData;
    private String tax;

    private TaxRateData(){}

    public static TaxRateData getInstance(){
        if(taxRateData == null){
            synchronized (TaxRateData.class){
                return taxRateData = new TaxRateData();
            }
        } else {
            return taxRateData;
        }
    }

    public String getTax(){
        return tax;
    }

    public void setTax(String tax){
        this.tax = tax;
    }

}
