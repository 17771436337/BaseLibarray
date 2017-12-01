package cai.test.com.base.manger;

import android.app.Application;
import android.util.Log;

import java.io.File;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import cai.test.com.base.BuildConfig;
import cai.test.com.base.interfaces.DbManager;
import cai.test.com.base.db.table.TableEntity;
import cai.test.com.base.x;


/**
 * Created by Administrator on 2017/10/28.
 */

public abstract class AppManger extends Application {



    private static AppManger instances;

    public static AppManger getInstances() {
        return instances;
    }


    /**数据库名称*/
    public abstract String getDBName();

    /**数据库路径*/
    protected abstract String getDBPath();

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        initXUtils();


    }


    private void initXUtils(){

    }




}
