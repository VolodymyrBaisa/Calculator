package ua.bios.mvvm.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.View;

import ua.bios.keyboard.ButtonHapticFeedback;
import ua.bios.mvvm.model.AboutCommunication;
import ua.bios.mvvm.view.MainActivity;

/**
 * Created by BIOS on 2/3/2017.
 */

public class AboutHandler {

    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            ButtonHapticFeedback.setPerformHapticFeedback(v);
        }
        return false;
    }

    public void onClickBack(View v) {
        getBackToHomeScreen();
    }

    private void getBackToHomeScreen() {
        AboutCommunication aboutCommunication = AboutCommunication.getInstance();
        Context context = aboutCommunication.getContext();


        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}