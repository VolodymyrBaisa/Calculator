package ua.bios.mvvm.viewmodel;

import ua.bios.SettingsObserver.IObserver;
import ua.bios.mvvm.model.CalculatorScreenCommunication;
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
        saveScreenState(settingsModel, calculatorScreenCommunication);
        saveTaxRate(settingsModel);
    }

    private void saveScreenState(SettingsModel settingsModel, CalculatorScreenCommunication calculatorScreenCommunication) {
        settingsModel.setScreenExpression(calculatorScreenCommunication.getText());
        settingsModel.setCursorPosition(calculatorScreenCommunication.getCursorPosition());
    }

    private void saveTaxRate(SettingsModel settingsModel) {
        TaxRateData taxRateData = TaxRateData.getInstance();
        settingsModel.setTaxRate(taxRateData.getTax());
    }

    @Override
    public void readSettings() {
        SettingsModel settingsModel = SettingsModel.getInstance();
        setDefaultValues(settingsModel);

        CalculatorScreenCommunication calculatorScreenCommunication = CalculatorScreenCommunication.getInstance();
        restoreScreenState(settingsModel, calculatorScreenCommunication);
        restoreTaxRate(settingsModel);
        setScreenAttributes(settingsModel, calculatorScreenCommunication);
        setButtonsAttributes(settingsModel);
    }

    private void setButtonsAttributes(SettingsModel settingsModel) {
        ButtonViewModel buttonViewModel = ButtonViewModel.getInstance();
        buttonViewModel.setTextSize(settingsModel.getKeyboardFontSize());
        buttonViewModel.setVibrationOn(settingsModel.getVibration());
    }

    private void setDefaultValues(SettingsModel settingsModel) {
        settingsModel.setDefaultValues();
    }

    private void setScreenAttributes(SettingsModel settingsModel, CalculatorScreenCommunication calculatorScreenCommunication) {
        calculatorScreenCommunication.setTextColor(settingsModel.getScreenFontColor());
        calculatorScreenCommunication.setTextSize(settingsModel.getScreenFontSize());
        calculatorScreenCommunication.setTextLines(settingsModel.getScreenLines());
        calculatorScreenCommunication.setScreenAlwaysOn(settingsModel.getScreenKeepOn());
    }

    private void restoreTaxRate(SettingsModel settingsModel) {
        String taxRate = settingsModel.getTaxRate();
        CalculatorViewModel calculatorViewModel = CalculatorViewModel.getInstance();
        calculatorViewModel.setTaxRateValue(taxRate);

        TaxRateData taxRateData = TaxRateData.getInstance();
        taxRateData.setTax(taxRate);
    }

    private void restoreScreenState(SettingsModel settingsModel, CalculatorScreenCommunication calculatorScreenCommunication) {
        calculatorScreenCommunication.setText(settingsModel.getScreenExpression());
        calculatorScreenCommunication.setCursorPosition(settingsModel.getCursorPosition());
    }
}
