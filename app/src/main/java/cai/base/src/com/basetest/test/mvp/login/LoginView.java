package cai.base.src.com.basetest.test.mvp.login;

/**
 * Created by Administrator on 2017/12/4.
 */

public interface LoginView {
    /**用户用户账号*/
    String getAccount();
    /**获取用户密码*/
    String getPassword();
    /**跳转首页*/
    void startMain();
}
