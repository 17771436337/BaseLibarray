package cai.base.src.com.basetest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/10/23.
 * Fragment初始化的用到的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface FragmentInject{
    /**
     * 中间布局
     *
     * @return
     */
    int contentViewId() default -1;

    /***
     * 是否添加加载图标
     * @return
     */
    boolean isLoading() default false;
}
