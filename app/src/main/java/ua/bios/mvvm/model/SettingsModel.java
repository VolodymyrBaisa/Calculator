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

    public Boolean getVibration(){
        return sharedPreferences.getBoolean(getResourceString(R.string.app_pref_vibration_settings), true);
    }

    public String getScreenFontColor(){
        return sharedPreferences.getString(getResourceString(R.string.app_pref_screen_font_color_settings), "#000000");
    }

    public float getScreenFontSize(){
        String defaultValue = "40";
        String fontSize = sharedPreferences.getString(getResourceString(R.string.app_pref_screen_font_size_settings), defaultValue);
       return Float.parseFloat(fontSize.isEmpty() ? defaultValue : fontSize);
    }

    public int getScreenLines(){
        String defaultValue = "2";
        String lines = sharedPreferences.getString(getResourceString(R.string.app_pref_screen_text_lines_settings), defaultValue);
        return Integer.parseInt(lines.isEmpty() ? defaultValue : lines);
    }

    public Boolean getScreenKeepOn(){
        return sharedPreferences.getBoolean(getResourceString(R.string.app_pref_screen_keep_on_settings), false);
    }

    public float getKeyboardFontSize(){
        String defaultValue = "20";
        String fontSize = sharedPreferences.getString(getResourceString(R.string.app_pref_keyboard_font_size_settings), defaultValue);
        return Float.parseFloat(fontSize.isEmpty() ? defaultValue : fontSize);
    }

    private String getResourceString(int id){
        return iSettings.getContext().getString(id);
    }
}
