package cai.base.src.com.basetest.manger;

import android.app.Application;
import android.util.Log;

import org.xutils.BuildConfig;
import org.xutils.DbManager;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.io.File;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import cai.base.src.com.basetest.CommonConfig;
import cai.base.src.com.basetest.db.TestDb;

/**
 * Created by Administrator on 2017/10/28.
 */

public class App extends Application {



    public DbManager getDb() {
        return db;
    }

    DbManager db;


    @Override
    public void onCreate() {
        super.onCreate();
        initXUtils();
        dbInit();

    }


    private void initXUtils(){
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
    }


    /**数据库的初始化*/
    private void dbInit(){
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                //设置数据库名，默认xutils.db
                .setDbName(CommonConfig.DBName+".db")
                //设置表创建的监听
                .setTableCreateListener(new DbManager.TableCreateListener() {
                    @Override
                    public void onTableCreated(DbManager db, TableEntity<?> table) {
                        Log.i("JAVA", "onTableCreated：" + table.getName());
                    }
                })
                //设置是否允许事务，默认true
                //.setAllowTransaction(true)
                //设置数据库路径，默认安装程序路径下
                .setDbDir(new File(CommonConfig.DBPath))
                //设置数据库的版本号
                .setDbVersion(1)
                //设置数据库更新的监听
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion,
                                          int newVersion) {
                    }
                })
                //设置数据库打开的监听
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        //开启数据库支持多线程操作，提升性能
                        db.getDatabase().enableWriteAheadLogging();
                    }
                });
        db = x.getDb(daoConfig);

    }
}
