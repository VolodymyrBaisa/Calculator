package ua.bios.mvvm.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by BIOS on 2/7/2017.
 */

public class ButtonViewModel extends BaseObservable {
    private static volatile ButtonViewModel buttonViewModel;
    private float fontSize = 20;

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
    public float getFontSize() {
        return fontSize;
    }

    public void setFontSize(float fontSize) {
        this.fontSize = fontSize;
        notifyPropertyChanged(BR.fontSize);
    }
}
