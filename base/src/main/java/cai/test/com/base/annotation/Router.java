package cai.test.com.base.annotation;

import android.app.Activity;

import org.xutils.view.annotation.Event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cai.test.com.base.base.activitys.BasicsActivity;

/**
 * Created by Administrator on 2017/11/15.
 * 通过对应的uri进行代码处理
 */


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Router {
    /**获取每个Activity*/

    String value();

}
