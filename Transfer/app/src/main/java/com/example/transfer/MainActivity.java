package com.example.transfer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;

import com.example.data.ExamineByParcelable;
import com.example.data.ExamineBySerializable;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.text_input);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    /*
    * 通过intent传输基础类型数据
    * */
    public void simpleTransfer(View view) {
        Intent intent = new Intent();
        intent.setClass(this,SimpleActivity.class);

        Bundle bundle = new Bundle();

        String info = editText.getText().toString();
        bundle.putString("myInfo",info);
        intent.putExtra("data",bundle);

        //intent.putExtra("myInfo",info);

        startActivity(intent);
    }

    /*
    * 通过序列化传输对象数据
    * */
     public void serializableTransfer(View view) {
        ExamineBySerializable exam = new ExamineBySerializable();
        exam.setId(1);
        exam.setName("随堂测试1");
        exam.setNumber(123011);
        exam.setType("语文");

        Intent intent = new Intent(this, SerializableActivity.class);
        intent.putExtra("Examine", (Serializable) exam);

        //Toast.makeText(this,"走到了这里",Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void parcelableTransfer(View view) {
        ExamineByParcelable exam = new ExamineByParcelable();
        exam.setId(1);
        exam.setName("随堂测试2");
        exam.setNumber(1658741);
        exam.setType("数学");

        Intent intent = new Intent(this, ParcelableActivity.class);
        intent.putExtra("Examine", (Parcelable) exam);

        //Toast.makeText(this,"走到了这里",Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void turnToForResult(View view) {
         Intent intent = new Intent(this,ForResultActivity.class);

         startActivity(intent);
    }
}