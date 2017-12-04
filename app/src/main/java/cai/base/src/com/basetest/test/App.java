package cai.base.src.com.basetest.test;

import android.app.Application;

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
