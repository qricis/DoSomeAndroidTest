package com.example.broadcasttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IntentFilter mIntentFilter;
    private NetworkChangeReceiver mNetworkChangeReceiver;

    private LocalReceiver mLocalReceiver;
    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        mNetworkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(mNetworkChangeReceiver,mIntentFilter);

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);

        mIntentFilter.addAction("com.example.broadcasttest.LOCAL_BROADCAST");
        mLocalReceiver = new LocalReceiver();
        //注册本地广播监听器
        mLocalBroadcastManager.registerReceiver(mLocalReceiver,mIntentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mNetworkChangeReceiver);
        mLocalBroadcastManager.unregisterReceiver(mLocalReceiver);
    }

    public void buttonToBroadcast(View view) {
//        Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");
//        intent.setComponent(new ComponentName("com.example.broadcasttest","com.example.broadcasttest.MyBroadcastReceiver"));
//        sendBroadcast(intent);

        /*//设置这个广播的action，只有和这个action一样的接收者才能接收广播
        Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");
        sendBroadcast(intent,"com.example.broadcasttest.MY_BROADCAST");
*/
       /* //设置这个广播的action，只有和这个action一样的接收者才能接收广播
        Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");
        sendOrderedBroadcast(intent,"com.example.broadcasttest.MY_BROADCAST");
*/

       Intent intent = new Intent("com.example.broadcasttest.LOCAL_BROADCAST");
       mLocalBroadcastManager.sendBroadcast(intent);

    }

    class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context,"network is available",Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(context,"network is unavailable",Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(context,"network changes",Toast.LENGTH_SHORT).show();
        }
    }

    class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"received local broadcast",Toast.LENGTH_SHORT).show();
        }
    }
}