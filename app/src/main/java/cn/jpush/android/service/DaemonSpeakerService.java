package cn.jpush.android.service;

import android.app.IntentService;
import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.d.a;
import cn.jiguang.d.i.i;
import com.baidu.tts.MyTts;
import com.xj.ionic.speechandtts.listener.XjTtsListener;

public class DaemonSpeakerService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */

    public DaemonSpeakerService() {
        super("DaemonSpeakerService");
        System.out.println("DaemonSpeakerService DaemonSpeakerService");
    }

    public DaemonSpeakerService(String name) {

        super(name);

        System.out.println("DaemonSpeakerService name");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        System.out.println("DaemonSpeakerService onBind");
        this.init();
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        System.out.println("DaemonSpeakerService onHandleIntent");
        this.init();
    }

    private void init() {
        JobSchedulerManager manager = JobSchedulerManager.getJobSchedulerInstance(this);
        manager.startJobScheduler();
    }

    public int onStartCommand(Intent var1, int var2, int var3) {
        System.out.println("DaemonSpeakerService onStartCommand");
        return super.onStartCommand(var1, var2, var3);
    }
}


