package com.example.providertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //添加数据
    //调用Uri.parse()方法将一个内容URI解析成Uri对象
    //insert()方法会返回一个Uri对象，这个对象中包含了新增数据的id
    public void addButton(View view) {

        Uri uri = Uri.parse("content://com.example.contentprovider.provider/book");
        ContentValues values = new ContentValues();

        values.put("name","A Clash of Kings");
        values.put("author","George Martin");
        values.put("pages",1040);
        values.put("price",22.85);

        Uri newUri = getContentResolver().insert(uri,values);
        newId = newUri.getPathSegments().get(1);

    }

    //查询数据
    public void queryButton(View view) {

        Uri uri = Uri.parse("content://com.example.contentprovider.provider/book");
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));

                Log.d("MainActivity", "book name is " + name);
                Log.d("MainActivity", "book author is " + author);
                Log.d("MainActivity", "book pages is " + pages);
                Log.d("MainActivity", "book price is " + price);
            }
            cursor.close();
        }

    }

    //更新数据
    public void updateButton(View view) {

        Uri uri = Uri.parse("content://com.example.contentprovider.provider/book/" + newId);
        ContentValues values = new ContentValues();

        values.put("name","A Storm of Swords");
        values.put("pages",1216);
        values.put("price",28.98);

        getContentResolver().update(uri,values,null,null);

    }

    //删除数据
    public void deleteButton(View view) {

        Uri uri = Uri.parse("content://com.example.contentprovider.provider/book/" + newId);
        getContentResolver().delete(uri,null,null);

    }
}