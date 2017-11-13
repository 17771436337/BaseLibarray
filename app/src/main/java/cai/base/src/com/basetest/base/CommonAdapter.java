package cai.base.src.com.basetest.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.common.util.LogUtil;

import java.util.List;

import cai.base.src.com.basetest.interfaces.BaseAdapterInterfaces;
import cai.base.src.com.basetest.interfaces.BaseAdapterOnClickInterfaces;
import cai.base.src.com.basetest.interfaces.BaseViewInterrfaces;

/**
 * Created by Administrator on 2017/11/13.
 * 通用适配器
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<HolderView> implements BaseAdapterOnClickInterfaces,BaseAdapterInterfaces {

    private List<T> data;

    public Context context;

    public CommonAdapter(List<T> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public HolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        return HolderView.get(context,parent,getItemLayoutId(viewType));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public void onBindViewHolder(final HolderView holder, final int position) {
        bindData(holder,position);
        //---------------------------------------------------------------
        /**列表的单机事件*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(v,holder,position);
            }
        });

        /**列表的长按事件*/
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return onItemLongClick(v,holder,position);
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
