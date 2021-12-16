package com.android.myviewpager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Description
 * <p>
 *
 * @author qricis on 2021/2/19 10:30
 * @version 1.0.0
 */
public class TwoFragment extends Fragment {

    @Override
    public void onAttach(@NonNull Context context) {
        Log.d("TwoFragment","qricis onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TwoFragment","qricis onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two,container,false);
        Log.d("TwoFragment","qricis onCreateView");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("TwoFragment","qricis onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("TwoFragment","qricis onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TwoFragment","qricis onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("TwoFragment","qricis onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("TwoFragment","qricis onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("TwoFragment","qricis onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TwoFragment","qricis onDestroy");
    }

}
