package com.android.myviewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

/**
 * Description
 * <p>
 *
 * @author qricis on 2021/2/19 10:27
 * @version 1.0.0
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvItemOne;
    private TextView tvItemTwo;
    private TextView tvItemThree;

    private ViewPager mViewPager;
    private List<Fragment> mFragments;
    private MyFragmentPagerAdapter mMyFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        initView();
        //初始化菜单点击事件
        initListener();
        //初始化fragment数组
        initFragment();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        tvItemOne = findViewById(R.id.text_item_one);
        tvItemTwo = findViewById(R.id.text_item_two);
        tvItemThree = findViewById(R.id.text_item_three);
        mViewPager = findViewById(R.id.view_pager);
    }

    /**
     * 初始化菜单栏点击事件
     */
    private void initListener() {
        tvItemOne.setOnClickListener(this);
        tvItemTwo.setOnClickListener(this);
        tvItemThree.setOnClickListener(this);
        mViewPager.addOnPageChangeListener(new MyPagerChangeListener());
    }

    /**
     * 初始化fragmentList
     */
    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new OneFragment());
        mFragments.add(new TwoFragment());
        mFragments.add(new ThreeFragment());

        mMyFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mFragments);
        mViewPager.setAdapter(mMyFragmentPagerAdapter);
        //初始化显示第一个界面
        mViewPager.setCurrentItem(0);
        initColor();
    }

    /**
     * 初始化所有text的颜色
     */
    private void initColor() {
        tvItemOne.setTextColor(getColor(R.color.purple_200));
        tvItemTwo.setTextColor(getColor(R.color.black));
        tvItemThree.setTextColor(getColor(R.color.black));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_item_one:
                mViewPager.setCurrentItem(0);
                tvItemOne.setTextColor(getColor(R.color.purple_200));
                tvItemTwo.setTextColor(getColor(R.color.black));
                tvItemThree.setTextColor(getColor(R.color.black));
                break;
            case R.id.text_item_two:
                mViewPager.setCurrentItem(1);
                tvItemOne.setTextColor(getColor(R.color.black));
                tvItemTwo.setTextColor(getColor(R.color.purple_200));
                tvItemThree.setTextColor(getColor(R.color.black));
                break;
            case R.id.text_item_three:
                mViewPager.setCurrentItem(2);
                tvItemOne.setTextColor(getColor(R.color.black));
                tvItemTwo.setTextColor(getColor(R.color.black));
                tvItemThree.setTextColor(getColor(R.color.purple_200));
                break;
            default:
                break;
        }
    }

    public class MyPagerChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //当页面左右滑动的时候 改变菜单栏的颜色
            switch (position) {
                case 0:
                    tvItemOne.setTextColor(getColor(R.color.purple_200));
                    tvItemTwo.setTextColor(getColor(R.color.black));
                    tvItemThree.setTextColor(getColor(R.color.black));
                    break;
                case 1:
                    tvItemOne.setTextColor(getColor(R.color.black));
                    tvItemTwo.setTextColor(getColor(R.color.purple_200));
                    tvItemThree.setTextColor(getColor(R.color.black));
                    break;
                case 2:
                    tvItemOne.setTextColor(getColor(R.color.black));
                    tvItemTwo.setTextColor(getColor(R.color.black));
                    tvItemThree.setTextColor(getColor(R.color.purple_200));
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}