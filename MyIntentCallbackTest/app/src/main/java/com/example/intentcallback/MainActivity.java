package com.example.intentcallback;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedDate = data.getStringExtra("data_return");
                    Log.d("MainActicity", returnedDate);
                    Toast.makeText(this,returnedDate,Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    public void toGetData(View view) {

        Intent intent = new Intent(this,SecondActivity.class);
        startActivityForResult(intent,1);

    }
}