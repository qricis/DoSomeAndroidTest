package com.example.litepaltest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.litepaltest.R;
import com.example.litepaltest.data.Book;

import org.litepal.LitePal;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void createDatabase(View view) {

        LitePal.getDatabase();
        Toast.makeText(this,"数据库创建成功",Toast.LENGTH_SHORT).show();

    }

    public void addDatabase(View view) {

        Book book = new Book();
        book.setName("The Da Vinci Code");
        book.setAuthor("Dan Brown");
        book.setPages(454);
        book.setPrice(19.69);
        book.setPress("Unknown");
        book.save();

    }

    public void updateDatabase(View view) {

        Book book = new Book();

        //第一种方式
        book.setName("The Lost Symbol");
        book.setAuthor("Dan Brown");
        book.setPages(510);
        book.setPrice(19.95);
        book.setPress("Unknown");
        book.save();
        book.setPrice(20.25);
        book.save();
        book.clearSavedState();

        //第二种方式
        Book book1 = LitePal.find(Book.class,1);
        book1.setPrice(18.88);
        book1.save();

        //第三种方式
        Book book2 = new Book();
        book2.setPrice(28.88);
        book2.update(1);
        book2.updateAll("name = ? and author = ?","The Lost Symbol","Dan Brown");

        //第四种方式，值设置成默认值
        Book book3 = new Book();
        book3.setToDefault("pages");
        book3.updateAll();
    }

    public void deleteDatabase(View view) {

        //第一种方式
        LitePal.delete(Book.class,2);

        //第二种方式
        LitePal.deleteAll(Book.class,"id = ?","2");

    }

    public void queryDatabase(View view) {

        //第一种方式
        List<Book> books = LitePal.findAll(Book.class);
        for (Book book: books) {
            Log.d("MainActivity","book name is " + book.getName());
            Log.d("MainActivity","book Author is " + book.getAuthor());
            Log.d("MainActivity","book pages is " + book.getPages());
            Log.d("MainActivity","book price is " + book.getPrice());
            Log.d("MainActivity","book press is " + book.getPress());
        }

        //第二种方式
        Book book = LitePal.find(Book.class,1);
        Log.d("MainActivity","book name is " + book.getName());
        Log.d("MainActivity","book Author is " + book.getAuthor());
        Log.d("MainActivity","book pages is " + book.getPages());
        Log.d("MainActivity","book price is " + book.getPrice());
        Log.d("MainActivity","book press is " + book.getPress());

        //第三种方式
        List<Book> books1 = LitePal.where("author = ?","Dan Brown").order("price").find(Book.class);
        for (Book book1: books1) {
            Log.d("MainActivity","book name is " + book1.getName());
            Log.d("MainActivity","book Author is " + book1.getAuthor());
            Log.d("MainActivity","book pages is " + book1.getPages());
            Log.d("MainActivity","book price is " + book1.getPrice());
            Log.d("MainActivity","book press is " + book1.getPress());
        }
    }
}