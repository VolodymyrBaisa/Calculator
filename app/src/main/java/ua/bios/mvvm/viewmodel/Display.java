package ua.bios.mvvm.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import ua.bios.BR;


/**
 * Created by BIOS on 1/2/2017.
 */

public class Display extends BaseObservable {
    private static volatile Display display;
    private String value = "";

    private Display() {
    }

    public static Display getInstance() {
        if (display == null) {
            synchronized (Display.class) {
                return display = new Display();
            }
        } else {
            return display;
        }
    }

    @Bindable
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        display.notifyPropertyChanged(BR.value);
    }
}
