package com.example.transfer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ForSendActivity extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        listView = findViewById(R.id.list_send);

        final String[] items = {"18392601541","18209293348","18392554352","15389269620",
                                "13882556323","15956984125","13954268752"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String >(
               this,android.R.layout.simple_list_item_1,android.R.id.text1,items
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String item = items[position];
                Intent intent = new Intent();
                intent.putExtra("item",item);
                setResult(RESULT_OK,intent);//设置返回结果
                finish();//结束当前Activty
            }
        });
    }
}
