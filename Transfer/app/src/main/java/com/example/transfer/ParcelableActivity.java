package com.example.transfer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.data.ExamineByParcelable;

public class ParcelableActivity extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcelable);

        textView = findViewById(R.id.text_parcelable_get);
        Intent intent = getIntent();

        ExamineByParcelable exam = intent.getParcelableExtra("Examine");
        textView.setText(exam.toString());
    }
}
