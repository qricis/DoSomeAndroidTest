package com.example.transfer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SimpleActivity extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        textView = findViewById(R.id.text_simple_get);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        assert bundle != null;
        String info = bundle.getString("myInfo");

        //String info = intent.getStringExtra("myInfo");

        textView.setText(info);
    }

}
