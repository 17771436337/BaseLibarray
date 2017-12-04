package cai.base.src.com.basetest.test.mvp.login;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cai.base.src.com.basetest.R;
import cai.base.src.com.basetest.test.mvp.main.MainActivity;
import cai.base.src.com.basetest.test.mvp.register.RegisterActivity;
import cai.test.com.base.annotation.Event;
import cai.test.com.base.annotation.RequirePresenter;
import cai.test.com.base.annotation.ViewInject;
import cai.test.com.base.view.activitys.BaseTitleActivity;

/**
 * Created by Administrator on 2017/11/24.
 */
@RequirePresenter(LoginPresenter.class)
public class LoginActivity extends BaseTitleActivity<LoginPresenter> implements LoginView{


    @ViewInject(R.id.account)
    private EditText accountEdit;

    @ViewInject(R.id.password)
    private EditText passwordEdit;


    @Override
    public void init() {
        getPresenter().isLogin();
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
    @Override
    public String getAccount(){
        return accountEdit.getText().toString();
    }

    /**获取用户密码*/
    @Override
    public String getPassword(){
        return passwordEdit.getText().toString();
    }

    /**跳转首页*/
    @Override
    public void startMain(){
                startActivity(MainActivity.class,null);

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
