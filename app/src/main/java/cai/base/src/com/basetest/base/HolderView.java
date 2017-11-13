package cai.base.src.com.basetest.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/11/13.
 */

public class HolderView extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;

    public HolderView(Context context, View itemView, ViewGroup parent)
    {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<View>();
    }

    /**
     * 获取对应的Item
     * @param context
     *      上下文参数
     * @param parent
     *      父类视图组
     * @param layoutId
     *        对应的布局id
     * @return
     */
    public static HolderView get(Context context, ViewGroup parent, int layoutId)
    {

        View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        HolderView holder = new HolderView(context, itemView, parent);
        return holder;
    }





    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T findViewById(int viewId)
    {
        View view = mViews.get(viewId);
        if (view == null)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }


    /**
     * 视图id
     * @param viewId
     * 布局背景
     * @param resId
     * @return
     */
    public HolderView setBackground(int viewId, int resId) {
        View view = findViewById(viewId);
        view.setBackgroundResource(resId);
        return this;
    }

    /**设置TestView的文字*/
    public HolderView setText(int viewId, String text)
    {
        TextView tv = findViewById(viewId);
        tv.setText(text);
        return this;
    }

    /**设置图片*/
    public HolderView setImageResource(int viewId, int resId)
    {
        ImageView view = findViewById(viewId);
        view.setImageResource(resId);
        return this;
    }

    /**设置布局的单机事件*/
    public HolderView setOnClickListener(int viewId,View.OnClickListener listener)
    {
        View view = findViewById(viewId);
        view.setOnClickListener(listener);
        return this;
    }
}
