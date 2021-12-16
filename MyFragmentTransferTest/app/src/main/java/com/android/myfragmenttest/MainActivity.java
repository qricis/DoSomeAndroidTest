package com.android.myfragmenttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

/**
 * Description
 * <p>
 *
 * @author qricis on 2021/1/25 16:11
 * @version 1.0.0
 */
public class MainActivity extends AppCompatActivity implements MyFragmentListener {
    private Fragment mLeftFragment;
    private Fragment mRightFragment;
    private Fragment mAnotherRightFragment;
    private boolean isRightFragment = false;
    private boolean isAnotherRightFragment = false;
    private final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

//        displayFragment(R.id.left_frame_layout, mLeftFragment);
//        displayFragment(R.id.right_frame_layout, mRightFragment);
//        isRightFragment = true;
//
//        setNewText(mRightFragment, "right! right! right!");
//        setNewText(mLeftFragment, "left! left! left!");

        checkMaxLifecycle();

        new Handler(Looper.myLooper()).postDelayed(() -> {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setMaxLifecycle(mLeftFragment, Lifecycle.State.DESTROYED);
//            fragmentTransaction.setMaxLifecycle(mLeftFragment, Lifecycle.State.STARTED);
            fragmentTransaction.commit();
        }, 500);


    }

    /**
     * 初始化
     */
    private void init() {
        mLeftFragment = new LeftFragment();
        mRightFragment = new RightFragment();
    }

    private void checkMaxLifecycle() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.left_frame_layout,mLeftFragment);
        transaction.commitNowAllowingStateLoss();
    }

    /**
     * @param fragment 给谁传参
     * @param str      传的字符串参数
     */
    private void setNewText(Fragment fragment, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(fragment.getClass().getSimpleName(), str);
        fragment.setArguments(bundle);
    }

    /**
     * 初始化左右屏显示内容
     *
     * @param id       显示的容器号
     * @param fragment 显示的fragment
     */
    private void displayFragment(int id, Fragment fragment) {
        //开启一个事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragmentManager.getFragments() != null) {
            transaction.add(id, fragment);
        } else {
            transaction.replace(id, fragment);
        }
        transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.commitAllowingStateLoss();
    }

    /**
     * @param showFragment 需要显示的Fragment
     * @param hideFragment 需要隐藏的Fragment
     */
    private void showHideFragment(Fragment showFragment, Fragment hideFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        //开启一个事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.show(showFragment);
        transaction.hide(hideFragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void whatFragmentSay(String str) {
        Toast.makeText(this, "You say that " + str, Toast.LENGTH_LONG).show();
    }

    @Override
    public void changeRightFragment() {
        if (mAnotherRightFragment == null) {
            mAnotherRightFragment = ((RightFragment) mRightFragment).changeFragment();
            displayFragment(R.id.right_frame_layout, mAnotherRightFragment);
            isAnotherRightFragment = true;
            showBackStack();
        } else if (isRightFragment && !isAnotherRightFragment) {
            showHideFragment(mAnotherRightFragment, mRightFragment);
            isRightFragment = false;
            isAnotherRightFragment = true;
        } else {
            showHideFragment(mRightFragment, mAnotherRightFragment);
            isRightFragment = true;
            isAnotherRightFragment = false;
        }
    }

    /**
     * 每点击一次按钮 调用一次
     * 显示回退栈里的fragment
     */
    private void showBackStack() {
        new Handler(Looper.myLooper()).postDelayed(() -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            for (Fragment fragment : fragmentManager.getFragments()) {
                Log.e("yubo", "fragment = " + fragment);
            }

            Log.e("yubo", "getBackStackEntryCount = " + fragmentManager.getBackStackEntryCount());
        }, 500);
    }

}
