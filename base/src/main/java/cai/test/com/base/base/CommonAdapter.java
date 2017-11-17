package cai.test.com.base.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

import cai.test.com.base.utils.LogUtil;
import cai.test.com.base.interfaces.BaseAdapterInterfaces;
import cai.test.com.base.interfaces.BaseAdapterOnClickInterfaces;


/**
 * Created by Administrator on 2017/11/13.
 * 通用适配器
 */

public class CommonAdapter<T> extends RecyclerView.Adapter<HolderView>{

    /**数据实现*/
    private List<T> data;

    /**环境配置*/
    public Context context;

    BaseAdapterOnClickInterfaces mOnClick;

    BaseAdapterInterfaces mAdapterInterfaces;

    public CommonAdapter(List<T> data, Context context, BaseAdapterOnClickInterfaces mOnClick, BaseAdapterInterfaces mAdapterInterfaces) {
        this.data = data;
        this.context = context;
        this.mOnClick = mOnClick;
        this.mAdapterInterfaces = mAdapterInterfaces;
    }

    @Override
    public HolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        return HolderView.get(context,parent,mAdapterInterfaces.getItemLayoutId(viewType));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public void onBindViewHolder(final HolderView holder, final int position) {
        mAdapterInterfaces.bindData(holder,position);
        //---------------------------------------------------------------
        /**列表的单机事件*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClick.onItemClick(v,holder,position);
            }
        });

        /**列表的长按事件*/
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return mOnClick.onItemLongClick(v,holder,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }else{
            LogUtil.d("BaseListFragment：列表为空");
            return 0;
        }
    }
}
