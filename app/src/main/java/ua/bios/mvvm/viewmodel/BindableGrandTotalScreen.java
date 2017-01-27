package ua.bios.mvvm.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by BIOS on 1/26/2017.
 */

public class BindableGrandTotalScreen extends BaseObservable {
    private static volatile BindableGrandTotalScreen bindableGrandTotalScreen;
    private boolean isActivate = false;

    private BindableGrandTotalScreen(){}

    public static BindableGrandTotalScreen getInstance(){
        if(bindableGrandTotalScreen == null){
            synchronized (BindableGrandTotalScreen.class){
                return bindableGrandTotalScreen = new BindableGrandTotalScreen();
            }
        }else{
            return bindableGrandTotalScreen;
        }
    }

    @Bindable
    public boolean isActivate() {
        return isActivate;
    }

    public void setActivate(boolean activate) {
        isActivate = activate;
        notifyPropertyChanged(BR.activate);
    }
}
