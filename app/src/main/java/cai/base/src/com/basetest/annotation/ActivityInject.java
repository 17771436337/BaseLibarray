package cai.base.src.com.basetest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cai.base.src.com.basetest.enums.ActivityTypeEnum;

/**
 * Activit初始化的用到的注解
 */

/**
 * @Target : 表示该注解用于什么地方，可能的值在枚举类 ElemenetType 中，包括：
ElemenetType.CONSTRUCTOR        构造器声明
ElemenetType.FIELD          　　　　 域声明（包括 enum 实例）
ElemenetType.LOCAL_VARIABLE     局部变量声明
ElemenetType.METHOD           　　 方法声明
ElemenetType.PACKAGE          　　 包声明
ElemenetType.PARAMETER             参数声明
ElemenetType.TYPE          　　　　  类，接口（包括注解类型）或enum声明

 @Retention : 表示在什么级别保存该注解信息。可选的参数值在枚举类型 RetentionPolicy 中，包括：
 RetentionPolicy.SOURCE 　　　    注解将被编译器丢弃
 RetentionPolicy.CLASS 　　　　　 注解在class文件中可用，但会被VM丢弃
 RetentionPolicy.RUNTIME 　　　　VM将在运行期也保留注释，因此可以通过反射机制读取注解的信息。

 @Documented : 将此注解包含在 javadoc 中 ，它代表着此注解会被javadoc工具提取成文档。在doc文档中的内容会因为此注解的信息内容不同而不同。相当与@see,@param 等。

 @Inherited : 在您定义注解后并使用于程序代码上时，预设上父类别中的注解并不会被继承至子类别中，您可以在定义注解时加上java.lang.annotation.Inherited 限定的Annotation，这让您定义的Annotation型别被继承下来。注意注解继承只针对class 级别注解有效（这段建议看完全文后在来回顾）。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ActivityInject {
    /**
     * 中间布局
     * @return
     */
    int contentViewId() default -1;

    /**
     * 是否显示标题
     * @return
     */
    boolean isTable() default false;

    /**
     * 设置标题名字
     * @return
     */
    String tableName() default "";

    /***
     * 返回键是否显示
     * @return
     */
    boolean isBackShow() default true;

    /***
     * 是否添加加载图标
     * @return
     */
    boolean isLoading() default false;

}
