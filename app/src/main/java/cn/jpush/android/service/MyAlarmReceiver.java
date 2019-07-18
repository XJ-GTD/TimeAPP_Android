package cn.jpush.android.service;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import com.android.plugins.Permissions;

public class MyAlarmReceiver extends BroadcastReceiver {
    private static final String TAG = "AlarmMyReceiver";
    private Context appContext;

    public MyAlarmReceiver() {
        System.out.println("onReceiveonReceiveonReceiveonReceiveonReceive");
    }

    public void onReceive(Context var1, Intent var2) {
        System.out.println("MyAlarmReceiver" + var2.getAction());
        System.out.println("MyAlarmReceiver" + var1.getApplicationContext());
        Intent intent = new Intent("cn.jpush.android.ui.PushActivity");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

//        if (intent.resolveActivity(var1.getPackageManager()) != null) {
//        }
        this.appContext = var1.getApplicationContext();
        this.appContext.startActivity(intent);
    }
}
