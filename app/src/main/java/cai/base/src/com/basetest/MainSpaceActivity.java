package cai.base.src.com.basetest;


import android.widget.Toast;

import com.ycl.tabview.library.TabViewChild;

import java.util.ArrayList;
import java.util.List;

import cai.base.src.com.basetest.base.activitys.BaseFragmentActivity;

public class MainSpaceActivity extends BaseFragmentActivity {


    @Override
    protected void initData() {
        addFragment(R.id.content,new MainFragment());

//        //动态加载碎片的五个步骤，按钮按下时替换原来的碎片布局
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.content,new MainFragment());
//        fragmentTransaction.commit();
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }
}
