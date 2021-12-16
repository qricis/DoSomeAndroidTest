package com.example.servicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private MyService.DownloadBinder mDownloadBinder;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mDownloadBinder = (MyService.DownloadBinder) iBinder;
            mDownloadBinder.startDownload();
            mDownloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void startService(View view) {

        Intent intent = new Intent(this,MyService.class);
        startService(intent);

    }

    public void stopService(View view) {

        Intent intent = new Intent(this,MyService.class);
        stopService(intent);

    }

    public void bindService(View view) {

        Intent intent = new Intent(this,MyService.class);
        //绑定服务
        bindService(intent,mConnection,BIND_AUTO_CREATE);

    }

    public void unbindService(View view) {

        //解绑服务
        unbindService(mConnection);
    }

    public void startIntentService(View view) {

        Log.d("MainActivity","Thread id is " + Thread.currentThread().getId());
        Intent intent = new Intent(this,MyIntentService.class);
        startService(intent);

    }
}