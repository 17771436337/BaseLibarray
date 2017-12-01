package cai.base.src.com.basetest.test.mvp.presenter;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import cai.base.src.com.basetest.test.Config;
import cai.base.src.com.basetest.test.MainSpaceActivity;
import cai.base.src.com.basetest.test.db.UserDb;
import cai.base.src.com.basetest.test.mvp.view.activity.LoginActivity;
import cai.test.com.base.presenter.Presenter;
import cai.test.com.base.utils.SPUtils;

/**
 * Created by Administrator on 2017/11/27.
 */

public class LoginPresenter extends Presenter<LoginActivity> {


    /**登陆方法*/
    public void login(){
        String  account = getView().getAccount();
        String password = getView().getPassword();



        EMClient.getInstance().login(account,password,new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Log.d("main", "登录聊天服务器成功！");
                SPUtils.getInstance(Config.AppUserSPName).put(Config.UserId,getView().getAccount()+"");
                getView().startMain();
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Log.d("main", "登录聊天服务器失败！");
            }
        });
//
//        if (TextUtils.isEmpty(account)){
//            Toast.makeText(getView(),"请输入用户账号",Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(password)){
//            Toast.makeText(getView(),"请输入用户密码",Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        long userId = UserDb.login(account,password);
//        if (userId >= 0){
//            Toast.makeText(getView(),"登陆成功",Toast.LENGTH_SHORT).show();
//            SPUtils.getInstance(Config.AppUserSPName).put(Config.UserId,userId+"");
//            getView().startMain();
//            getView().finish();
//        }else{
//            Toast.makeText(getView(),"用户不存在",Toast.LENGTH_SHORT).show();
//            return;
//        }


    }
}
