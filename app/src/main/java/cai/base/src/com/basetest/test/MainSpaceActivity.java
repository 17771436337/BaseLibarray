package cai.base.src.com.basetest.test;
import cai.base.src.com.basetest.R;
import cai.test.com.base.annotation.ContentView;
import cai.test.com.base.view.activitys.BaseTitleActivity;
import cai.test.com.base.view.activitys.WebActivity;

@ContentView(R.layout.activity_no_network)
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
