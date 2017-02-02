package ua.bios.mvvm.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by BIOS on 2/1/2017.
 */

public class MemoryViewModel extends BaseObservable{
    private static volatile MemoryViewModel memoryViewModel;
    private boolean isActivate = false;

    private MemoryViewModel(){}

    public static MemoryViewModel getInstance(){
        if(memoryViewModel == null){
            synchronized (MemoryViewModel.class){
                return memoryViewModel = new MemoryViewModel();
            }
        }else{
            return memoryViewModel;
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
