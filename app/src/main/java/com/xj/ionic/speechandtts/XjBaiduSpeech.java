package com.xj.ionic.speechandtts;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import com.baidu.aip.asrwakeup3.core.recog.MyRecognizer;
import com.baidu.aip.asrwakeup3.core.util.FileUtil;
import com.baidu.speech.asr.SpeechConstant;
import com.rabbitmq.client.*;
import com.xj.ionic.speechandtts.listener.XjSpeechRecogListener;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import cn.sh.com.xj.timeApp.R;


/**
 * This class echoes a string called from JavaScript.
 */
public class XjBaiduSpeech extends CordovaPlugin {


    private  static MyRecognizer recongnizer;

    private static Activity cordovaActivity;



    @Override
    protected void pluginInitialize() {

        super.pluginInitialize();

        if (recongnizer == null){

            initPermission();

            recongnizer = new MyRecognizer(cordova.getActivity());
        }
        cordovaActivity = cordova.getActivity();

    }




    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        recongnizer.setEventListener(new XjSpeechRecogListener(callbackContext));
        cordovaActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (action.equals("start")) {
                    recongnizer.start();
                } else if (action.equals("stop")) {
                    recongnizer.stop();

                } else if (action.equals("cancel")) {
                    recongnizer.cancel();
                } else if (action.equals("release")) {
                    recongnizer.release();
                }
            }
        });



        return true;
    }




    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermission() {
        String permissions[] = {Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(cordova.getActivity(), perm)) {
                toApplyList.add(perm);

            }
        }
        String tmpList[] = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(cordova.getActivity(), toApplyList.toArray(tmpList), 123);
        }

    }

}
