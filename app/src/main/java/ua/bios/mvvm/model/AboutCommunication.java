package ua.bios.mvvm.model;

import android.content.Context;

import ua.bios.about.IAbout;

/**
 * Created by BIOS on 2/4/2017.
 */

public class AboutCommunication {
    private static volatile AboutCommunication aboutCommunication;
    private IAbout iAbout; // call it about

    private AboutCommunication(IAbout iAbout){
        this.iAbout = iAbout;
    }

    public static void init(IAbout iAbout){
        synchronized (AboutCommunication.class) {
            aboutCommunication = new AboutCommunication(iAbout);
        }
    }

    public static AboutCommunication getInstance(){
        return aboutCommunication;
    }

    public Context getContext(){
        return iAbout.getContext();
    }
}
