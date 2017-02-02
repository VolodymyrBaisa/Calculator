package ua.bios.mvvm.model;

/**
 * Created by BIOS on 2/1/2017.
 */

public class MemoryData {
    private static volatile MemoryData memoryData;
    private String memory = "";

    private MemoryData(){}

    public static MemoryData getInstance(){
        if(memoryData == null){
            synchronized (MemoryData.class){
                return memoryData = new MemoryData();
            }
        } else {
            return memoryData;
        }
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public boolean isEmpty(){
        return memory.isEmpty();
    }

    public void clear(){
        this.memory = "";
    }
}
