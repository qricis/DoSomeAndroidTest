package com.example.transfer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class ForResultActivity extends Activity {

    private EditText editText;
    private static final int REQUESTCODE_1 = 0x1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_result);

        editText = findViewById(R.id.text_input_num);
    }

    //重写该方法处理返回的结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUESTCODE_1&&resultCode==RESULT_OK) {
            assert data != null;
            String item = data.getStringExtra("item");
            editText.setText(item);
        }
    }


    public void chooseNum(View view) {
        Intent intent = new Intent(this,ForSendActivity.class);
        //除了startActivity外唯二的启动Activity的方法，该方法可以返回结果。
        startActivityForResult(intent,REQUESTCODE_1);

    }

    public void submitNum(View view) {


        //Intent intent = new Intent(this,MainActivity.class);
        Toast.makeText(this,"正在拨打"+editText.getText().toString(),Toast.LENGTH_SHORT).show();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                String number = editText.getText().toString();
                //Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+number));
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+number));
                startActivity(intent);
            }
        };
        timer.schedule(timerTask,2500);
    }
}
