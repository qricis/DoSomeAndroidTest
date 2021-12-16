package com.android.myfragmenttest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Description
 * <p>
 *
 * @author qricis on 2021/2/1 13:33
 * @version 1.0.0
 */
public class RightFragmentTwo extends Fragment {

    private static final String KEY = "CHANGE_FRAGMENT_TO_MYSELF";

    private TextView mTextView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right_two, container, false);
        mTextView = view.findViewById(R.id.right_fragment_two_textView);
        checkBundle();

        return view;
    }

    /**
     * activity是否有传递数据
     */
    private void checkBundle() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String str = bundle.getString(KEY);
            mTextView.setText(str);
        }
    }

    /**
     * @param str 用来传入需要显示的字符串
     * @return 返回一个fragment
     */
    public static Fragment newInstance(String str) {
        Fragment fragment = new RightFragmentTwo();
        Bundle bundle = new Bundle();
        bundle.putString(KEY, str);
        fragment.setArguments(bundle);
        return fragment;
    }
}
