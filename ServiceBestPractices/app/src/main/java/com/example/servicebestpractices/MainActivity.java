package com.example.servicebestpractices;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DownloadService.DownloadBinder mDownloadBinder;

    //创建一个ServiceConnection的匿名类
    private ServiceConnection mConnection = new ServiceConnection() {
        //获取DownloadBinder实例，用于在活动中调用服务提供的各种方法
        //将活动与服务绑定
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mDownloadBinder = (DownloadService.DownloadBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //调用startService和bindService来启动和绑定服务
        //启动服务让DownloadService一直在后台运行
        //绑定服务让MainActivity和DownloadService进行通信
        Intent intent = new Intent(this,DownloadService.class);
        startService(intent);
        bindService(intent,mConnection,BIND_AUTO_CREATE);

        //进行权限申请
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void startDownload(View view) {

        if (mDownloadBinder == null){
            return;
        }

        String url = "https://raw.githubusercontent.com/guolindev/eclipse/master/eclipse-inst-win64.exe";
        mDownloadBinder.startDownload(url);

    }

    public void pauseDownload(View view) {
        mDownloadBinder.pauseDownload();
    }

    public void cancelDownload(View view) {
        mDownloadBinder.cancelDownload();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"拒绝权限将无法使用程序",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }
}