package com.example.recyclerviewtest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.example.recyclerviewtest.R;
import com.example.recyclerviewtest.adapter.FruitAdapterClick;
import com.example.recyclerviewtest.adapter.FruitAdapterHorizontal;
import com.example.recyclerviewtest.adapter.FruitAdapterVertical;
import com.example.recyclerviewtest.adapter.FruitAdapterWaterfall;
import com.example.recyclerviewtest.data.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
* Description
* <p>
*
*
* @author qricis on 2020/8/13 13:50
* @version 1.0.0
*/
public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();

    /**
     * 竖向滚动
     * */
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFruits();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
    }*/

    /**
     * 横向滚动
     * */
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFruits();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapterHorizontal adapter = new FruitAdapterHorizontal(fruitList);
        recyclerView.setAdapter(adapter);
    }*/

    /*private void initFruits(){

        for (int i = 0; i <4; i++){

            Fruit apple = new Fruit("Apple",R.drawable.apple);
            fruitList.add(apple);

            Fruit banana = new Fruit("Banana",R.drawable.banana);
            fruitList.add(banana);

            Fruit orange = new Fruit("Orange",R.drawable.orange);
            fruitList.add(orange);

            Fruit watermelon = new Fruit("Watermelon",R.drawable.watermelon);
            fruitList.add(watermelon);

            Fruit pear = new Fruit("Pear",R.drawable.pear);
            fruitList.add(pear);

            Fruit grape = new Fruit("Grape",R.drawable.grape);
            fruitList.add(grape);

            Fruit pineapple = new Fruit("Pineapple",R.drawable.pineapple);
            fruitList.add(pineapple);

            Fruit strawberry = new Fruit("Strawberry",R.drawable.strawberry);
            fruitList.add(strawberry);

            Fruit cherry = new Fruit("Cherry",R.drawable.cherry);
            fruitList.add(cherry);

            Fruit mango = new Fruit("Mango",R.drawable.mango);
            fruitList.add(mango);

        }

    }*/

    /**
     * 瀑布滚动
     * *//*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFruits();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        FruitAdapterWaterfall adapter = new FruitAdapterWaterfall(fruitList);
        recyclerView.setAdapter(adapter);
    }*/

    /**
     * 点击事件
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFruits();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        FruitAdapterClick adapter = new FruitAdapterClick(fruitList);
        recyclerView.setAdapter(adapter);
    }

    private void initFruits(){

        for (int i = 0; i <5; i++){

            Fruit apple = new Fruit(getRandomLengthName("Apple"),R.drawable.apple);
            fruitList.add(apple);

            Fruit banana = new Fruit(getRandomLengthName("Banana"),R.drawable.banana);
            fruitList.add(banana);

            Fruit orange = new Fruit(getRandomLengthName("Orange"),R.drawable.orange);
            fruitList.add(orange);

            Fruit watermelon = new Fruit(getRandomLengthName("Watermelon"),R.drawable.watermelon);
            fruitList.add(watermelon);

            Fruit pear = new Fruit(getRandomLengthName("Pear"),R.drawable.pear);
            fruitList.add(pear);

            Fruit grape = new Fruit(getRandomLengthName("Grape"),R.drawable.grape);
            fruitList.add(grape);

            Fruit pineapple = new Fruit(getRandomLengthName("Pineapple"),R.drawable.pineapple);
            fruitList.add(pineapple);

            Fruit strawberry = new Fruit(getRandomLengthName("Strawberry"),R.drawable.strawberry);
            fruitList.add(strawberry);

            Fruit cherry = new Fruit(getRandomLengthName("Cherry"),R.drawable.cherry);
            fruitList.add(cherry);

            Fruit mango = new Fruit(getRandomLengthName("Mango"),R.drawable.mango);
            fruitList.add(mango);

        }
    }

    private String getRandomLengthName(String name) {
        Random random = new Random();
        int length = random.nextInt(30) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(name);
        }

        return builder.toString();
    }
}