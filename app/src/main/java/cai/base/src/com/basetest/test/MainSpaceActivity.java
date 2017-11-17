package cai.base.src.com.basetest.test;
import cai.base.src.com.basetest.R;
import cai.test.com.base.base.activitys.BaseFragmentActivity;
import cai.test.com.base.base.activitys.BaseTitleActivity;
import cai.test.com.base.base.activitys.WebActivity;
import cai.test.com.base.base.fragments.BaseSpaceFragment;
public class MainSpaceActivity extends BaseTitleActivity {

    @Override
    public void init() {

    }
    @Override
    public int getContentViewId() {
        return R.layout.activity_no_network;
    }

    @Override
    protected boolean isShowBack() {
        return true;
    }

    @Override
    protected String getTitleName() {
        return "测试";
    }

    @Override
    protected void onBackClick() {

        WebActivity.launch(this,"http://http://www.breakyizhan.com/GIT/14.html","百度");
    }
}
