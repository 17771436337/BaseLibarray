package cai.test.com.base.view.activitys;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ycl.tabview.library.TabView;
import com.ycl.tabview.library.TabViewChild;

import java.util.List;

import cai.test.com.base.presenter.Presenter;
import cai.test.com.base.R;
import cai.test.com.base.manger.ActivityManger;


/**
 * Created by Administrator on 2017/10/24.
 */

public abstract class BaseHomeActivity<P extends Presenter> extends BasicsActivity<P> {
    TabView tabView;

    private long exitTime;

    @Override
    public int getContentViewId() {
        return R.layout.activity_base_home;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(getContentViewId());

        tabView= (TabView) findViewById(R.id.tabView);
        //start add data

        //end add data
        tabView.setTabViewDefaultPosition(0);
        tabView.setTabViewChild(getTabDatas(),getSupportFragmentManager());
        tabView.setOnTabChildClickListener(new TabView.OnTabChildClickListener() {
            @Override
            public void onTabChildClick(int  position, ImageView currentImageIcon, TextView currentTextView) {
                onTabClick(position);
            }
        });

        init();

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
                ActivityManger.getAppManager().AppExit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    public abstract void init();
}
