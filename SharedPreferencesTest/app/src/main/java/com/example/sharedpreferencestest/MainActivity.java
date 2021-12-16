package com.example.sharedpreferencestest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void saveData(View view) {
        SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
        editor.putString("name","Qricis Qi");
        editor.putInt("age",23);
        editor.putBoolean("married",false);
        editor.apply();
    }

    public void restoreData(View view) {
        SharedPreferences pref = getSharedPreferences("data",MODE_PRIVATE);
        String name = pref.getString("name","");
        int age = pref.getInt("age",0);
        boolean married = pref.getBoolean("married",false);

        Log.d("MainActivity","name is " + name);
        Log.d("MainActivity","age is " + age);
        Log.d("MainActivity","married is " + married);
    }
}