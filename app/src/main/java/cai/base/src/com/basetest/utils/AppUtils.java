package cai.base.src.com.basetest.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.Settings;
import android.widget.Toast;

import java.io.File;
import java.util.Collections;
import java.util.List;

import cai.base.src.com.basetest.manger.ActivityManger;

/**
 * Created by Administrator on 2017/10/28.
 * 实现各种跳转App的方法
 */

public class AppUtils {
    private static AppUtils instance;

    private AppUtils() {}


    /**
     * 单一实例
     */
    public static AppUtils getInstance() {
        if (instance == null) {
            synchronized (AppUtils.class) {
                if (instance == null) {
                    instance = new AppUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 检查系统应用程序，并打开
     */
    public void openApp(Context context,String packageName){
        //应用过滤条件
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager mPackageManager = context.getPackageManager();
        List<ResolveInfo> mAllApps = mPackageManager.queryIntentActivities(mainIntent, 0);
        //按包名排序
        Collections.sort(mAllApps, new ResolveInfo.DisplayNameComparator(mPackageManager));

        for(ResolveInfo res : mAllApps){
            //该应用的包名和主Activity
            String pkg = res.activityInfo.packageName;
            String cls = res.activityInfo.name;

            if(pkg.contains(packageName)){
                ComponentName componet = new ComponentName(pkg, cls);
                Intent intent = new Intent();
                intent.setComponent(componet);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }else{
                Toast.makeText(context, "您未能成功打开该App,请下载或手动进入该App进行业务操作",Toast.LENGTH_SHORT).show();
            }
        }
    }



    /**
     *  启动应用的设置
     *
     * @since 2.5.0
     *
     */
    public void startAppSettings(Context context) {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivity(intent);
    }


    /**
     * 启动应用的音频播放器
     * @param context
     * @param path
     *          路径
     */
    public void startAppAudio(Context context,String path){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setDataAndType(Uri.fromFile(new File(path)), "audio/*");
        context.startActivity(intent);
    }
    /**
     * 启动应用的文本
     * @param context
     * @param path
     *          路径
     */
    public void startAppText(Context context,String path){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setDataAndType(Uri.fromFile(new File(path)), "text/*");
        context.startActivity(intent);
    }
    /**
     * 启动应用的图片
     * @param context
     * @param path
     *          路径
     */
    public void startAppmage(Context context,String path){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setDataAndType(Uri.fromFile(new File(path)), "image/*");
        context.startActivity(intent);
    }
}
