package ua.bios.display;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;

/**
 * Created by BIOS on 1/2/2017.
 */

public class CalculatorScreen extends EditText {
    private Context context;

    public CalculatorScreen(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        styles();
        addText("0");
    }

    private void styles() {
        super.setTextIsSelectable(true);
        super.setAllCaps(false);
        super.setGravity(Gravity.CENTER);
        super.setTextAlignment(TEXT_ALIGNMENT_TEXT_END);
        super.setVerticalFadingEdgeEnabled(true);
    }

    public void setFont(String fontPath) {
        Typeface fontsStyle = Typeface.createFromAsset(context.getAssets(), fontPath);
        this.setTypeface(fontsStyle, Typeface.NORMAL);
    }

    public void addText(String value) {
        super.getText().insert(getSelectionStart(), value);
    }

    public void delete() {
        int cursor = getSelectionStart();
        if (cursor > 0) {
            super.getText().delete(cursor - 1, cursor);
        }
    }

    public void clear() {
        super.setText("");
    }
}
