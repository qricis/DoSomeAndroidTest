package com.android.myviewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Description
 * <p>
 *
 * @author qricis on 2021/2/19 10:35
 * @version 1.0.0
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragments;

    public MyFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Fragment> fragments) {
        super(fm, behavior);
        mFragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
