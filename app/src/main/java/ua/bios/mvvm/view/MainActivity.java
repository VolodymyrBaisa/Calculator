package ua.bios.mvvm.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import ua.bios.R;
import ua.bios.databinding.MainLayoutBinding;
import ua.bios.display.CalculatorInterface;
import ua.bios.mvvm.model.CalculatorScreenCommunication;
import ua.bios.mvvm.viewmodel.CalculatorHandler;
import ua.bios.mvvm.viewmodel.CalculatorScreenHandler;
import ua.bios.mvvm.viewmodel.CalculatorViewModel;

/**
 * Created by BIOS on 12/26/2016.
 */

public class MainActivity extends AppCompatActivity implements CalculatorInterface {
    private MainLayoutBinding mainLayoutBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainLayoutBinding = DataBindingUtil.setContentView(this, R.layout.main_layout);
        setKeyboardHandler();
        setCalculatorViewModel();
        setCalculatorScreenHandler();
        CalculatorScreenCommunication.init(this);
    }

    private void setCalculatorScreenHandler() {
        mainLayoutBinding.screenActivity.setCalculatorScreenHandler(new CalculatorScreenHandler());
    }

    private void setCalculatorViewModel() {
        CalculatorViewModel calculatorViewModel = CalculatorViewModel.getInstance();
        mainLayoutBinding.screenActivity.setCalculatorViewModel(calculatorViewModel);
        mainLayoutBinding.keyboardActivity.setCalculatorViewModel(calculatorViewModel);
    }

    private void setKeyboardHandler() {
        mainLayoutBinding.keyboardActivity.setKeyboard(new CalculatorHandler());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            this.startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public String getText() {
        return mainLayoutBinding.screenActivity.screen.getText().toString();
    }

    @Override
    public void addText(String value) {
        mainLayoutBinding.screenActivity.screen.addText(value);
    }

    @Override
    public void insertText(int cursor, String value) {
        mainLayoutBinding.screenActivity.screen.insertText(cursor, value);
    }

    @Override
    public int getCursorPosition() {
        return mainLayoutBinding.screenActivity.screen.getSelectionStart();
    }

    @Override
    public void setCursorPosition(int value) {
        mainLayoutBinding.screenActivity.screen.setSelection(value);
    }

    @Override
    public char getCharAt(int i) {
        return mainLayoutBinding.screenActivity.screen.getCharAt(i);
    }

    @Override
    public void clear() {
        mainLayoutBinding.screenActivity.screen.clear();
    }

    @Override
    public void delete() {
        mainLayoutBinding.screenActivity.screen.delete();
    }

    @Override
    public void delete(int start, int end) {
        mainLayoutBinding.screenActivity.screen.delete(start, end);
    }
}
