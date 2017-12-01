package cai.base.src.com.basetest.test.mvp.view.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cai.base.src.com.basetest.R;
import cai.base.src.com.basetest.test.db.UserDb;
import cai.test.com.base.annotation.Event;
import cai.test.com.base.annotation.ViewInject;
import cai.test.com.base.view.widget.CountDownTimerUtils;
import cai.test.com.base.view.activitys.BaseTitleActivity;

/**
 * Created by Administrator on 2017/11/24.
 */

public class RegisterActivity extends BaseTitleActivity {

    @ViewInject(R.id.account)
    private EditText accountEdit;

    @ViewInject(R.id.password)
    private EditText passwordEdit;

    @ViewInject(R.id.verification)
    private EditText verificationEdit;

    @ViewInject(R.id.code)
    private TextView codeText;

    private String code;


    @Override
    public void init() {

    }


    @Event(value = {R.id.register, R.id.code})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                register();
                break;
            case R.id.code:
                getCode();
                break;
        }
    }

    /**
     * 注册
     */
    private void register() {
        String  account = accountEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        String verification = verificationEdit.getText().toString();
        if (TextUtils.isEmpty(account)){
            Toast.makeText(context,"请输入用户账号",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(context,"请输入用户密码",Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(verification)){
            Toast.makeText(context,"请输入验证码",Toast.LENGTH_SHORT).show();
            return;
        }

        if (!TextUtils.isEmpty(code)){
            if (!code.equals(verification)){
                Toast.makeText(context,"两次验证码不相同",Toast.LENGTH_SHORT).show();
                return;
            }
        }else{
            Toast.makeText(context,"请获取验证码",Toast.LENGTH_SHORT).show();
            return;
        }

        UserDb user = new UserDb();
        user.setAccount(account);
        user.setPassword(password);
        user.setTimeCreation(System.currentTimeMillis());
        if (UserDb.register(user)){
            Toast.makeText(context,"注册成功",Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(context,"用户已存在",Toast.LENGTH_SHORT).show();
        }


    }


    /**
     * 获取验证码
     */
    private void getCode() {
        CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(codeText, 60000, 1000, new CountDownTimerUtils.DownTimer() {
            @Override
            public void onFinish() {
                code = 1234+"";
            }
        });
        mCountDownTimerUtils.start();
    }


    @Override
    public int getContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected boolean isShowBack() {
        return true;
    }

    @Override
    protected String getTitleName() {
        return "注册";
    }

    @Override
    protected void onBackClick() {
        finish();
    }
}
