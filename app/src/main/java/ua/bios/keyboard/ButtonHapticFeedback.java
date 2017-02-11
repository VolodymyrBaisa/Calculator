package ua.bios.keyboard;

import android.view.HapticFeedbackConstants;
import android.view.View;

/**
 * Created by BIOS on 2/10/2017.
 */

public class ButtonHapticFeedback {
    private ButtonHapticFeedback(){}

    public static void setPerformHapticFeedback(View v) {
        v.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
    }
}
