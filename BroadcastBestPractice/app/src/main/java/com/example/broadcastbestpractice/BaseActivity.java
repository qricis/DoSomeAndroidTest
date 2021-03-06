package com.example.broadcastbestpractice;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Description
 * <p>
 *
 * @author qricis on 2020/8/18 14:30
 * @version 1.0.0
 */
public class BaseActivity extends Activity {

    private static final String TAG = "BaseActivity";

    private ForceOfflineReceiver mForceOfflineReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG,"onCreate");
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
        Toast.makeText(this,"onResume",Toast.LENGTH_SHORT).show();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcastbestpractice.FORCE_OFFLINE");
        mForceOfflineReceiver = new ForceOfflineReceiver();
        registerReceiver(mForceOfflineReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG,"onPause");
        if (mForceOfflineReceiver != null) {
            unregisterReceiver(mForceOfflineReceiver);
            mForceOfflineReceiver = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestory");
        ActivityCollector.removeActivity(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart");
    }

    static class ForceOfflineReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
            Toast.makeText(context,"Broadcast",Toast.LENGTH_SHORT).show();
            //????????????
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("You are forced to be offline. Please try to login again.");
            //????????????????????????
            builder.setCancelable(false);
            //????????????ok?????????
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //??????????????????
                    ActivityCollector.finishAll();
                    //????????????LoginActivity
                    Intent intent = new Intent(context,LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
            builder.show();
        }
    }
}
