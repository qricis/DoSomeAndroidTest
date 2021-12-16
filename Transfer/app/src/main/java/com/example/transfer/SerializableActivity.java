package com.example.transfer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.data.ExamineBySerializable;

public class SerializableActivity extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serializable);

        textView = findViewById(R.id.text_serializable_get);
        Intent intent = getIntent();

        ExamineBySerializable exam = (ExamineBySerializable)intent.getSerializableExtra("Examine");

        textView.setText(exam.toString());
    }
}
