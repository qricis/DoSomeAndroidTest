package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.contentprovider.data.MyDatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper mMyDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMyDatabaseHelper = new MyDatabaseHelper(this,"BookStore.db",null,2);

    }

    public void createDatabase(View view) {
        mMyDatabaseHelper.getWritableDatabase();
    }

    public void addData(View view) {

        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        //开始组装第一条数据
        values.put("name","The Da Vinci Code");
        values.put("author","Dan Brown");
        values.put("pages",454);
        values.put("price",16.96);
        //插入第一条数据
        db.insert("Book",null,values);
        values.clear();

        //插入第二条数据
        values.put("name","The Lost Symbol");
        values.put("author","Dan Brown");
        values.put("pages",510);
        values.put("price",19.95);
        //插入第一条数据
        db.insert("Book",null,values);
        values.clear();

        //db.execSQL("insert into Book (name,author,pages,price) values(?,?,?,?)",new String[] {"The Da Vinci Code","Dan Brown","454","44.23"});

    }

    public void updateData(View view) {

        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("price",29.89);

        db.update("Book",values,"name = ?",new String[]{"The Da Vinci Code"});
        values.clear();

        //db.execSQL("update Book set price = ? where name = ?",new String[] {"29.9","The Da Vinci Code"});
    }

    public void deleteData(View view) {

        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();

        db.delete("Book","id > ?",new String[]{"2"});

        //db.execSQL("delete from Book where id > ?",new String[] {"2"});

    }

    public void queryData(View view) {

        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();

        //查询Book表中的所有数据
        Cursor cursor = db.query("Book",null,null,null,null,null,null);

        //Cursor cursor = db.rawQuery("select * from Book",null);

        if (cursor.moveToFirst()) {
            do {
                //遍历Cuosor对象，取出数据并打印
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));

                Log.d("MainActivity","book name is " + name);
                Log.d("MainActivity","book author is " + author);
                Log.d("MainActivity","book pages is " + pages);
                Log.d("MainActivity","book price is " + price);
            }while (cursor.moveToNext());
        }
        cursor.close();

    }
}