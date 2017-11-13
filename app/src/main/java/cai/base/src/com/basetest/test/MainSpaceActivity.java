package cai.base.src.com.basetest.test;


import com.ycl.tabview.library.TabViewChild;

import java.util.ArrayList;
import java.util.List;

import cai.base.src.com.basetest.R;
import cai.test.com.base.base.activitys.BaseHomeActivity;

public class MainSpaceActivity extends BaseHomeActivity {


    @Override
    protected List<TabViewChild> getTabDatas() {

        List<TabViewChild> data = new ArrayList<>();
        TabViewChild tabViewChild1 = new TabViewChild(R.mipmap.ic_launcher, R.mipmap.ic_launcher, "1号", new MainFragment());
        TabViewChild tabViewChild2 = new TabViewChild(R.mipmap.ic_launcher, R.mipmap.ic_launcher, "2号", new MainFragment());
        TabViewChild tabViewChild3 = new TabViewChild(R.mipmap.ic_launcher, R.mipmap.ic_launcher, "3号", new MainFragment());
        TabViewChild tabViewChild4 = new TabViewChild(R.mipmap.ic_launcher, R.mipmap.ic_launcher, "4号", new MainFragment());
        data.add(tabViewChild1);
        data.add(tabViewChild2);
        data.add(tabViewChild3);
        data.add(tabViewChild4);
        return data;
    }

    @Override
    protected void onTabClick(int position) {

    }

    @Override
    public void init() {

    }
}
