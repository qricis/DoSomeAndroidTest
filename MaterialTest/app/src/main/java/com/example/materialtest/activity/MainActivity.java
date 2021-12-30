package com.example.materialtest.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.materialtest.R;
import com.example.materialtest.adapter.FruitAdapter;
import com.example.materialtest.data.Fruit;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private Fruit[] mFruits = {new Fruit("Apple",R.drawable.apple),new Fruit("Cherry",R.drawable.cherry),
            new Fruit("Orange",R.drawable.orange),new Fruit("Pear",R.drawable.pear),
            new Fruit("Pineapple",R.drawable.pineapple),new Fruit("Strawberry",R.drawable.strawberry),
            new Fruit("Watermelon",R.drawable.watermelon),new Fruit("Mango",R.drawable.mango),
            new Fruit("Banana",R.drawable.banana),new Fruit("Grape",R.drawable.grape)};

    private List<Fruit> mFruitList = new ArrayList<>();

    private FruitAdapter mFruitAdapter;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //得到DrawerLayout实例
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        //得到ActionBar实例，具体实现由ToolBar来完成
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //让导航按钮显示出来
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置一个导航按钮图标，将图片传进去
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                //Toast.makeText(MainActivity.this,"This is nav",Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        //实现RecyclerView
        //初始化List<Fruit>数组
        initFruits();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        //定义布局方式 LinearLayoutManager直接是线性布局
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        //瀑布流布局，实现交错式布局，第一个参数是列数，第二个参数是显示方向
        //StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        //不设置的话，图片闪烁错位，有可能有整列错位的情况。
        //layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

        //GridLayoutManager是网格布局 第一个参数为context，第二个参数为列数
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        mFruitAdapter = new FruitAdapter(mFruitList);
        recyclerView.setAdapter(mFruitAdapter);

        //下拉刷新
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruit();
            }

        });
    }

    /**
     * 下拉刷新实现
     * */
    private void refreshFruit() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        mFruitAdapter.notifyDataSetChanged();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();

    }

    private void initFruits(){
        mFruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(mFruits.length);
            mFruitList.add(mFruits[index]);
        }
    }

    //加载了toolbar.xml菜单文件
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    //处理各个按钮的点击事件
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            //HomeAsUp的图标的id永远是这一个
            case android.R.id.home:
                //调用该方法将菜单展示出来，传入的参数是Gravity参数
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this,"You clicked backup",Toast.LENGTH_SHORT).show();
                //能响应点击事件
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.delete:
                Toast.makeText(this,"You clicked delete",Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this,"You clicked settings",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    public void fabClick(View view) {

        Snackbar.make(view,"Data deleted",Snackbar.LENGTH_LONG)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"Data Restored",Toast.LENGTH_SHORT).show();
                    }
                }).show();
        //Toast.makeText(this,"You clicked FloatingActionButton",Toast.LENGTH_SHORT).show();

    }
}