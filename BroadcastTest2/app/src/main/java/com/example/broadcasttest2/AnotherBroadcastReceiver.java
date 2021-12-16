package com.example.broadcasttest2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Description
 * <p>
 *
 * @author qricis on 2020/8/18 10:22
 * @version 1.0.0
 */
public class AnotherBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

            Toast.makeText(context,"received in AnotherBroadcastReceiver",Toast.LENGTH_SHORT).show();

    }
}
