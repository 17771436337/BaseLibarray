package cai.base.src.com.basetest.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.SwipeRefreshHeaderLayout;

import cai.base.src.com.basetest.R;

/**
 * Created by Administrator on 2017/10/25.
 */

public class CustomRefreshHeadView extends SwipeRefreshHeaderLayout {
    public CustomRefreshHeadView(Context context) {
        this(context, null, 0);
    }

    public CustomRefreshHeadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRefreshHeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**初始化*/
    private void init() {
        //这里的原理就是简单的动态布局添加
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = View.inflate(getContext(), R.layout.layout_list_head, null);
        addView(view, lp);

    }

    @Override
    public void onRefresh() {
        Log.i("test","CustomRefreshHeadView:onRefresh...");
    }

    @Override
    public void onPrepare() {
        Log.i("test","CustomRefreshHeadView:onPrepare...");
    }

    @Override
    public void onMove(int i, boolean b, boolean b1) {
        Log.i("test","CustomRefreshHeadView:onMove...i:"+i+",b:"+b+",b1:"+b1);
    }

    @Override
    public void onRelease() {
        Log.i("test","CustomRefreshHeadView:onRelease...");
    }

    @Override
    public void onComplete() {
        Log.i("test","CustomRefreshHeadView:onComplete...");
    }

    @Override
    public void onReset() {
        Log.i("test","CustomRefreshHeadView:onReset...");
    }
}
