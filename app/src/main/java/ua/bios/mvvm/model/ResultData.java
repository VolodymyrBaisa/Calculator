package ua.bios.mvvm.model;

import java.util.ArrayList;

/**
 * Created by BIOS on 1/26/2017.
 */

public class ResultData {
    private final ArrayList<String> resultStorageArray = new ArrayList<>();
    private static volatile ResultData resultData;

    private ResultData() {
    }

    public static ResultData getInstance() {
        if (resultData == null) {
            synchronized (ResultData.class) {
                return resultData = new ResultData();
            }
        } else {
            return resultData;
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
