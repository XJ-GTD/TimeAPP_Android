
package com.xj.ionic.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import cn.jiguang.api.JCoreInterface;
import cn.jpush.android.service.PushService;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class MQService extends Service {

    private static final String TAG = MQService.class.getSimpleName();

    private Connection connection = null;
    private Channel channel = null;

    private Context mContext;

    // Binder given to clients
    private final IBinder mBinder = new MQService.MQBinder();

    public class MQBinder extends Binder {
        public MQService getService() {
            // Return this instance of ForegroundService
            // so clients can call public methods
            return MQService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        JCoreInterface.asyncExecute(new MQ());

    }

    /**
     * No need to run headless on destroy.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        //sleepWell();
    }


    //
    class CustomConsumer extends QueueingConsumer {
        public CustomConsumer(Channel ch) {
            super(ch);
        }

        @Override
        public void handleDelivery(String consumerTag,
                                   Envelope envelope,
                                   AMQP.BasicProperties properties,
                                   byte[] body)
                throws IOException {
            String routingKey = envelope.getRoutingKey();
            String contentType = properties.getContentType();
            long deliveryTag = envelope.getDeliveryTag();
            // (process the message components here ...)
            channel.basicAck(deliveryTag, false);

            System.out.println(" MQ TEST message: routingKey ,contentType,deliveryTag:" + routingKey + ":" + contentType + ":" + deliveryTag + ":" + new String(body));
        }

        @Override
        public void handleCancel(String consumerTag) throws IOException {
            super.handleCancel(consumerTag);
            System.out.println(" MQ TEST message: end " + consumerTag);
        }
    }

    ;

    class MQ implements Runnable {
        ConnectionFactory factory;
        QueueingConsumer consumer;

        public void run() {
            try {
                if (android.os.Build.VERSION.SDK_INT > 9) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }

                testMq();
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("MQ TEST" + ex.getMessage());
            }
        }

        private void testMq() throws IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException, InterruptedException {
            System.out.println(" MQ TEST [*] Waiting for messages.1");
            factory = new ConnectionFactory();
            //factory.setHost("wss://pluto.guobaa.com/ws");
            factory.setHost("116.62.165.52");
            factory.setPort(5672);
            System.out.println(" MQ TEST [*] Waiting for messages.2");
            factory.setUsername("gtd_mq");
            factory.setPassword("gtd_mq");
            factory.setVirtualHost("/");
            factory.setAutomaticRecoveryEnabled(true);
            factory.setShutdownTimeout(0);
            factory.setRequestedHeartbeat(5000);
            factory.setHandshakeTimeout(5000);
            factory.setNetworkRecoveryInterval(5000);
            // disable topology recovery
            //factory.setTopologyRecoveryEnabled(false);

            connection = factory.newConnection();
//        connection.close();
            channel = connection.createChannel();
            System.out.println(" MQ TEST [*] Waiting for messages.3");
            //channel.exchangeDeclare(exchangeName, "direct", true);
//        channel.exchangeDeclare("queueName", "fanout");
            //String queueName = channel.queueDeclare().getQueue();
            System.out.println(" MQ TEST [*] Waiting for messages.4");
            //channel.queueBind(queueName, "queueName", "");

//            System.out.println(" MQ TEST [*] Waiting for messages. To exit press CTRL+C");
//        RpcClientParams params = new RpcClientParams();
//        params.channel(channel);
//        params.replyTo("queueName");
//        params.replyHandler(new RpcClient.RpcClientReplyHandler() {
//            @Override
//            public RpcClient.Response handle(Object reply) {
//                RpcClient.DEFAULT_REPLY_HANDLER.handle(reply);
//                System.out.println(" MQ TEST message: routingKey ,contentType,deliveryTag" + reply);
//                return null;
//
//            }
//        });
////
//
//            RpcClient client = new RpcClient(params);

//            consumer = new QueueingConsumer(channel);
            channel.basicConsume("zhangjy", false, "myConsumerTag", new CustomConsumer(channel));


//        while (true){
//            channel.
//        }

//            while (true){
//                try{
//
//                    QueueingConsumer.Delivery delivery = consumer.nextDelivery();
//                    String message = new String(delivery.getBody());
//                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
//                    System.out.println(" [x] Received '" + message + "'");
//                }catch (Exception ex){
//                    try{
//
//                        System.out.println(" [x] Received ' error" );
//                        connection = factory.newConnection();
//                        channel = connection.createChannel();
//                        consumer = new QueueingConsumer(channel);
//                        channel.basicConsume("zhangjy", false, "myConsumerTag",consumer);
//                        Thread.sleep(1000);
//                    }catch (Exception ex1){
//                        ex1.printStackTrace();
//                    }
//                }
//            }

        }
    }
}
