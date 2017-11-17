package cai.base.src.com.basetest.test;
import cai.base.src.com.basetest.R;
import cai.test.com.base.base.activitys.BaseFragmentActivity;
import cai.test.com.base.base.fragments.BaseSpaceFragment;
public class MainSpaceActivity extends BaseFragmentActivity {

    @Override
    public void init() {
        addFragment(R.id.content,new MainFragment());
    }
    @Override
    public int getContentViewId() {
        return R.layout.fragment_main;
    }
}
