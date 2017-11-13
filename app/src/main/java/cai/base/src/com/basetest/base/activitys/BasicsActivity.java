package cai.base.src.com.basetest.base.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import org.xutils.x;

import cai.base.src.com.basetest.R;
import cai.base.src.com.basetest.manger.ActivityManger;
import cai.base.src.com.basetest.utils.StatusBarCompat;

/**
 * Created by Administrator on 2017/9/25.
 */
public abstract class BasicsActivity extends AppCompatActivity{

    //-------------------------------------------------------------------周期相关代码----------------------------------------------------------------

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityManger.getAppManager().addActivity(this);
        initView(savedInstanceState);
        x.view().inject(this);
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


    //-------------------------------------------------------------------自实现相关逻辑代码----------------------------------------------------------------


    protected abstract void initView(Bundle savedInstanceState);



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

}