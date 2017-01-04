package ua.bios.mvvm.viewmodel;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


/**
 * Created by BIOS on 12/26/2016.
 */

public class ButtonHandlers {

    public void onClickButton(View v) {
        Display display = Display.getInstance();

        String value = "";
        if (v instanceof Button) {
            value = ((Button) v).getText().toString();
        } else if (v instanceof ImageButton) {
            value = v.getContentDescription().toString();
        }

       // String screen = Formatter.stringFormat(expressionBuilder.getExpression());
        display.setValue(value);
    }
}
