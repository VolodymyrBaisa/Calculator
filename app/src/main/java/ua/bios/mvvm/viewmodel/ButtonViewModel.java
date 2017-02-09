package ua.bios.mvvm.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by BIOS on 2/7/2017.
 */

public class ButtonViewModel extends BaseObservable {
    private static volatile ButtonViewModel buttonViewModel;
    private float textSize = 20;
    private boolean isVibrationOn = false;

    private ButtonViewModel() {
    }

    public static ButtonViewModel getInstance() {
        if (buttonViewModel == null) {
            synchronized (ButtonViewModel.class) {
                return buttonViewModel = new ButtonViewModel();
            }
        } else {
            return buttonViewModel;
        }
    }

    @Bindable
    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        notifyPropertyChanged(BR.textSize);
    }

    @Bindable
    public boolean isVibrationOn() {
        return isVibrationOn;
    }

    public void setVibrationOn(boolean vibrationOn) {
        isVibrationOn = vibrationOn;
        notifyPropertyChanged(BR.vibrationOn);
    }
}
