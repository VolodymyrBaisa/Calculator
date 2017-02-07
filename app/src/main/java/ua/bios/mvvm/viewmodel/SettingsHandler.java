package ua.bios.mvvm.viewmodel;

import ua.bios.SettingsObserver.IObserver;
import ua.bios.mvvm.model.CalculatorScreenCommunication;
import ua.bios.mvvm.model.CalculatorButtonCommunication;
import ua.bios.mvvm.model.SettingsModel;
import ua.bios.mvvm.model.TaxRateData;

/**
 * Created by BIOS on 2/5/2017.
 */

public class SettingsHandler implements IObserver {


    @Override
    public void writeSettings() {
        SettingsModel settingsModel = SettingsModel.getInstance();
        CalculatorScreenCommunication calculatorScreenCommunication = CalculatorScreenCommunication.getInstance();
        settingsModel.setScreenExpression(calculatorScreenCommunication.getText());
        settingsModel.setCursorPosition(calculatorScreenCommunication.getCursorPosition());

        TaxRateData taxRateData = TaxRateData.getInstance();
        settingsModel.setTaxRate(taxRateData.getTax());
    }

    @Override
    public void readSettings() {
        SettingsModel settingsModel = SettingsModel.getInstance();
        settingsModel.setDefaultValues();
        CalculatorScreenCommunication calculatorScreenCommunication = CalculatorScreenCommunication.getInstance();
        calculatorScreenCommunication.setText(settingsModel.getScreenExpression());
        calculatorScreenCommunication.setCursorPosition(settingsModel.getCursorPosition());

        String taxRate = settingsModel.getTaxRate();
        CalculatorViewModel taxRateViewModel = CalculatorViewModel.getInstance();
        taxRateViewModel.setTaxRateValue(taxRate);

        TaxRateData taxRateData = TaxRateData.getInstance();
        taxRateData.setTax(taxRate);

        calculatorScreenCommunication.setTextColor(settingsModel.getScreenFontColor());
        calculatorScreenCommunication.setTextSize(settingsModel.getScreenFontSize());
        calculatorScreenCommunication.setTextLines(settingsModel.getScreenLines());
        calculatorScreenCommunication.setScreenAlwaysOn(settingsModel.getScreenKeepOn());

        CalculatorButtonCommunication calculatorButtonCommunication = CalculatorButtonCommunication.getInstance();
        calculatorButtonCommunication.setFontSize(settingsModel.getKeyboardFontSize());
    }
}
