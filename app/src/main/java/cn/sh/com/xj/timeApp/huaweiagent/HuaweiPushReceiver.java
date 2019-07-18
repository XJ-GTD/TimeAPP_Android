package cn.sh.com.xj.timeApp.huaweiagent;


import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.baidu.tts.MyTts;
import com.huawei.android.hms.agent.HMSAgent;
import com.huawei.android.hms.agent.push.handler.DeleteTokenHandler;
import com.huawei.hms.support.api.push.PushReceiver;
import com.xj.ionic.speechandtts.listener.XjTtsListener;

public class HuaweiPushReceiver extends PushReceiver {
    private static final String TAG = "HuaweiPushReceiver";
    protected String appId = "14502702";

    protected String appKey = "6YvlNRGZ5I4CkA715XnVyoSm";

    protected String secretKey = "9oHZPMLgc0BM9a4m3DhpHUhGSqYvsrAF";

    private MyTts tts;

    public HuaweiPushReceiver() {
    }
    @Override
    public void onToken(Context context, String token, Bundle extras) {

        String belongId = extras.getString("belongId");
        Log.i(TAG, "belongId:" + belongId);
        Log.i(TAG, "Token:" + token);
        //开发者自行实现token保存逻辑。
    }
    @Override
    public boolean onPushMsg(Context context, byte[] msg, Bundle bundle) {

        try {
            //开发者可以自己解析消息内容，然后做相应的处理
            tts = new MyTts(context, new XjTtsListener(null), appId, appKey, secretKey);
            String content = new String(msg, "UTF-8");
            Log.i(TAG, "收到PUSH透传消息,消息内容为:" + content);
            tts.speak(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void onEvent(Context context, Event event, Bundle extras) {

        if (Event.NOTIFICATION_OPENED.equals(event) || Event.NOTIFICATION_CLICK_BTN.equals(event)) {
            int notifyId = extras.getInt(BOUND_KEY.pushNotifyId, 0);
            Log.i(TAG, "收到通知栏消息点击事件,notifyId:" + notifyId);
            if (0 != notifyId) {
                NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                manager.cancel(notifyId);
            }
        }

        String message = extras.getString(BOUND_KEY.pushMsgKey);
        super.onEvent(context, event, extras);
    }

    /**
     * 删除token | delete push token
     */

    public void deleteToken(String token, DeleteTokenHandler handler){
        HMSAgent.Push.deleteToken(token, new DeleteTokenHandler() {
            @Override
            public void onResult(int rst) {
            }
        });
    }

}
