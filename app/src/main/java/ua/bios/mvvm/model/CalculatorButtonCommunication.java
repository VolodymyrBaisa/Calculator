package ua.bios.mvvm.model;

import ua.bios.keyboard.ICalculatorButton;

/**
 * Created by BIOS on 2/6/2017.
 */

public class CalculatorButtonCommunication {
    private static volatile CalculatorButtonCommunication calculatorButtonCommunication;
    private final ICalculatorButton iCalculatorButton;

    private CalculatorButtonCommunication(ICalculatorButton iCalculatorButton) {
        this.iCalculatorButton = iCalculatorButton;
    }

    public static CalculatorButtonCommunication getInstance() {
        return calculatorButtonCommunication;
    }

    public static void init(ICalculatorButton iCalculatorButton) {
        synchronized (CalculatorScreenCommunication.class) {
            calculatorButtonCommunication = new CalculatorButtonCommunication(iCalculatorButton);
        }
    }


    public void setFontSize(float size){
        iCalculatorButton.setButtonFontSize(size);
    }
}
