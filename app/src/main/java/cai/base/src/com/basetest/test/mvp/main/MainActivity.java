package cai.base.src.com.basetest.test.mvp.main;

import android.view.MenuItem;


import com.hyphenate.easeui.ui.EaseContactListFragment;

import cai.base.src.com.basetest.R;
import cai.base.src.com.basetest.test.mvp.chat.ChatActivity;
import cai.base.src.com.basetest.test.mvp.chat.ChatMainFragment;
import cai.base.src.com.basetest.test.mvp.memo.MemoFragment;
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
                startChat();
                break;
            case R.id.navigation_item_file://FTP
                break;
            case R.id.navigation_item_notepad://笔记本
                startMome();
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

    /**启动聊天*/
    private void startChat(){
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new ChatMainFragment()).commit();
    }

    /**启动备忘录*/
    private void startMome(){
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new MemoFragment()).commit();
    }

    @Override
    public void init() {
        startNews();
    }
}
