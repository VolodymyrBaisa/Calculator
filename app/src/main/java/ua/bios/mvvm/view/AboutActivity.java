package ua.bios.mvvm.view;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.util.Log;

import ua.bios.R;
import ua.bios.databinding.AboutActivityBinding;
import ua.bios.mvvm.viewmodel.AboutHandler;

/**
 * Created by BIOS on 12/26/2016.
 */

public class AboutActivity extends AppCompatActivity {
    private AboutActivityBinding aboutActivityBinding;
    private static final String TAG = "Easy Calculator";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aboutActivityBinding = DataBindingUtil.setContentView(this, R.layout.about_activity);
        setAboutHandler();
        setLinkClickable();
        getVersionAndCodeInfo();
    }

    private void setAboutHandler() {
        aboutActivityBinding.setAboutHandler(new AboutHandler());
    }

    private void setLinkClickable() {
        aboutActivityBinding.aboutText.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void getVersionAndCodeInfo() {
        try {
            PackageInfo pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            int versionNumber = pinfo.versionCode;
            String versionName = pinfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
