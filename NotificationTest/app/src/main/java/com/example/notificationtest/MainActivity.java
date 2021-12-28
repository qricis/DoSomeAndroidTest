package com.example.notificationtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import java.io.File;

/**
 * @author xiqi
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendNotice(View view) {

        //用于实现点击通知跳转
        Intent intent = new Intent(this,NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = null;

        //以下两者都可以显示通知
        //但是必须设置channelId
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            //构建NotificationChannel实例
            NotificationChannel notificationChannel = new NotificationChannel("1","my_channel", NotificationManager.IMPORTANCE_LOW);

            //设置通知出现时的闪光灯
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.GREEN);

            //设置通知出现时的震动
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{0,1000,1000,1000});

            notificationChannel.setSound(Uri.fromFile(new File("/system/media/audio/notifications/DearDeer.ogg")),Notification.AUDIO_ATTRIBUTES_DEFAULT);
            //在notificationManager中创建通知渠道
            manager.createNotificationChannel(notificationChannel);

            notification = new Notification.Builder(this)
                    .setContentTitle("This is content title")
                    .setContentText("Learn how to build notifications,send and sync data,and use voice actions.Get the official Android IDE and developer tools to build apps for Android")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                    .setChannelId("1")
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setStyle(new Notification.BigTextStyle().bigText("Learn how to build notifications,send and sync data,and use voice actions.Get the official Android IDE and developer tools to build apps for Android"))
                    .setStyle(new Notification.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.head)))
                    .setPriority(Notification.PRIORITY_MAX)
                    .build();

        } else {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,"001")
                    .setContentTitle("This is content title")
                    .setContentText("This is content text")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                    //用于设置版本号
                    .setChannelId("1")
                    //用于点击跳转
                    .setContentIntent(pendingIntent)
                    //用于通知点击后自动消失
                    .setAutoCancel(true)
                    //接收一个Uri参数，在通知发出的时候播放一段音频
                    .setSound(Uri.fromFile(new File("/system/media/audio/notifications/DearDeer.ogg")))
                    //设置震动(静止时长，振动时长，静止时长，振动时长)，但需要申请权限
                    .setVibrate(new long[]{0,1000,1000,1000})
                    //设置锁屏时闪烁灯(颜色，亮起时长，暗去时长)
                    .setLights(Color.GREEN,1000,1000)
                    //直接使用默认效果
                    .setDefaults(Notification.DEFAULT_ALL)
                    //将通知内容显示完整
                    .setStyle(new NotificationCompat.BigTextStyle().bigText("Learn how to build notifications,send and sync data,and use voice actions.Get the official Android IDE and developer tools to build apps for Android"))
                    //通知里显示一张大图片
                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background)))
                    //设置通知的重要程度，5个常量可选(_DEFAULT/_MIN/_LOW/_HIGH/_MAX)
                    //默认和不设置效果一样，最低只会在特定场景显示，较低会将这类通知缩小或改变显示顺序，较高将之放大或改变顺序，最高让用户立刻看到，甚至做出响应操作
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    ;
            Log.d("TAG", "sendNotice: 2222222222222222222");
            //第二种写法
//            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,"001")
//                    .setCustomBigContentView(new RemoteViews(getPackageName(),R.layout.notification_layout))
//                    ;
            notification = notificationBuilder.build();

        }
        manager.notify(1,notification);
    }

}