package com.example.servicetest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class MyService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();

    class DownloadBinder extends Binder {
        public void startDownload(){
            Log.d("MyService","startDownload executed");
        }

        public int getProgress(){
            Log.d("MyService","getProgress executed");
            return 0;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService","onCreate executed");

        //这里的id里面输入自己的项目的包的路径
        String ID = "com.example.servicetest";
        String NAME = "Channel One";

        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        //创建服务对象
        NotificationCompat.Builder notification;
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //设置channel
            NotificationChannel channel = new NotificationChannel(ID, NAME, manager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.setShowBadge(true);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            manager.createNotificationChannel(channel);
            //设置channelId
            notification = new NotificationCompat.Builder(this).setChannelId(ID);
        } else {
            notification = new NotificationCompat.Builder(this);
        }
        //设置通知的其他显示信息
        notification.setContentTitle("标题")
                .setContentText("内容")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)
                .build();

        Notification notification1 = notification.build();
        startForeground(1,notification1);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService","onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService","onDestroy executed");
    }

}
