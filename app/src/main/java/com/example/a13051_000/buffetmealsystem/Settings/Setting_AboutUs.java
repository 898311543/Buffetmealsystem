package com.example.a13051_000.buffetmealsystem.Settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.a13051_000.buffetmealsystem.R;

/**
 * Created by 13051_000 on 2016/7/23.
 */
public class Setting_AboutUs extends AppCompatActivity {

    private Toolbar toolbar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_aboutus);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("关于我们");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
