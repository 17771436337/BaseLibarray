package cai.base.src.com.basetest.test.mvp.main;

import android.view.MenuItem;


import cai.base.src.com.basetest.R;
import cai.base.src.com.basetest.test.mvp.news.NewsFragment;
import cai.base.src.com.basetest.test.mvp.news.NewsListFragment;
import cai.test.com.base.view.activitys.BaseHome2Activity;

public class MainActivity extends BaseHome2Activity {

    @Override
    protected int getMenuLayout() {
        return R.menu.drawer;
    }

    @Override
    protected int getHandLayout() {
        return R.layout.nav_header_main;
    }


    @Override
    protected void onNavigationItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_item_news://新闻
                startNews();
                break;
            case R.id.navigation_item_chat://聊天
                break;

            case R.id.navigation_item_file://FTP
                break;
            case R.id.navigation_item_notepad://笔记本
                break;
            case R.id.nav_center://个人中心
                break;
            case R.id.nav_sett://设置
                break;
            case R.id.nav_logout://退出登录
                break;

        }
    }

    /**启动新闻*/
    private void startNews(){
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new NewsListFragment()).commit();
    }

    @Override
    public void init() {
        startNews();
    }
}
