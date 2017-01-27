package ua.bios.mvvm.model;

import java.util.ArrayList;

/**
 * Created by BIOS on 1/26/2017.
 */

public class ResultStorage {
    private final ArrayList<String> resultStorageArray = new ArrayList<>();
    private static volatile ResultStorage resultStorage;

    private ResultStorage() {
    }

    public static ResultStorage getInstance() {
        if (resultStorage == null) {
            synchronized (ResultStorage.class) {
                return resultStorage = new ResultStorage();
            }
        } else {
            return resultStorage;
        }
    }

    public void add(String result) {
        resultStorageArray.add(result);
    }

    public String get(int index) {
        return resultStorageArray.get(index);
    }

    public int getSize() {
        return resultStorageArray.size();
    }

    public void clear() {
        resultStorageArray.clear();
    }
}
