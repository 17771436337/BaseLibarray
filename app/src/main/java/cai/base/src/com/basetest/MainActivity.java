package cai.base.src.com.basetest;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import cai.base.src.com.basetest.annotation.ActivityInject;
import cai.base.src.com.basetest.base.BaseActivity;
import cai.base.src.com.basetest.enums.ActivityTypeEnum;


@ActivityInject(contentViewId = R.layout.activity_main,
        isTable = true,
        tableName = "测试",
activityType = ActivityTypeEnum.BaseActivity)
public class MainActivity extends BaseActivity {

    FrameLayout content;


    @Override
    protected void initData() {
//        text.setText("测试成功");
//        content = (FrameLayout) findViewById(R.id.content);
        Log.d("test", "hahahahah");     //动态加载碎片的五个步骤，按钮按下时替换原来的碎片布局

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content,new MainFragment());
        fragmentTransaction.commit();
    }

    @Override
    protected void onBackClick() {
        Toast.makeText(this,"返回成功",Toast.LENGTH_SHORT).show();
    }
}
