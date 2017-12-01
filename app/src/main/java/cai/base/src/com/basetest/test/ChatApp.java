package cai.base.src.com.basetest.test;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

import java.util.List;

/**
 * Created by Administrator on 2017/11/30.
 */

public class ChatApp extends Service {

    EMMessageListener msgListener;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0x0001:
                    List<EMMessage> messages = (List<EMMessage>) msg.obj;
                    for (EMMessage context : messages){

                        Log.d("消息","接收到的消息:"+context.getType().toString() + ","+context.getBody().toString());

                    }
                    break;
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                EMClient.getInstance().chatManager().addMessageListener(msgListener);
                msgListener = new EMMessageListener() {

                    @Override
                    public void onMessageReceived(List<EMMessage> messages) {
                        //收到消息
                        Message msg = new Message();
                        msg.what = 0x0001;
                        msg.obj = messages;
                        handler.sendMessage(msg);



                    }

                    @Override
                    public void onCmdMessageReceived(List<EMMessage> messages) {
                        //收到透传消息
                    }

                    @Override
                    public void onMessageRead(List<EMMessage> messages) {
                        //收到已读回执
                    }

                    @Override
                    public void onMessageDelivered(List<EMMessage> message) {
                        //收到已送达回执
                    }

                    @Override
                    public void onMessageChanged(EMMessage message, Object change) {
                        //消息状态变动
                    }
                };
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        //记得在不需要的时候移除listener，如在activity的onDestroy()时
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }
}
