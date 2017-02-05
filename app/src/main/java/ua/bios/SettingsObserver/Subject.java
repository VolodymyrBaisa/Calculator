package ua.bios.SettingsObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BIOS on 2/5/2017.
 */

public class Subject implements ISubject {
    private List<IObserver> observers = new ArrayList<>();

    @Override
    public void register(IObserver obj) {
        observers.add(obj);
    }

    @Override
    public void unregister(IObserver obj) {
        observers.remove(obj);
    }

    @Override
    public void notifyWrite() {
        for (IObserver observer : observers) {
            observer.writeSettings();
        }
    }

    @Override
    public void notifyRead() {
        for (IObserver observer : observers) {
            observer.readSettings();
        }
    }
}
