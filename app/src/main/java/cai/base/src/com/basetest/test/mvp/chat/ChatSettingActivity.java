package cai.base.src.com.basetest.test.mvp.chat;

import cai.base.src.com.basetest.R;
import cai.test.com.base.view.activitys.BaseTitleActivity;

/**
 * Created by Administrator on 2017/12/6.
 */

public class ChatSettingActivity extends BaseTitleActivity {
    @Override
    public void init() {

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
        return "聊天设置";
    }

    @Override
    protected void onBackClick() {
finish();
    }
}
