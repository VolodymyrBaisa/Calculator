package ua.bios.mvvm.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by BIOS on 1/26/2017.
 */

public class GrandTotalViewModel extends BaseObservable {
    private static volatile GrandTotalViewModel grandTotalViewModel;
    private boolean isActivate = false;

    private GrandTotalViewModel(){}

    public static GrandTotalViewModel getInstance(){
        if(grandTotalViewModel == null){
            synchronized (GrandTotalViewModel.class){
                return grandTotalViewModel = new GrandTotalViewModel();
            }
        }else{
            return grandTotalViewModel;
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
