package cai.base.src.com.basetest.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cai.base.src.com.basetest.annotation.FragmentInject;

/**
 * Created by Administrator on 2017/10/23.
 */

public abstract class BaseFragment extends Fragment {

    /**上下文参数*/
    protected Context context;
    /**当前布局的视图*/
    protected  View mView;
    /**布局文件id*/
    private int contentViewId;

    /** 是否添加加载弹框*/
    private boolean isLoading ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getAnnotation();
        context = getActivity();
        mView = inflater.inflate(contentViewId,null);
        init();
        return mView;
    }
    //-------------------------------------------------------------------自实现相关逻辑代码----------------------------------------------------------------

    /**初始化*/
    protected abstract void init();


    //-------------------------------------------------------------------反射逻辑处理代码----------------------------------------------------------------
    /**注解内容获取*/
    private void getAnnotation(){
        if (getClass().isAnnotationPresent(FragmentInject.class)){//判断是否为当前的注解方式
            //获取到注解类的实例化对象
            FragmentInject annotation = getClass().getAnnotation(FragmentInject.class);
            //获取到对应的布局文件id
            contentViewId = annotation.contentViewId();
            //获取到设对应的Loading显示判断
            isLoading = annotation.isLoading();

        }else {//否则抛出异常
            throw new RuntimeException("Class must add annotations of ActivityFragmentInitParams.class");
        }
    }

}
