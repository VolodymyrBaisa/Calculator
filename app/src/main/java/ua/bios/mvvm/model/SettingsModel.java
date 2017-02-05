package ua.bios.mvvm.model;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import ua.bios.R;
import ua.bios.settings.ISettings;

/**
 * Created by BIOS on 2/4/2017.
 */

public class SettingsModel {
    private static volatile SettingsModel settingsModel;
    private ISettings iSettings;
    private SharedPreferences sharedPreferences;

    private SettingsModel(ISettings iSettings) {
        this.iSettings = iSettings;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(iSettings.getContext());
    }

    public static void init(ISettings iSettings) {
        synchronized (SettingsModel.class) {
            settingsModel = new SettingsModel(iSettings);
        }
    }

    public static SettingsModel getInstance() {
        return settingsModel;
    }

    public void setDefaultValues() {
        PreferenceManager.setDefaultValues(iSettings.getContext(), R.xml.preferences, false);
    }

    public String getScreenExpression() {
        return sharedPreferences.getString(getResourceString(R.string.app_pref_expression_settings), "0");
    }

    public void setScreenExpression(String expression) {
        sharedPreferences.edit().putString(getResourceString(R.string.app_pref_expression_settings), expression).apply();
    }

    public int getCursorPosition(){
        return sharedPreferences.getInt(getResourceString(R.string.app_pref_cursor_position_settings), 0);
    }

    public void setCursorPosition(int cursorPosition){
        sharedPreferences.edit().putInt(getResourceString(R.string.app_pref_cursor_position_settings), cursorPosition).apply();
    }

    public String getTaxRate(){
        return sharedPreferences.getString(getResourceString(R.string.app_pref_tax_rate_settings), "0");
    }

    public void setTaxRate(String taxRate){
        sharedPreferences.edit().putString(getResourceString(R.string.app_pref_tax_rate_settings), taxRate).apply();
    }

    public void setVibration(boolean vibration){
        sharedPreferences.edit().putBoolean(getResourceString(R.string.app_pref_vibration_settings), vibration).apply();
    }

    public Boolean getVibration(){
        return sharedPreferences.getBoolean(getResourceString(R.string.app_pref_vibration_settings), false);
    }

    public void setVibrationLenght(int lenght){
        sharedPreferences.edit().putInt(getResourceString(R.string.app_pref_vibration_length_settings), lenght).apply();
    }

    public int getVibrationLenght(){
        return sharedPreferences.getInt(getResourceString(R.string.app_pref_vibration_length_settings), 0);
    }

    public void setScreenFontColor(String color){
        sharedPreferences.edit().putString(getResourceString(R.string.app_pref_screen_font_color_settings), color).apply();
    }

    public String getScreenFontColor(){
        return sharedPreferences.getString(getResourceString(R.string.app_pref_screen_font_color_settings), "#000000");
    }

    public void setScreenFontSize(int size){
        sharedPreferences.edit().putInt(getResourceString(R.string.app_pref_screen_font_size_settings), size).apply();
    }

    public int getScreenFontSize(){
        return sharedPreferences.getInt(getResourceString(R.string.app_pref_screen_font_size_settings), 40);
    }

    public void setScreenKeepOn(boolean keep){
        sharedPreferences.edit().putBoolean(getResourceString(R.string.app_pref_screen_keep_on_settings), keep).apply();
    }

    public Boolean getScreenKeepOn(){
        return sharedPreferences.getBoolean(getResourceString(R.string.app_pref_screen_keep_on_settings), false);
    }

    public void setKeyboardFontSize(int size){
        sharedPreferences.edit().putInt(getResourceString(R.string.app_pref_keyboard_font_size_settings), size).apply();
    }

    public int getKeyboardFontSize(){
        return sharedPreferences.getInt(getResourceString(R.string.app_pref_keyboard_font_size_settings), 20);
    }

    private String getResourceString(int id){
        return iSettings.getContext().getString(id);
    }
}
