package cai.base.src.com.basetest.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import java.io.File;

/**
 * Created by Administrator on 2017/10/28.
 * 实现各种跳转App的方法
 */

public class AppUtils {

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
