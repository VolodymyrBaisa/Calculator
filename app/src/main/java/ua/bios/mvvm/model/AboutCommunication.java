package ua.bios.mvvm.model;

import android.content.Context;

import ua.bios.about.AboutInterface;

/**
 * Created by BIOS on 2/4/2017.
 */

public class AboutCommunication {
    private static volatile AboutCommunication aboutCommunication;
    private AboutInterface aboutInterface;

    private AboutCommunication(AboutInterface aboutInterface){
        this.aboutInterface = aboutInterface;
    }

    public static void init(AboutInterface aboutInterface){
        synchronized (AboutCommunication.class) {
            aboutCommunication = new AboutCommunication(aboutInterface);
        }
    }

    public static AboutCommunication getInstance(){
        return aboutCommunication;
    }

    public Context getContext(){
        return aboutInterface.getContext();
    }
}
