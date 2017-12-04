package cai.base.src.com.basetest.test.mvp.register;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cai.base.src.com.basetest.R;
import cai.base.src.com.basetest.test.db.UserDb;
import cai.test.com.base.annotation.Event;
import cai.test.com.base.annotation.RequirePresenter;
import cai.test.com.base.annotation.ViewInject;
import cai.test.com.base.view.widget.CountDownTimerUtils;
import cai.test.com.base.view.activitys.BaseTitleActivity;

/**
 * Created by Administrator on 2017/11/24.
 */

@RequirePresenter(RegisterPresenter.class)
public class RegisterActivity extends BaseTitleActivity<RegisterPresenter> implements RegisterView{

    @ViewInject(R.id.account)
    private EditText accountEdit;

    @ViewInject(R.id.password)
    private EditText passwordEdit;

    @ViewInject(R.id.verification)
    private EditText verificationEdit;

    @ViewInject(R.id.code)
    private TextView codeText;


    @Override
    public void init() {
    }


    @Event(value = {R.id.register, R.id.code})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                getPresenter().register();
                break;
            case R.id.code:
                getPresenter().getCode();
                break;
        }
    }



    @Override
    public String getAccount() {
        return accountEdit.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEdit.getText().toString();
    }


    @Override
    public String getVerification(){
        return verificationEdit.getText().toString();
    }

    @Override
    public TextView getCodeText() {
        return codeText;
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
