package com.example.alarmactivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

public class LongRunningService extends Service {
    public LongRunningService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                //...
            }
        }).start();
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //一分钟毫秒数
        int anMinute = 60 * 1000;

        //设定一个任务在10秒钟之后执行
        //使用SystemClock.elapsedRealtime()可以获取到系统开机至今所经历的时间的毫秒数
        //使用System.currentTimeMillis()可以获取到从1970/1/1/0点至今所经历的毫秒数
        long triggerAtTime = SystemClock.elapsedRealtime() + anMinute;

        Intent i = new Intent(this,LongRunningService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this,0,i,0);

        //第一个参数是整型参数，有4种值可以选择，第二个参数是定时任务触发的时间，第三个参数是能够执行服务或广播的PendingIntent
        //ELAPSED_REALTIME表示让定时任务的触发时间从系统开机开始算起，但不会唤醒CPU
        //ELAPSED_REALTIME_WAKEUP同上，但会唤醒CPU
        //RTC表示让定时任务触发从1970/1/1/0点算起，但不会唤醒CPU
        //RTC_WAKEUP同上，但会唤醒CPU
        //在Android4.4之后，set启动变得不准确，用以延长电池使用时间，若想要准时，则setExact()
        //该服务会在6min后再次执行
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pendingIntent);

        return super.onStartCommand(intent, flags, startId);
    }
}
