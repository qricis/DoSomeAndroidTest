package com.android.myviewpager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

/**
 * Description
 * <p>
 *
 * @author qricis on 2021/2/19 10:30
 * @version 1.0.0
 */
public class OneFragment extends Fragment implements View.OnClickListener {

    private TextView tvItemOne;
    private TextView tvItemTwo;
    private TextView tvItemThree;

    private ViewPager mViewPager;
    private List<Fragment> mFragments;
    private MyFragmentPagerAdapter mMyFragmentPagerAdapter;

    @Override
    public void onAttach(@NonNull Context context) {
        Log.d("OneFragment", "qricis onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("OneFragment", "qricis onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        Log.d("OneFragment", "qricis onCreateView");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("OneFragment", "qricis onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("OneFragment", "qricis onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("OneFragment", "qricis onResume");

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
        tvItemOne = getView().findViewById(R.id.fragment_text_item_one);
        tvItemTwo = getView().findViewById(R.id.fragment_text_item_two);
        tvItemThree = getView().findViewById(R.id.fragment_text_item_three);
        mViewPager = getView().findViewById(R.id.fragment_view_pager);
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
        mFragments.add(new ChildFragmentOne());
        mFragments.add(new ChildFragmentTwo());
        mFragments.add(new ChildFragmentThree());

        mMyFragmentPagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mFragments);
        mViewPager.setAdapter(mMyFragmentPagerAdapter);
        //初始化显示第一个界面
        mViewPager.setCurrentItem(0);
        initColor();
    }

    /**
     * 初始化所有text的颜色
     */
    private void initColor() {
        tvItemOne.setTextColor(getActivity().getColor(R.color.purple_500));
        tvItemTwo.setTextColor(getActivity().getColor(R.color.black));
        tvItemThree.setTextColor(getActivity().getColor(R.color.black));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_text_item_one:
                mViewPager.setCurrentItem(0);
                tvItemOne.setTextColor(getActivity().getColor(R.color.purple_500));
                tvItemTwo.setTextColor(getActivity().getColor(R.color.black));
                tvItemThree.setTextColor(getActivity().getColor(R.color.black));
                break;
            case R.id.fragment_text_item_two:
                mViewPager.setCurrentItem(1);
                tvItemOne.setTextColor(getActivity().getColor(R.color.black));
                tvItemTwo.setTextColor(getActivity().getColor(R.color.purple_500));
                tvItemThree.setTextColor(getActivity().getColor(R.color.black));
                break;
            case R.id.fragment_text_item_three:
                mViewPager.setCurrentItem(2);
                tvItemOne.setTextColor(getActivity().getColor(R.color.black));
                tvItemTwo.setTextColor(getActivity().getColor(R.color.black));
                tvItemThree.setTextColor(getActivity().getColor(R.color.purple_500));
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
                    tvItemOne.setTextColor(getActivity().getColor(R.color.purple_500));
                    tvItemTwo.setTextColor(getActivity().getColor(R.color.black));
                    tvItemThree.setTextColor(getActivity().getColor(R.color.black));
                    break;
                case 1:
                    tvItemOne.setTextColor(getActivity().getColor(R.color.black));
                    tvItemTwo.setTextColor(getActivity().getColor(R.color.purple_500));
                    tvItemThree.setTextColor(getActivity().getColor(R.color.black));
                    break;
                case 2:
                    tvItemOne.setTextColor(getActivity().getColor(R.color.black));
                    tvItemTwo.setTextColor(getActivity().getColor(R.color.black));
                    tvItemThree.setTextColor(getActivity().getColor(R.color.purple_500));
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("OneFragment", "qricis onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("OneFragment", "qricis onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("OneFragment", "qricis onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("OneFragment", "qricis onDestroy");
    }

}
