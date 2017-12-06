package cai.base.src.com.basetest.test.mvp.news;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/12/5.
 */

public class NewsAdapter extends FragmentPagerAdapter {
    private Context context;
    private ArrayList<NewsBean> list;

    public NewsAdapter(FragmentManager fm, Context context, ArrayList<NewsBean> list) {
        super(fm);
        this.context = context;
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        NewsFragment fragment = new NewsFragment();
        fragment.setTitle(list.get(position));
        return fragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    // 此方法用来显示Tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getTitle();
    }
}
