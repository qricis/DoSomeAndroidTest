package com.example.intentcallback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void toReturnData(View view) {

        Intent intent = new Intent();
        intent.putExtra("data_return","this is data that return to you");
        setResult(RESULT_OK,intent);
        finish();

    }

    /*
    * 按返回键回调数据
    * */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("data_return","This is data that you want");
        setResult(RESULT_OK,intent);
        finish();
    }
}
