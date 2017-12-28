package cai.test.com.base.view.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import cai.test.com.base.presenter.Presenter;
import cai.test.com.base.presenter.PresenterDelegate;
import cai.test.com.base.R;
import cai.test.com.base.presenter.ReflectionPresenterFactory;
import cai.test.com.base.interfaces.ActivityResult;
import cai.test.com.base.interfaces.BaseViewInterrfaces;
import cai.test.com.base.interfaces.PresenterFactory;
import cai.test.com.base.interfaces.ViewWithPresenter;
import cai.test.com.base.manger.ActivityManger;
import cai.test.com.base.x;

/**
 * Created by Administrator on 2017/9/25.
 */
public abstract class BasicsActivity<P extends Presenter> extends AppCompatActivity implements BaseViewInterrfaces,ViewWithPresenter<P> {

   private ActivityResult activityResult;

    protected Context context;

    private PresenterDelegate<P> presenterDelegate =
            new PresenterDelegate<>(ReflectionPresenterFactory.<P>fromViewClass(getClass()));


    //-------------------------------------------------------------------周期相关代码----------------------------------------------------------------

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenterDelegate.onCreate(this);
        context = this;
        ActivityManger.getAppManager().addActivity(this);
        initView(savedInstanceState);
        x.view().inject(this);
        init();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManger.getAppManager().removeActivity(this);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        activityResult.onActivityResult(requestCode,resultCode,data);
    }


    //-------------------------------------------------------------------自实现相关逻辑代码----------------------------------------------------------------


    protected abstract void initView(Bundle savedInstanceState);



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
     * 跳转到本Activity
     * @param activity
     * 要跳转的Activity
     * @param bundle
     *      需要传递的参数，如果不想传递参数，则直接传空
     */
    protected void startActivity(Class activity,Bundle bundle){
        Intent intent = new Intent(context,activity);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

    /**
     *
     * 跳转到本Activity
     * @param activity
     *      要跳转的Activity
     * @param bundle
     *      需要传递的参数，如果不想传递参数，则直接传空
     * @param requestCode
     * 参数值
     * @param activityResult
     * 回调对应的onActivityResult
     */
    public void startActivityForResult(Class<BasicsActivity>  activity,Bundle bundle,int requestCode,ActivityResult activityResult){
        Intent intent = new Intent(context,activity);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityForResult(intent,requestCode);
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        this.activityResult = activityResult;
    }



}