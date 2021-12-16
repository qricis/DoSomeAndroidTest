package com.android.myfragmenttest;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Description
 * <p>
 *
 * @author qricis on 2021/1/25 16:12
 * @version 1.0.0
 */
public class RightFragment extends Fragment {

    private static final String TAG = "rightFragment";

    private TextView mTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Qricis onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right, container, false);
        Log.d(TAG,"Qricis onCreateView");
        mTextView = view.findViewById(R.id.right_fragment_textView);
        checkBundle();

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

    /**
     * @return 返回了一个Fragment
     */
    public Fragment changeFragment() {

        return RightFragmentTwo.newInstance("I am another right fragment");
    }
}
