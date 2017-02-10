package ua.bios.mvvm.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by BIOS on 2/4/2017.
 */

public class AboutViewModel extends BaseObservable{
    private static volatile AboutViewModel aboutViewModel;
    private int versionNumber = 0;
    private String versionName = "";

    private AboutViewModel(){}

    public static AboutViewModel getInstance(){
        if(aboutViewModel == null){
            synchronized (AboutViewModel.class){
                return aboutViewModel = new AboutViewModel();
            }
        } else {
            return aboutViewModel;
        }
    }

    @Bindable
    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
        notifyPropertyChanged(BR.versionName);
    }

    @Bindable
    public int getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
        notifyPropertyChanged(BR.versionNumber);
    }
}
