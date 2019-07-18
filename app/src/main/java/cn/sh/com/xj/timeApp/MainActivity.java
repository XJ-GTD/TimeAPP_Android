/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package cn.sh.com.xj.timeApp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import cn.jpush.android.ui.PushActivity;
import com.huawei.android.hms.agent.HMSAgent;
import com.huawei.android.hms.agent.common.handler.ConnectHandler;
import com.huawei.android.hms.agent.push.handler.GetTokenHandler;
import com.huawei.hms.support.api.push.TokenResult;
import org.apache.cordova.CordovaActivity;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends CordovaActivity
{

    public MainActivity(){
       System.out.println("MainActivityMainActivityMainActivityMainActivity");
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        HMSAgent.connect(this, new ConnectHandler() {
            @Override
            public void onConnect(int rst) {
                System.out.println("HMS connect end:" + rst);
                HMSAgent.Push.getToken(new GetTokenHandler() {
                    @Override
                    public void onResult(int rst) {
                        System.out.println("get token: end" + rst);

                    }
                });

            }
        });

//        Calendar cl = Calendar.getInstance();
//        cl.add(Calendar.SECOND,60);
//        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
//
//
//                //闹钟的小时
//                .putExtra(AlarmClock.EXTRA_HOUR, cl.get(Calendar.HOUR_OF_DAY))
//                //闹钟的分钟
//                .putExtra(AlarmClock.EXTRA_MINUTES, cl.get(Calendar.MINUTE))
//                //响铃时提示的信息
//                .putExtra(AlarmClock.EXTRA_MESSAGE, "试一下222")
//                //用于指定该闹铃触发时是否振动
//                .putExtra(AlarmClock.EXTRA_VIBRATE, true)
//                //一个 content: URI，用于指定闹铃使用的铃声，也可指定 VALUE_RINGTONE_SILENT 以不使用铃声。
//                //如需使用默认铃声，则无需指定此 extra。
//                //一个 ArrayList，其中包括应重复触发该闹铃的每个周日。
//                // 每一天都必须使用 Calendar 类中的某个整型值（如 MONDAY）进行声明。
//                //如果为true，则调用startActivity()不会进入手机的闹钟设置界面
//                .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
//        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
//
//            //闹钟的小时
//            .putExtra(AlarmClock.EXTRA_LENGTH, 20)
//            //响铃时提示的信息
//            .putExtra(AlarmClock.EXTRA_MESSAGE, "试一下")
//            //用于指定该闹铃触发时是否振动
//            .putExtra(AlarmClock.EXTRA_VIBRATE, true)
//            //一个 content: URI，用于指定闹铃使用的铃声，也可指定 VALUE_RINGTONE_SILENT 以不使用铃声。
//            //如需使用默认铃声，则无需指定此 extra。
//            //一个 ArrayList，其中包括应重复触发该闹铃的每个周日。
//            // 每一天都必须使用 Calendar 类中的某个整型值（如 MONDAY）进行声明。
//            //如果为true，则调用startActivity()不会进入手机的闹钟设置界面
//            .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
//        Intent intent = new Intent(AlarmClock.ACTION_DISMISS_ALARM)
//
//                //闹钟的小时
//                .putExtra(AlarmClock.EXTRA_ALARM_SEARCH_MODE, AlarmClock.ALARM_SEARCH_MODE_NEXT)
//                //响铃时提示的信息
////                .putExtra(AlarmClock.EXTRA_MESSAGE, "试一下222")
//                //一个 content: URI，用于指定闹铃使用的铃声，也可指定 VALUE_RINGTONE_SILENT 以不使用铃声。
//                //如需使用默认铃声，则无需指定此 extra。
//                //一个 ArrayList，其中包括应重复触发该闹铃的每个周日。
//                // 每一天都必须使用 Calendar 类中的某个整型值（如 MONDAY）进行声明。
//                //如果为true，则调用startActivity()不会进入手机的闹钟设置界面
//                .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//

//        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" + "start");
//
//        AlarmManager manager =  (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
//        Intent it = new Intent(this, BackService.class);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.add(Calendar.SECOND, 20);
//
//
//        PendingIntent pi = PendingIntent.getService(
//                this, 0, it, FLAG_CANCEL_CURRENT);
//        manager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),200,pi);


//         enable Cordova apps to be started in the background


        Bundle extras = getIntent().getExtras();

        if (extras != null && extras.getBoolean("cdvStartInBackground", false)) {
            moveTaskToBack(true);
        }

        // Set by <content src="index.html" /> in config.xml
        loadUrl(launchUrl);
    }
}
