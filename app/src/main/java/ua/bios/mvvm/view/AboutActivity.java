package ua.bios.mvvm.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;

import ua.bios.R;
import ua.bios.about.IAbout;
import ua.bios.databinding.AboutActivityBinding;
import ua.bios.mvvm.model.AboutCommunication;
import ua.bios.mvvm.viewmodel.AboutHandler;
import ua.bios.mvvm.viewmodel.AboutViewModel;
import ua.bios.mvvm.viewmodel.ButtonViewModel;
import ua.bios.utils.PackageInformation;

/**
 * Created by BIOS on 12/26/2016.
 */

public class AboutActivity extends AppCompatActivity implements IAbout {
    private AboutActivityBinding aboutActivityBinding;
    private AboutViewModel aboutViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aboutActivityBinding = DataBindingUtil.setContentView(this, R.layout.about_activity);
        setAboutHandler();
        setAboutViewModel();
        setLinkClickable();
        setButtonViewModel();
        AboutCommunication.init(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        PackageInformation packageInformation = new PackageInformation(this);
        aboutViewModel.setVersionName(packageInformation.getVersionName());
        aboutViewModel.setVersionNumber(packageInformation.getVersionNumber());
    }

    private void setAboutHandler() {
        aboutActivityBinding.setAboutHandler(new AboutHandler());
    }

    private void setAboutViewModel() {
        aboutViewModel = AboutViewModel.getInstance();
        aboutActivityBinding.setAboutViewModel(aboutViewModel);
    }

    private void setLinkClickable() {
        aboutActivityBinding.aboutText.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void setButtonViewModel() {
        ButtonViewModel buttonViewModel = ButtonViewModel.getInstance();
        aboutActivityBinding.setCalculatorButton(buttonViewModel);
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }
}
