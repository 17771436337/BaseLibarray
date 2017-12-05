package cai.base.src.com.basetest.test.mvp.news;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Administrator on 2017/12/5.
 */

public class NewsAdapter extends FragmentPagerAdapter {
    private Context context;
    private String[] title;

    public NewsAdapter(FragmentManager fm, Context context, String[] title) {
        super(fm);
        this.context = context;
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {
        NewsFragment fragment = new NewsFragment();
        fragment.setTitle(title[position]);
        return fragment;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    // 此方法用来显示Tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
