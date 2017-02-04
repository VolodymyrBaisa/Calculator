package ua.bios.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Created by BIOS on 2/4/2017.
 */

public class PackageInformation {
    private PackageInfo pinfo;
    private static final String TAG = "Easy Calculator";

    public PackageInformation(Context context){
        try {
        pinfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public int getVersionNumber(){
        return pinfo.versionCode;
    }

    public String getVersionName(){
        return pinfo.versionName;
    }
}
