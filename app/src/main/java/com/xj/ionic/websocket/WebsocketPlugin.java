/*
package com.xj.ionic.websocket;

import android.app.Activity;
import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import cn.jpush.android.data.JPushLocalNotification;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class WebsocketPlugin extends CordovaPlugin {

    private static final String TAG = WebsocketPlugin.class.getSimpleName();

    private Context mContext;

    private static WebsocketPlugin instance;
    private static Activity cordovaActivity;

    public WebsocketPlugin() {
        instance = this;
    }

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView){
        super.initialize(cordova, webView);
        mContext = cordova.getActivity().getApplicationContext();


        cordovaActivity = cordova.getActivity();

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("wss://pluto.guobaa.com/ws");
        factory.setPassword("gtd_mq");
        factory.setUsername("gtd_mq");
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//
//        channel.exchangeDeclare("15821947260.YnJvd3Nlcg", "fanout");
//        String queueName = channel.queueDeclare().getQueue();
//        channel.queueBind(queueName, "15821947260.YnJvd3Nlcg", "");
//
//        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//
//        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//            String message = new String(delivery.getBody(), "UTF-8");
//            System.out.println(" [x] Received '" + message + "'");
//        };
//        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }

    private void init(String url,String username,String password){
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        cordovaActivity = null;
        instance = null;
    }

}
*/
