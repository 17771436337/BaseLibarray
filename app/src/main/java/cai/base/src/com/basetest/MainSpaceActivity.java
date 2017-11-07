package cai.base.src.com.basetest;


import android.widget.Toast;

import com.ycl.tabview.library.TabViewChild;

import java.util.ArrayList;
import java.util.List;

import cai.base.src.com.basetest.base.activitys.BaseFragmentActivity;
import cai.base.src.com.basetest.base.activitys.BaseHomeActivity;

public class MainSpaceActivity extends BaseHomeActivity {


    @Override
    protected void initData() {
//        addFragment(R.id.content,new MainFragment());

//        //动态加载碎片的五个步骤，按钮按下时替换原来的碎片布局
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.content,new MainFragment());
//        fragmentTransaction.commit();
    }



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
}
