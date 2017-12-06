package cai.test.com.base.view.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;
import cai.test.com.base.R;
import cai.test.com.base.presenter.Presenter;
import cai.test.com.base.view.adapter.CommonAdapter;
import cai.test.com.base.view.adapter.HolderView;
import cai.test.com.base.interfaces.BaseAdapterInterfaces;
import cai.test.com.base.interfaces.BaseAdapterOnClickInterfaces;
import cai.test.com.base.interfaces.RecyclerViewRefresh;
import cai.test.com.base.view.widget.xrecyclerview.ProgressStyle;
import cai.test.com.base.view.widget.xrecyclerview.XRecyclerView;

/**
 * Created by Administrator on 2017/10/23.
 * 列表展示数据
 */
public abstract class BaseListFragment<T extends Object,P extends Presenter> extends BasicsFragment<P>  implements RecyclerViewRefresh,BaseAdapterInterfaces<T>,BaseAdapterOnClickInterfaces{
    /**
     * grid布局与瀑布流布局默认行数
     */
    private int mSpanCount = 1;
    /**
     * 默认为0单行布局
     */
    private int mListType = 0;
    /**上拉下拉加载布局控件*/
    private XRecyclerView mRecyclerView;
    /**适配器*/
    private CommonAdapter<T> mAdapter;
    /**数据列表*/
    private List<T> list;
    /**
     * 排列方式默认垂直
     */
    private boolean mIsVertical = true;
    /**是否允许下拉刷新（默认不允许）*/
    private boolean isLoadMore = false;
    /** 是否允许上拉加载(默认允许)*/
    private boolean isRefresh = true;

    /**返回对应的RecyclerView*/
    public XRecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    /**返回RecyclerView对应的适配器*/
    public CommonAdapter<T> getAdapter() {
        return mAdapter;
    }

    public List<T> getList() {
        return list;
    }


    /**显示时不需要该布局*/
    @Override
    public int getContentViewId() {
        return R.layout.fragment_base_list;
    }

    @Override
    protected View initView(LayoutInflater inflater, Bundle savedInstanceState) {
        mView = inflater.inflate(getContentViewId(),null);
        mRecyclerView = mView.findViewById(R.id.recyclerview);
        list = new ArrayList<>();
        mAdapter = new CommonAdapter<>(list,context,this,this);
        chooseListType(BaseListType.STAGGERED_GRID_LAYOUT_MANAGER,true);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        setLoadMoreAndRefresh(isLoadMore,isRefresh);
        mRecyclerView.setLoadingListener(this);
        return mView;
    }

    /**
     * 添加数据
     * @param data
     *      需要添加的数据
     * @param isClear
     *       是否需要清空之前的数据
     */
    protected void addData(ArrayList<T>  data,boolean isClear){
       if (isClear){
           list.clear();
       }
       list.addAll(data);
        getAdapter().notifyDataSetChanged();
    }




    /**设置是否上拉或者下拉*/
    protected void setLoadMoreAndRefresh(boolean isLoadMore,boolean isRefresh){
        mRecyclerView.setLoadingMoreEnabled(isLoadMore);
        mRecyclerView.setPullRefreshEnabled(isRefresh);
    }
    /**
     * @param listType 选择布局种类
     */
    protected void chooseListType(int listType, boolean isVertical) {
        switch (listType) {
            case BaseListType.LINEAR_LAYOUT_MANAGER:
                //设置布局管理器
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

                linearLayoutManager.setOrientation(isVertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);

                mRecyclerView.setLayoutManager(linearLayoutManager);
                break;
            case BaseListType.GRID_LAYOUT_MANAGER:

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), mSpanCount);

                gridLayoutManager.setOrientation(isVertical ? GridLayoutManager.VERTICAL : GridLayoutManager.HORIZONTAL);

                mRecyclerView.setLayoutManager(gridLayoutManager);
                break;
            case BaseListType.STAGGERED_GRID_LAYOUT_MANAGER:
                //设置布局管理器
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager
                        (mSpanCount, isVertical ? StaggeredGridLayoutManager.VERTICAL : StaggeredGridLayoutManager.HORIZONTAL);

                mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
                break;
            default:
                //设置布局管理器
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

                layoutManager.setOrientation(isVertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);

                mRecyclerView.setLayoutManager(layoutManager);
                break;
        }
        mRecyclerView.setAdapter(mAdapter);
    }




    /**设置对应的RecyclerView的显示类别值*/
 public class BaseListType{
        public static final int LINEAR_LAYOUT_MANAGER = 0;
        public static final int GRID_LAYOUT_MANAGER = 1;
        public static  final int STAGGERED_GRID_LAYOUT_MANAGER = 2;
  }


}
