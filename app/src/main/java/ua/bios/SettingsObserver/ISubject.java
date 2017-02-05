package ua.bios.SettingsObserver;

/**
 * Created by BIOS on 2/5/2017.
 */

public interface ISubject {
    void register(IObserver obj);
    void unregister(IObserver obj);

    void notifyWrite();
    void notifyRead();
}
