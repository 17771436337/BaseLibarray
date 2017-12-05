package cai.base.src.com.basetest.test.mvp.news;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import cai.base.src.com.basetest.R;
import cai.test.com.base.annotation.ViewInject;
import cai.test.com.base.view.activitys.BaseTitleActivity;
import cai.test.com.base.view.fragments.BaseSpaceFragment;

/**
 * Created by Administrator on 2017/12/5.
 */

public class NewsListFragment extends BaseSpaceFragment {

//    @ViewInject(R.id.tabLayout)
    TabLayout tabLayout;
//    @ViewInject(R.id.viewPager)
    ViewPager viewPager;

    NewsAdapter adapter;

    private String[] title = {"头条","社会","国内","国际","娱乐","体育","军事","科技","财经","时尚"};

    @Override
    public void init() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        adapter = new NewsAdapter(getChildFragmentManager(),context,title);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_news;
    }
}
