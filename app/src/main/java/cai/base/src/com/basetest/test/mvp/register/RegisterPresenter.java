package cai.base.src.com.basetest.test.mvp.register;

import android.text.TextUtils;
import android.widget.Toast;

import cai.base.src.com.basetest.test.db.UserDb;
import cai.test.com.base.presenter.Presenter;
import cai.test.com.base.view.widget.CountDownTimerUtils;

/**
 * Created by Administrator on 2017/12/4.
 */

public class RegisterPresenter extends Presenter<RegisterActivity>{

    private String code;

    /**
     * 注册
     */
    public void register() {
        String  account = getView().getAccount();
        String password = getView().getPassword();
        String verification = getView().getVerification();
        if (TextUtils.isEmpty(account)){
            Toast.makeText(getView(),"请输入用户账号",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(getView(),"请输入用户密码",Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(verification)){
            Toast.makeText(getView(),"请输入验证码",Toast.LENGTH_SHORT).show();
            return;
        }

        if (!TextUtils.isEmpty(code)){
            if (!code.equals(verification)){
                Toast.makeText(getView(),"两次验证码不相同",Toast.LENGTH_SHORT).show();
                return;
            }
        }else{
            Toast.makeText(getView(),"请获取验证码",Toast.LENGTH_SHORT).show();
            return;
        }

        UserDb user = new UserDb();
        user.setAccount(account);
        user.setPassword(password);
        user.setTimeCreation(System.currentTimeMillis());
        if (UserDb.register(user)){
            Toast.makeText(getView(),"注册成功",Toast.LENGTH_SHORT).show();
            getView().finish();
        }else{
            Toast.makeText(getView(),"用户已存在",Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 获取验证码
     */
    public void getCode() {
        CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(getView().getCodeText(), 60000, 1000, new CountDownTimerUtils.DownTimer() {
            @Override
            public void onFinish() {
                code = 1234+"";
            }
        });
        mCountDownTimerUtils.start();
    }

}
