package cai.base.src.com.basetest.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cai.base.src.com.basetest.R;
import cai.base.src.com.basetest.annotation.FragmentInject;
import cai.base.src.com.basetest.enums.FragmentTypeEnum;

/**
 * Created by Administrator on 2017/10/23.
 */

public abstract class BaseFragment extends Fragment {

    /**上下文参数*/
    protected Context context;
    /**当前布局的视图*/
    protected  View mView;
    /**布局文件id*/
    protected int contentViewId;
    /** 是否添加加载弹框*/
    protected boolean isLoading;
    /** Fragment的类别 */
    protected FragmentTypeEnum fragmentType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getAnnotation();
        context = getActivity();
        isFragmentType(inflater);
        init();
        return mView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }


    //-------------------------------------------------------------------自实现相关逻辑代码----------------------------------------------------------------

    /**初始化*/
    protected abstract void init();


    /**列表的视图*/
    protected abstract void setListFragment(LayoutInflater inflater);

//    protected abstract void setBaseFragment(LayoutInflater inflater);


    /**判断不同类别，执行不同的逻辑方法*/
    protected void isFragmentType(LayoutInflater inflater){
        switch (fragmentType){
            case BaseFragment:
                mView = inflater.inflate(contentViewId,null);
                break;
            case ListFragment:
                setListFragment(inflater);
                break;
        }
    }
    //-----------------------跳转方法---------------------
    /**
     * Fragment不带参数得页面跳转
     */
    protected void startActivity(Class activity) {
        Intent it = new Intent(context, activity);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(it);
        getActivity().overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }


    /**
     * Fragment携带参数得页面跳转
     */
    protected void startActivity(Class activity, Bundle bundle) {
        Intent it = new Intent(context, activity);
        it.putExtras(bundle);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(it);
        getActivity().overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

    /**
     * Fragment不带参数得页面跳转 ,可返回
     */
    protected void startActivityForResult( Class activity, int requestCode) {
        Intent it = new Intent(context, activity);
        getActivity().startActivityForResult(it, requestCode);
        getActivity().overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

    /**
     * Fragment携带参数得页面跳转  ,可返回
     */
    protected void startActivityForResult(Class activity, Bundle bundle, int requestCode) {
        Intent it = new Intent(context, activity);
        it.putExtras(bundle);
        getActivity().startActivityForResult(it, requestCode);
        getActivity().overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }


    //-------------------------------------------------------------------反射逻辑处理代码----------------------------------------------------------------
    /**注解内容获取*/
    private void getAnnotation(){
        if (getClass().isAnnotationPresent(FragmentInject.class)){//判断是否为当前的注解方式
            //获取到注解类的实例化对象
            FragmentInject annotation = getClass().getAnnotation(FragmentInject.class);
            //获取到对应的布局文件id
            contentViewId = annotation.contentViewId();
            //获取到对应的Fragment的类别
            fragmentType = annotation.fragmentType();
            //获取到设对应的Loading显示判断
            isLoading = annotation.isLoading();

        }else {//否则抛出异常
            throw new RuntimeException("Class must add annotations of FragmentInject.class");
        }
    }


}
