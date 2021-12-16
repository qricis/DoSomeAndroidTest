package com.android.myfragmenttest;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

/**
 * Description
 * <p>
 *
 * @author qricis on 2021/1/25 16:12
 * @version 1.0.0
 */
public class LeftFragment extends Fragment {

    private static final String TAG = "LeftFragment";

    private TextView mTextView;
    private Button mButton;
    private MyFragmentListener mMyFragmentListener;

    @Override
    public void onAttach(@NonNull Context context) {
        mMyFragmentListener = (MyFragmentListener) context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Qricis onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"Qricis onCreateView");
        View view = inflater.inflate(R.layout.fragment_left, container, false);
        mTextView = view.findViewById(R.id.left_fragment_textView);
        mButton = view.findViewById(R.id.left_fragment_button);
        checkBundle();
        mMyFragmentListener.whatFragmentSay("I am a left fragment");
        mButton.setOnClickListener(v -> {
            mMyFragmentListener.changeRightFragment();
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG,"Qricis onViewCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"Qricis onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"Qricis onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"Qricis onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"Qricis onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG,"Qricis onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Qricis onDestroy");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    /**
     * activity是否有传递数据
     */
    private void checkBundle() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String str = bundle.getString(this.getClass().getSimpleName());
            mTextView.setText(str);
        }
    }
}
