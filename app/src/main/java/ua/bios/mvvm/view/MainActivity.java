package ua.bios.mvvm.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import ua.bios.R;
import ua.bios.databinding.MainLayoutBinding;
import ua.bios.mvvm.viewmodel.ButtonHandlers;
import ua.bios.mvvm.viewmodel.Display;

/**
 * Created by BIOS on 12/26/2016.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainLayoutBinding mainLayoutBinding = DataBindingUtil.setContentView(this, R.layout.main_layout);
        Display display = Display.getInstance();
        mainLayoutBinding.screenActivity.setDisplay(display);
        mainLayoutBinding.keyboardActivity.setButtonHandlers(new ButtonHandlers());

    }
}
