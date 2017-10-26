package cai.base.src.com.basetest.base.activitys;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ycl.tabview.library.TabView;
import com.ycl.tabview.library.TabViewChild;

import java.util.ArrayList;
import java.util.List;

import cai.base.src.com.basetest.MainFragment;
import cai.base.src.com.basetest.R;
import cai.base.src.com.basetest.enums.ActivityTypeEnum;

/**
 * Created by Administrator on 2017/10/24.
 */

public abstract class BaseHomeActivity extends BasicsActivity {
    TabView tabView;

    private long exitTime;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_base_home);

        tabView= (TabView) findViewById(R.id.tabView);
        //start add data

        //end add data
        tabView.setTabViewDefaultPosition(2);
        tabView.setTabViewChild(getTabDatas(),getSupportFragmentManager());
        tabView.setOnTabChildClickListener(new TabView.OnTabChildClickListener() {
            @Override
            public void onTabChildClick(int  position, ImageView currentImageIcon, TextView currentTextView) {
                onTabClick(position);
            }
        });

    }


    /**通过对应的数据进行添加首页*/
    protected abstract List<TabViewChild> getTabDatas();


    protected abstract void onTabClick(int  position);







    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
               System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
