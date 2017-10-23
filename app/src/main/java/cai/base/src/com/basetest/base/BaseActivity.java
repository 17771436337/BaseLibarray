package cai.base.src.com.basetest.base;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cai.base.src.com.basetest.R;
import cai.base.src.com.basetest.annotation.ActivityFragmentInject;
import cai.base.src.com.basetest.annotation.FindById;

/**
 * Created by Administrator on 2017/9/25.
 */

public abstract class BaseActivity extends AppCompatActivity{


    /** 日志输出标志 */
    protected String TAG;
    /**布局文件id*/
   private int contentViewId;
    /** 是否显示标题 */
    private boolean isTable = false;

    /** 标题名字*/
    private String titleName;

    private boolean isTitleBack;
    /** 是否添加加载弹框*/
    private boolean isLoading ;




    //-------------------------------------------------------------------周期相关代码----------------------------------------------------------------


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAnnotation();
        setContentView(R.layout.layout_base_activity);
        setTableView();
        inject(this);
        initData();
    }


    @Override
    public void finish() {
        super.finish();
    }


    //-------------------------------------------------------------------自实现相关逻辑代码----------------------------------------------------------------

    /***
     * Loading的显示
     */
    protected void showLoading(){
        if (isLoading){//数据加载Load显示

        }
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 返回键监听事件
     */
    protected abstract void onBackClick();


    /**
     * 初始化视图，并且添加对应的标题
     * @return
     */
    private void setTableView(){
        View tableView = findViewById(R.id.activity_base_title);
        ImageView back = tableView.findViewById(R.id.table_back);
        TextView name = tableView.findViewById(R.id.table_name);

        FrameLayout content = (FrameLayout)findViewById(R.id.activity_base_content);
        content.addView(LayoutInflater.from(this).inflate(contentViewId,null));

        if (isTable) {
            if (!TextUtils.isEmpty(titleName)) {//如果内容不为空，则设置标题
                name.setText(titleName);
            }
            if (isTitleBack) {//判断返回键是否显示
                /**返回键的点击事件*/
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackClick();
                    }
                });
                back.setVisibility(View.VISIBLE);
            } else {
                back.setVisibility(View.GONE);
            }
            tableView.setVisibility(View.VISIBLE);
        }else{
            tableView.setVisibility(View.GONE);
        }


    }


    //-------------------------------------------------------------------反射逻辑处理代码----------------------------------------------------------------

    /**注解内容获取*/
    private void getAnnotation(){
        if (getClass().isAnnotationPresent(ActivityFragmentInject.class)){//判断是否为当前的注解方式
            //获取到注解类的实例化对象
            ActivityFragmentInject annotation = getClass().getAnnotation(ActivityFragmentInject.class);
            //获取到对应的布局文件id
            contentViewId = annotation.contentViewId();
            //获取到设置的对应的日志输出标志
            TAG = annotation.getTag();
            //获取到设对应的显示标题
            isTable = annotation.isTable();
            //获取到设对应的Loading显示判断
            isLoading = annotation.isLoading();
            //获取到标题名字
            titleName = annotation.tableName();
            //获取到返回键是否显示
            isTitleBack = annotation.isBackShow();

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