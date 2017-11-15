package cai.test.com.base.utils;


import android.app.Activity;

import cai.test.com.base.annotation.Router;

/**
 * Created by Administrator on 2017/11/15.
 * Activity跳转
 */

public final class AnnotationUtils {

    /**注解获取*/
    public static  String getAnnotation(Class<Activity> cls){
        if (cls.getClass().isAnnotationPresent(Router.class)){//判断是否为当前的注解方式
            //获取到注解类的实例化对象
            Router annotation = cls.getClass().getAnnotation(Router.class);
            //获取到对应的uri
          String activity = annotation.value();

            return activity;
        }else {//否则抛出异常
            throw new RuntimeException("Class must add annotations of Router.class");
        }
    }




}
