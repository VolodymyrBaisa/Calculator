package ua.bios.keyboard;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.Button;

/**
 * Created by BIOS on 2/7/2017.
 */

public class CalculatorButton extends Button {
    public CalculatorButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setFontSize(float size) {
        super.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }
}
