package cn.jpush.android.service;

import android.annotation.TargetApi;
import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import com.baidu.tts.MyTts;
import com.xj.ionic.speechandtts.listener.XjTtsListener;

public class JobSpeakerService extends JobService {
    protected String appId = "14502702";

    protected String appKey = "6YvlNRGZ5I4CkA715XnVyoSm";

    protected String secretKey = "9oHZPMLgc0BM9a4m3DhpHUhGSqYvsrAF";


    private final static String TAG = "KeepAliveService";
    // 告知编译器，这个变量不能被优化
    public volatile static Service mKeepAliveService = null;
    private MyTts tts;

    public static boolean isJobServiceAlive() {
        return mKeepAliveService != null;
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        mKeepAliveService = this;
        tts = new MyTts(this.getApplication(), new XjTtsListener(null), appId, appKey, secretKey);
        mJobHandler.sendMessage(Message.obtain(mJobHandler, 1, params));
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        System.out.println("service back is end**********************************************");
        return true;
    }

    // 创建一个handler来处理对应的job
    private Handler mJobHandler = new Handler(new Handler.Callback() {
        // 在Handler中，需要实现handleMessage(Message msg)方法来处理任务逻辑。
        @Override
        public boolean handleMessage(Message msg) {
            System.out.println("service back is start");
            tts.speak("我成功了");
            jobFinished((JobParameters) msg.obj, true);
            JobSchedulerManager manager = JobSchedulerManager.getJobSchedulerInstance(mKeepAliveService.getApplication());
             manager.startJobScheduler();
            return true;
        }
    });

}


