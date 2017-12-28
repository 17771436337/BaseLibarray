package cai.base.src.com.basetest.test.mvp.chat;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;

import cai.base.src.com.basetest.R;
import cai.base.src.com.basetest.test.mvp.login.LoginActivity;
import cai.test.com.base.view.activitys.BaseTitleActivity;

/**
 * Created by Administrator on 2017/12/6.
 */

public class ChatActivity extends BaseTitleActivity{

    public static ChatActivity activityInstance;
    private EaseChatFragment chatFragment;
    String toChatUsername;

    @Override
    public void init() {
        activityInstance = this;
        //聊天人或群id
        toChatUsername = getIntent().getExtras().getString(EaseConstant.EXTRA_USER_ID);
        chatFragment = new EaseChatFragment();
        //传入参数
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityInstance = null;
    }


    /**跳转聊天界面*/
    public static void  startChat(Context context, String toChatUsername){
        Intent intent = new Intent(context , ChatActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(EaseConstant.EXTRA_USER_ID, toChatUsername);
        context.startActivity(intent);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        // 点击notification bar进入聊天页面，保证只有一个聊天页面
        String username = intent.getStringExtra("userId");
        if (toChatUsername.equals(username))
            super.onNewIntent(intent);
        else {
            finish();
            startActivity(intent);
        }

    }


    @Override
    public void onBackPressed() {
        chatFragment.onBackPressed();
    }

    public String getToChatUsername(){
        return toChatUsername;
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_chat;
    }

    @Override
    protected boolean isShowBack() {
    return true;
    }

    @Override
    protected String getTitleName() {
        return toChatUsername;
    }

    @Override
    protected void onBackClick() {
finish();
    }
}
