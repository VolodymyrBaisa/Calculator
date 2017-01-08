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
import ua.bios.mvvm.viewmodel.ButtonHandlers;
import ua.bios.mvvm.viewmodel.Display;

/**
 * Created by BIOS on 12/26/2016.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainLayoutBinding mainLayoutBinding = DataBindingUtil.setContentView(this, R.layout.main_layout);
        Display display = Display.getInstance();
        mainLayoutBinding.screenActivity.setDisplay(display);
        mainLayoutBinding.keyboardActivity.setButtonHandlers(new ButtonHandlers());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.about){
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            this.startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
