package cai.base.src.com.basetest.test.mvp.news;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import cai.base.src.com.basetest.R;
import cai.test.com.base.annotation.ViewInject;
import cai.test.com.base.view.activitys.BaseTitleActivity;
import cai.test.com.base.view.fragments.BaseListFragment;
import cai.test.com.base.view.fragments.BaseSpaceFragment;

/**
 * Created by Administrator on 2017/12/5.
 */

public class NewsListFragment extends BaseSpaceFragment {

    @ViewInject(R.id.tabLayout)
    private TabLayout tabLayout;
    @ViewInject(R.id.viewPager)
    private ViewPager viewPager;

    NewsAdapter adapter;
ArrayList<NewsBean> list ;

    @Override
    public void init() {

        list = new ArrayList<NewsBean>();
        list.add(new NewsBean("头条","top"));
        list.add(new NewsBean("社会","shehui"));
        list.add(new NewsBean("国内","guonei"));
        list.add(new NewsBean("国际","guoji"));
        list.add(new NewsBean("娱乐","yule"));
        list.add(new NewsBean("体育","tiyu"));

        list.add(new NewsBean("军事","junshi"));
        list.add(new NewsBean("科技","keji"));
        list.add(new NewsBean("财经","caijing"));
        list.add(new NewsBean("时尚","shishang"));


        adapter = new NewsAdapter(getChildFragmentManager(),context,list);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_news;
    }
}
