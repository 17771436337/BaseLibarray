package cai.base.src.com.basetest.base.activitys;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cai.base.src.com.basetest.R;
import cai.base.src.com.basetest.annotation.ActivityInject;
import cai.base.src.com.basetest.annotation.FindById;
import cai.base.src.com.basetest.enums.ActivityTypeEnum;

/**
 * Created by Administrator on 2017/9/25.
 */
public abstract class BasicsActivity extends AppCompatActivity{

    /**布局文件id*/
    protected int contentViewId;
    /** 是否显示标题 */
    private boolean isTable = false;


    /** 是否添加加载弹框*/
    private boolean isLoading ;


    //-------------------------------------------------------------------周期相关代码----------------------------------------------------------------

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAnnotation();
        isActivityType(savedInstanceState);
//
        inject(this);
        initData();
        showLoading();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }


    //-------------------------------------------------------------------自实现相关逻辑代码----------------------------------------------------------------

    /***
     * Loading的显示
     */
    protected void showLoading(){
        if (isLoading){//数据加载Load显示
            Toast.makeText(this,"测试",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();



    /**
     * 获取Activity类别
     */
    protected abstract ActivityTypeEnum getActivityType();

    /**
     * 首页的Activity
     */
    protected abstract void onHomeActivity(Bundle savedInstanceState);

    /**
     * 附带Fragment的Activity
     */
    protected abstract void onFragmentActivity(Bundle savedInstanceState);

    /**
     * 基础的Fragment
     */
    protected abstract void onSpaceActivity(Bundle savedInstanceState);

    /**
     * 带有标题的Fragment
     */
    protected abstract void onTitleActivity(Bundle savedInstanceState);

    /**判断不同类别，执行不同的逻辑方法*/
    private void isActivityType(Bundle savedInstanceState){
        switch (getActivityType()){
            case HomeActivity://首页
                onHomeActivity(savedInstanceState);
                break;
            case SpaceActivity:
                onSpaceActivity(savedInstanceState);
                break;
            case TitleActivity:
                onTitleActivity(savedInstanceState);
                break;
            case FragmentActivity:
                onFragmentActivity(savedInstanceState);
                break;
            default:
                onSpaceActivity(savedInstanceState);
                break;
        }
    }




    //-----------------------跳转方法---------------------


    /**
     * 设置意图
     */
    public void startActivity(Intent intent) {
       super.startActivity(intent);
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

    /**
     * Activity设置意图转 ,可返回
     */
    public void startActivityForResult(Intent intent, int requestCode) {
       super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }


    /**
     * Activity不带参数得页面跳转
     */
    protected  void startActivity(Class activity) {
        Intent it = new Intent(this, activity);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(it);
    }


    /**
     * Activity携带参数得页面跳转
     */
    protected  void startActivity(Class activity, Intent bundle) {
        Intent it = new Intent(this, activity);
        it.putExtras(bundle);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(it);
    }

    /**
     * Activity不带参数得页面跳转 ,可返回
     */
    protected void startActivityForResult(Class activity, int requestCode) {
        Intent it = new Intent(this, activity);
        startActivityForResult(it, requestCode);
    }

    /**
     * Activity携带参数得页面跳转  ,可返回
     */
    protected void startActivityForResult( Class activity, Bundle bundle, int requestCode) {
        Intent it = new Intent(this, activity);
        it.putExtras(bundle);
        startActivityForResult(it, requestCode);
    }



    //-------------------------------------------------------------------反射逻辑处理代码----------------------------------------------------------------

    /**注解内容获取*/
    private void getAnnotation(){
        if (getClass().isAnnotationPresent(ActivityInject.class)){//判断是否为当前的注解方式
            //获取到注解类的实例化对象
            ActivityInject annotation = getClass().getAnnotation(ActivityInject.class);
            //获取到对应的布局文件id
            contentViewId = annotation.contentViewId();
            //获取到设对应的Loading显示判断
            isLoading = annotation.isLoading();

        }else {//否则抛出异常
            throw new RuntimeException("Class must add annotations of ActivityFragmentInitParams.class");
        }
    }


    /**
     * 设置对应的布局控件初始化
     * @param activity
     */
    public  void inject(Activity activity){
        //获取activity对象的实际类别
        Class<? extends Activity> atyCls = activity.getClass();
        //获取类自定义的成员，不包括继承自父类的成员
        Field[] fields = atyCls.getDeclaredFields();
        for(Field field:fields){
            //成员属性是否有ViewField注解
            FindById vf = field.getAnnotation(FindById.class);
            if(vf != null){
                //通过反射，获取atyCls的findViewById方法
                try {
                    int id = vf.ViewId();
                    //指定方法名 和 参数类别列表（方法重载，需要指定参数详细）
                    //findViewById是从父类继承来的，所以用getMethod,而不是getDeclaredMethod
                    Method method = atyCls.getMethod("findViewById",int.class);
                    //调用方法，指定参数
//                    Object view = method.invoke(activity,id);
                    //可访问
                    field.setAccessible(true);
                    //给成员属性赋值
                    field.set(activity,getView(activity,method,vf));
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    /**
     * 根据不同的类别进行不同的数据判断
     * @param activity
     * @param method
     * @param vf
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private Object getView( Activity activity,Method method,FindById vf) throws InvocationTargetException, IllegalAccessException {
        int id = vf.ViewId();
        //调用方法，指定参数
        try {
            TextView view = (TextView) method.invoke(activity,id);
            String str = vf.defaultString();
            view.setText(str);
            return view;
        } catch (Exception e){
            return  method.invoke(activity,id);
        }

    }
}