package cai.base.src.com.basetest.test.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cai.base.src.com.basetest.R;
import cai.base.src.com.basetest.test.Config;
import cai.base.src.com.basetest.test.MainSpaceActivity;
import cai.base.src.com.basetest.test.db.UserDb;
import cai.base.src.com.basetest.test.mvp.presenter.LoginPresenter;
import cai.test.com.base.annotation.ContentView;
import cai.test.com.base.annotation.Event;
import cai.test.com.base.annotation.RequirePresenter;
import cai.test.com.base.annotation.ViewInject;
import cai.test.com.base.utils.SPUtils;
import cai.test.com.base.view.activitys.BaseTitleActivity;
import cai.test.com.base.view.activitys.NetworkingAcitivity;

/**
 * Created by Administrator on 2017/11/24.
 */
@RequirePresenter(LoginPresenter.class)
public class LoginActivity extends BaseTitleActivity<LoginPresenter> {


    @ViewInject(R.id.account)
    private EditText accountEdit;

    @ViewInject(R.id.password)
    private EditText passwordEdit;


    @Override
    public void init() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_login;
    }


    @Event(value = {R.id.register,R.id.login,R.id.forget},type = View.OnClickListener.class)
    private void onClick(View v){//点击事件
        switch (v.getId()){
            case R.id.login://登陆
                getPresenter().login();
                break;

            case R.id.register://注册
              startActivity(RegisterActivity.class,null);
                break;

            case R.id.forget://忘记密码
                Toast.makeText(context,"忘记密码",Toast.LENGTH_SHORT).show();
                break;
        }
    }


    /**用户用户账号*/
    public String getAccount(){
        return accountEdit.getText().toString();
    }

    /**获取用户密码*/
    public String getPassword(){
        return passwordEdit.getText().toString();
    }

    /**跳转首页*/
    public void startMain(){
        new Handler(getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                startActivity(MainSpaceActivity.class,null);
            }
        });

    }

    /**跳转到登陆界面*/
    public static void startLogin(Context context){
        Intent intent = new Intent(context , LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    @Override
    protected boolean isShowBack() {
        return false;
    }

    @Override
    protected String getTitleName() {
        return "登陆";
    }

    @Override
    protected void onBackClick() {

    }
}
