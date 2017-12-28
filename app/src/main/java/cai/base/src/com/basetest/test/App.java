package cai.base.src.com.basetest.test;

import android.app.Application;
import android.content.Context;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import cai.test.com.base.BuildConfig;
import cai.test.com.base.x;

/**
 * Created by Administrator on 2017/11/13.
 */

public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        initBase();
        initEase(this);
    }

    /**
     * 环信的初始化
     */
    private void initEase(Context context) {
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        EaseUI.getInstance().init(context, options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
    }


    /**
     * 基础类初始化
     */
    private void initBase(){
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 开启debug会影响性能
        // 全局默认信任所有https域名 或 仅添加信任的https域名
        // 使用RequestParams#setHostnameVerifier(...)方法可设置单次请求的域名校验
        x.Ext.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        x.Ext.initDb(Config.DBName,Config.DBPath);
    }
}
