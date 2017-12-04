package cai.base.src.com.basetest.test.mvp.register;

import android.widget.TextView;

/**
 * Created by Administrator on 2017/12/4.
 */

public interface RegisterView {
    /**用户用户账号*/
    String getAccount();
    /**获取用户密码*/
    String getPassword();
    /**获取验证码*/
    String getVerification();
    /**返回对应的按钮布局控件*/
    TextView getCodeText();
}
