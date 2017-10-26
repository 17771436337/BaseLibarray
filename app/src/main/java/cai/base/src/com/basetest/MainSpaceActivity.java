package cai.base.src.com.basetest;


import android.widget.Toast;

import com.ycl.tabview.library.TabViewChild;

import java.util.ArrayList;
import java.util.List;

import cai.base.src.com.basetest.base.activitys.BaseHomeActivity;

public class MainSpaceActivity extends BaseHomeActivity {


    @Override
    protected void initData() {

//        //动态加载碎片的五个步骤，按钮按下时替换原来的碎片布局
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.content,new MainFragment());
//        fragmentTransaction.commit();
    }

    @Override
    protected List<TabViewChild> getTabDatas() {
        List<TabViewChild> tabViewChildList=new ArrayList<>();
        TabViewChild tabViewChild01=new TabViewChild(R.mipmap.ic_launcher,R.mipmap.ic_launcher,"首页", new MainFragment());
        TabViewChild tabViewChild02=new TabViewChild(R.mipmap.ic_launcher,R.mipmap.ic_launcher,"分类",  new MainFragment());
        TabViewChild tabViewChild03=new TabViewChild(R.mipmap.ic_launcher,R.mipmap.ic_launcher,"资讯", new MainFragment());
        TabViewChild tabViewChild04=new TabViewChild(R.mipmap.ic_launcher,R.mipmap.ic_launcher,"购物车", new MainFragment());
        TabViewChild tabViewChild05=new TabViewChild(R.mipmap.ic_launcher,R.mipmap.ic_launcher,"我的",  new MainFragment());
        tabViewChildList.add(tabViewChild01);
        tabViewChildList.add(tabViewChild02);
        tabViewChildList.add(tabViewChild03);
        tabViewChildList.add(tabViewChild04);
        tabViewChildList.add(tabViewChild05);
        return tabViewChildList;
    }

    @Override
    protected void onTabClick(int position) {
        Toast.makeText(getApplicationContext(),"position:"+position,Toast.LENGTH_SHORT).show();
    }

}
