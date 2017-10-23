package cai.base.src.com.basetest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 对应的布局使用时的对应id
 * Created by Administrator on 2017/9/26.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS)
public @interface FindById {
    /**
     * 对应布局的每个控件
     * @return
     */
    int ViewId() default -1;

    /**
     * 默认字符串
     */
    String defaultString() default "";
}
