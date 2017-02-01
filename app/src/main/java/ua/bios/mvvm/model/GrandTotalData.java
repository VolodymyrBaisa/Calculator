package ua.bios.mvvm.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by BIOS on 1/26/2017.
 */

public class GrandTotalData {
    private final ArrayList<String> resultStorageArray = new ArrayList<>();
    private static volatile GrandTotalData grandTotalData;

    private GrandTotalData() {
    }

    public static GrandTotalData getInstance() {
        if (grandTotalData == null) {
            synchronized (GrandTotalData.class) {
                return grandTotalData = new GrandTotalData();
            }
        } else {
            return grandTotalData;
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

    public String[] getArrayString() {
        return resultStorageArray.toArray(new String[resultStorageArray.size()]);
    }

    public void addAll(String[] array){
        resultStorageArray.addAll(Arrays.asList(array));
    }

    public void clear() {
        resultStorageArray.clear();
    }
}
