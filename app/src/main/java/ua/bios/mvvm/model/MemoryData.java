package ua.bios.mvvm.model;

import java.util.ArrayList;

/**
 * Created by BIOS on 2/1/2017.
 */

public class MemoryData {
    private static volatile MemoryData memoryData;
    private final ArrayList<String> memoryList = new ArrayList<>();

    private MemoryData() {
    }

    public static MemoryData getInstance() {
        if (memoryData == null) {
            synchronized (MemoryData.class) {
                return memoryData = new MemoryData();
            }
        } else {
            return memoryData;
        }
    }

    public String get(int index) {
        return memoryList.get(index);
    }

    public void add(String value) {
        memoryList.add(value);
    }

    public int getLength() {
        return memoryList.size();
    }

    public void clear() {
        memoryList.clear();
    }
}
