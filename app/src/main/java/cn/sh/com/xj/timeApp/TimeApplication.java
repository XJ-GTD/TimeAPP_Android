package cn.sh.com.xj.timeApp;

import android.app.Application;
import android.provider.Settings;
import com.huawei.android.hms.agent.HMSAgent;

public class TimeApplication extends Application {
    @Override
    public void onCreate() {

        super.onCreate();
        System.out.println("huawei init");
        HMSAgent.init(this);
    }
}
