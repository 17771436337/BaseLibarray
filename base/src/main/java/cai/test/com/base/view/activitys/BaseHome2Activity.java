package cai.test.com.base.view.activitys;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import cai.test.com.base.R;
import cai.test.com.base.manger.ActivityManger;
import cai.test.com.base.view.widget.toolbar.BaseToolbar;
import cai.test.com.base.view.widget.toolbar.TitleToolbar;

/**
 * Created by Administrator on 2017/12/4.
 */

public abstract class BaseHome2Activity extends BasicsActivity {
    private long exitTime;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;
    private TitleToolbar mToolbar;

    /**菜单布局文件*/
    protected abstract int getMenuLayout();
    /**头部布局文件*/
    protected abstract int getHandLayout();
    /**菜单选中的点击事件*/
    protected abstract void onNavigationItemClick(MenuItem item);

    @Override
    public int getContentViewId() {
        return R.layout.activity_base_home_2;
    }





    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        onNavigationItemClick(menuItem);
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }






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



    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(getContentViewId());
        mToolbar = (TitleToolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("首页");
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close);

        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);


        //引入header
        if (getHandLayout() > 0) {
            mNavigationView.inflateHeaderView(getHandLayout());
        }
        //引入Menu
        mNavigationView.inflateMenu(getMenuLayout());

        setupDrawerContent(mNavigationView);
    }
}
