package ua.bios.mvvm.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;


/**
 * Created by BIOS on 1/2/2017.
 */

public class Display extends BaseObservable {
    private static volatile Display display;
    @Bindable
    public final ObservableField<String> value = new ObservableField<>("");
    @Bindable
    public final ObservableField<String> zero = new ObservableField<>("0");

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
}
