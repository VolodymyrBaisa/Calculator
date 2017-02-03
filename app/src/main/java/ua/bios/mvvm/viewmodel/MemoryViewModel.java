package ua.bios.mvvm.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by BIOS on 2/1/2017.
 */

public class MemoryViewModel extends BaseObservable {
    private static volatile MemoryViewModel memoryViewModel;
    private boolean isMemoryActivate = false;
    private int getSize = 0;

    private MemoryViewModel() {
    }

    public static MemoryViewModel getInstance() {
        if (memoryViewModel == null) {
            synchronized (MemoryViewModel.class) {
                return memoryViewModel = new MemoryViewModel();
            }
        } else {
            return memoryViewModel;
        }
    }

    @Bindable
    public boolean isMemoryActivate() {
        return isMemoryActivate;
    }

    public void setMemoryActivate(boolean memoryActivate) {
        isMemoryActivate = memoryActivate;
        notifyPropertyChanged(BR.memoryActivate);
    }

    @Bindable
    public int getGetSize() {
        return getSize;
    }

    public void setGetSize(int getSize) {
        this.getSize = getSize;
        notifyPropertyChanged(BR.getSize);
    }
}
