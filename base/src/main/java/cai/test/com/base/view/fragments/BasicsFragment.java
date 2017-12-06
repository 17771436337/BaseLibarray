package cai.test.com.base.view.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import cai.test.com.base.R;
import cai.test.com.base.interfaces.BaseViewInterrfaces;
import cai.test.com.base.interfaces.PresenterFactory;
import cai.test.com.base.interfaces.ViewWithPresenter;
import cai.test.com.base.presenter.Presenter;
import cai.test.com.base.presenter.PresenterDelegate;
import cai.test.com.base.presenter.ReflectionPresenterFactory;
import cai.test.com.base.view.activitys.BasicsActivity;
import cai.test.com.base.x;

/**
 * Created by Administrator on 2017/10/23.
 * 碎片抽象化的基类
 */

public abstract class BasicsFragment<P extends Presenter> extends Fragment implements BaseViewInterrfaces ,ViewWithPresenter<P> {

    /**上下文参数*/
    protected Context context;
    /**当前布局的视图*/
    protected  View mView;
    /**宿主Activity*/
    private BasicsActivity mActivity;

    private boolean injected = false;

    private PresenterDelegate<P> presenterDelegate =
            new PresenterDelegate<>(ReflectionPresenterFactory.<P>fromViewClass(getClass()));

    /**
     * 获取宿主的Activity
     * @return
     */
    protected BasicsActivity getHoldingActivity(){
        return mActivity;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenterDelegate.onCreate(this);
        injected = true;

        context = getActivity();
       mView = initView(inflater,savedInstanceState);
//        mView = x.view().inject(this, inflater, container);
        x.view().inject(this,mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!injected) {
            x.view().inject(this, mView);
        }
        init();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (BasicsActivity) activity;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }


    //-------------------------------------------------------------------自实现相关逻辑代码----------------------------------------------------------------
    protected View findViewById(int id){
        return mView.findViewById(id);
    }


    /**初始化布局*/
    protected abstract View initView(LayoutInflater inflater,Bundle savedInstanceState);




    @Override
    public PresenterFactory<P> getPresenterFactory() {
        return presenterDelegate.getPresenterFactory();
    }

    @Override
    public void setPresenterFactory(PresenterFactory<P> factory) {
        presenterDelegate.setPresenterFactory(factory);
    }

    @Override
    public P getPresenter() {
        return presenterDelegate.getPresenter();
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

}
