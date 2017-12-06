package cai.base.src.com.basetest.test.mvp.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import cai.base.src.com.basetest.R;
import cai.base.src.com.basetest.test.db.NewsDb;
import cai.test.com.base.annotation.RequirePresenter;
import cai.test.com.base.view.activitys.WebActivity;
import cai.test.com.base.view.adapter.HolderView;
import cai.test.com.base.view.fragments.BaseListFragment;
import cai.test.com.base.x;

/**
 * Created by Administrator on 2017/10/23.
 */
@RequirePresenter(NewsListPresenter.class)
public class NewsFragment extends BaseListFragment<NewsDb,NewsListPresenter>{

    /**是否清除表中数据*/
    private boolean isClear = true;

    private NewsBean title = null;

    private int page = 0;

    private int pageSize = 10;

    public void setTitle(NewsBean title) {
        this.title = title;
    }

    @Override
    public void init() {

        setLoadMoreAndRefresh(true,true);
        getPresenter().getData(title,page,pageSize);
    }




    public static Fragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public void addData(ArrayList<NewsDb> list){
        addData(list,isClear);
    }

    @Override
    public void onItemClick(View view, HolderView holder, int position) {

        WebActivity.launch(context,getList().get(position).getUrl(),getList().get(position).getCategory());

    }

    @Override
    public boolean onItemLongClick(View view, HolderView holder, int position) {

        return true;
    }

    @Override
    public void bindData(HolderView holder, NewsDb data, int position) {
        holder.setText(R.id.news_title,data.getTitle());
        holder.setText(R.id.time,data.getDate());
        holder.setText(R.id.textView2,data.getAuthor_name());
        x.image().bind((ImageView)holder.findViewById(R.id.icon),data.getThumbnail_pic_s());

    }

    @Override
    public void onRefresh() {
        isClear = true;
        page = 0;
        getPresenter().getData(title,page,pageSize);

    }

    @Override
    public void onLoadMore() {
        isClear = false;
        page ++;
        getPresenter().getData(title,page,pageSize);

    }




    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.item_fragment_news;
    }
}
