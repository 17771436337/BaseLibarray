package cai.test.com.base.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import cai.test.com.base.R;

/**
 * Created by Administrator on 2017/11/15.
 * 页面跳转
 */

public class StartActivityUtils {


    /**
     * 设置意图
     */
    public void startActivity(Context context,Intent intent) {
        context.startActivity(intent);

    }

    /**
     * Activity设置意图转 ,可返回
     */
    public void startActivityForResult(Activity activity, Intent intent, int requestCode) {
        activity.startActivityForResult(intent, requestCode);

    }


    /**
     * Activity不带参数得页面跳转
     */
    protected  void startActivity(Context context,Class activity) {
        Intent it = new Intent(context, activity);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(context,it);
    }


    /**
     * Activity携带参数得页面跳转
     */
    protected  void startActivity(Context context,Class activity, Bundle bundle) {
        Intent it = new Intent(context, activity);
        it.putExtras(bundle);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(context,it);
    }

    /**
     * Activity不带参数得页面跳转 ,可返回
     */
    protected void startActivityForResult(Activity activity,Class toActivity, int requestCode) {
        Intent it = new Intent(activity, toActivity);
        startActivityForResult(activity,it, requestCode);
    }

    /**
     * Activity携带参数得页面跳转  ,可返回
     */
    protected void startActivityForResult(Activity activity,Class toActivity,Bundle bundle, int requestCode) {
        Intent it = new Intent(activity, toActivity);
        it.putExtras(bundle);
        startActivityForResult(activity,it, requestCode);
    }
}
