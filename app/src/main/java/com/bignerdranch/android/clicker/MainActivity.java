package com.bignerdranch.android.clicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // это будет именем файла настроек
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_COUNTER = "counter";

    private SharedPreferences mSettings;

    public int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if (savedInstanceState != null) {
//            count = savedInstanceState.getInt("key");
//            TextView textView = (TextView) findViewById(R.id.text);
//            textView.setText(Integer.toString(count));
//        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void onMyButtonClick(View view)
    {
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(Integer.toString(++count));
    }

    public void resetCount(View view)
    {
        count = 0;
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(Integer.toString(count));
    }

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putInt("key", count);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_count_clicks:
                Intent intent = new Intent(this, CountActivity.class);
                intent.putExtra("count", count);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mSettings.contains(APP_PREFERENCES_COUNTER)) {
            // Получаем число из настроек
            count = mSettings.getInt(APP_PREFERENCES_COUNTER, 0);
            // Выводим на экран данные из настроек
            TextView textView = (TextView) findViewById(R.id.text);
            textView.setText(Integer.toString(count));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Запоминаем данные
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(APP_PREFERENCES_COUNTER, count);
        editor.apply();
    }

}
