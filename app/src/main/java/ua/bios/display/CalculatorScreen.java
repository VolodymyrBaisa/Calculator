package ua.bios.display;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.EditText;

/**
 * Created by BIOS on 1/2/2017.
 */

public class CalculatorScreen extends EditText {
    private final Context context;

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

    public void setScreenAlwaysOn(boolean flag){
        if(flag) {
            ((Activity) context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            ((Activity) context).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    public void setFont(String fontPath) {
        Typeface fontsStyle = Typeface.createFromAsset(context.getAssets(), fontPath);
        this.setTypeface(fontsStyle, Typeface.NORMAL);
    }

    public void setTextColor(String color) {
        super.setTextColor(Color.parseColor(color));
    }

    public void setTextLines(int count) {
        super.setMinLines(count);
        super.setMaxLines(count);
    }

    public void addText(String value) {
        super.getText().insert(getSelectionStart(), value);
    }

    public void insertText(int cursor, String value) {
        super.getText().insert(cursor, value);
    }

    public char getCharAt(int i) {
        return super.getText().charAt(i);
    }

    public void delete() {
        int cursor = getSelectionStart();
        if (cursor > 0) {
            super.getText().delete(cursor - 1, cursor);
        }
    }

    public void delete(int start, int end) {
        super.getText().delete(start, end);
    }

    public void clear() {
        super.setText("");
    }
}