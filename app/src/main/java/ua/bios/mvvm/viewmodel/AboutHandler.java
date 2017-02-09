package ua.bios.mvvm.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.HapticFeedbackConstants;
import android.view.View;

import ua.bios.mvvm.model.AboutCommunication;
import ua.bios.mvvm.view.MainActivity;

/**
 * Created by BIOS on 2/3/2017.
 */

public class AboutHandler {


    public void onClickBack(View v) {
        getBackToHomeScreen();
        setPerformHapticFeedback(v);
    }

    private void setPerformHapticFeedback(View v) {
        v.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP,HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
    }

    private void getBackToHomeScreen() {
        AboutCommunication aboutCommunication = AboutCommunication.getInstance();
        Context context = aboutCommunication.getContext();


        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}