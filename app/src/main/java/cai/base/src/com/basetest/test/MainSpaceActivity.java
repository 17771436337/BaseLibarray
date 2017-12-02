package cai.base.src.com.basetest.test;

import android.content.Intent;
import android.text.TextUtils;

import cai.base.src.com.basetest.R;
import cai.base.src.com.basetest.test.mvp.view.activity.LoginActivity;
import cai.test.com.base.utils.SPUtils;
import cai.test.com.base.view.activitys.BaseFragmentActivity;

public class MainSpaceActivity extends BaseFragmentActivity {

    @Override
    public void init() {
        addFragment(R.id.content,new MainFragment());

        if (!isLogin()){
           LoginActivity.startLogin(this);
        }
        startService(new Intent(this,ChatApp.class));
    }
    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }


    /**判断是否登陆*/
    private boolean isLogin(){
        if (!TextUtils.isEmpty(SPUtils.getInstance(Config.AppUserSPName).getString(Config.UserId))){
            return true;
        }
        return false;
    }
}
