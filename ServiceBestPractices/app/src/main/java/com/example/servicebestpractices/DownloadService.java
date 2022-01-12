package com.example.servicebestpractices;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.io.File;

/**
 * Description
 * <p>
 * 为保证DownloadTask一直在后台运行，创建一个下载的服务类
 * @author qricis on 2020/8/31 14：12
 * @version 1.0.0
 */
public class DownloadService extends Service {

    private DownloadTask mDownloadTask;
    private String downloadUrl;

    /**
     * 创建了DownloaderListener的匿名类实例，并实现了五个方法
     * 调用getNotification方法构建了一个用于显示下载的通知
     * 方法中调用了NotificationManager的notify()方法去触发这个通知
     * */
    private DownloadListener mDownloadListener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1,getNotification("Downloading...",progress));
        }

        @Override
        public void onSuccess() {

            mDownloadTask = null;
            //下载成功时。将前台服务通知关闭，并创建一个下载成功的通知
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Download Success",-1));
            Toast.makeText(DownloadService.this,"Download Success",Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onFailed() {

            mDownloadTask = null;
            //下载失败时将前台服务通知关闭，并创建一个下载失败的通知
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Download Failed",-1));
            Toast.makeText(DownloadService.this,"Download Failed",Toast.LENGTH_SHORT).show();


        }

        @Override
        public void onPaused() {

            mDownloadTask = null;
            Toast.makeText(DownloadService.this,"Download Failed",Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCanceled() {

            mDownloadTask = null;
            //下载取消时将前台服务通知关闭
            stopForeground(true);
            Toast.makeText(DownloadService.this,"Canceled",Toast.LENGTH_SHORT).show();

        }
    };

    private DownloadBinder mDownloadBinder = new DownloadBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mDownloadBinder;
    }

    /**
     * 用于使DownloadService可以和活动进行通信
     * */
    class DownloadBinder extends Binder {

        /**
         * 创建了一个DownloadTask实例，把上面的DownloadListener实例作为参数传入
         * 调用execute开启下载，并将下载文件的URL地址传入到execute()方法中
         * 调用startForeground()方法，使之成为一个前台服务
         * */
        @RequiresApi(api = Build.VERSION_CODES.Q)
        public void startDownload(String url) {
            if (mDownloadTask == null) {
                downloadUrl = url;
                mDownloadTask = new DownloadTask(mDownloadListener);
                mDownloadTask.execute(downloadUrl);

                startForeground(1,getNotification("Downloading...",0));
                Toast.makeText(DownloadService.this,"Downlading...",Toast.LENGTH_SHORT).show();

            }
        }

        public void pauseDownload() {
            if (mDownloadTask != null) {
                mDownloadTask.pauseDownload();
            }
        }

        public void cancelDownload() {
            if (mDownloadTask != null) {
                mDownloadTask.canceledDownload();
            } else {
                if (downloadUrl != null) {
                    //取消下载时需将文件删除，并将通知关闭
                    String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                    String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory + fileName);
                    if (file.exists()) {
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
                    Toast.makeText(DownloadService.this,"Canceled",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    /**
     * 返回一个NotificationManager
     * */
    private NotificationManager getNotificationManager() {
        return (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }

    /**
     * 构建一个用于显示下载进度的通知
     * */
    private Notification getNotification(String title, int progress) {

        //这里的id里面输入自己的项目的包的路径
        String ID = "com.example.servicebestpractice";
        String NAME = "Channel One";

        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        //创建服务对象
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //设置channel
            NotificationChannel channel = new NotificationChannel(ID, NAME, manager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.setShowBadge(true);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            manager.createNotificationChannel(channel);
            //设置channelId
            notification.setChannelId(ID);
        }
        //设置通知的其他显示信息
        notification.setContentTitle(title)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent);

        if (progress >= 0) {
            //当progress大于或等于0时才显示下载进度
            notification.setContentText(progress + "%");
            //第一个参数是传入通知的最大进度，第二个参数是传入通知的当前进度，第三个参数表示是否使用模糊进度条
            notification.setProgress(100,progress,false);
        }

        return notification.build();
    }
}
