package cai.base.src.com.basetest.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreFooterLayout;
import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

import cai.base.src.com.basetest.R;

/**
 * Created by Administrator on 2017/10/25.
 */

public class CustomRefreshFootView extends SwipeLoadMoreFooterLayout {

    public CustomRefreshFootView(Context context) {
        this(context, null, 0);
    }


    public CustomRefreshFootView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRefreshFootView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**初始化*/
    private void init() {
        //这里的原理就是简单的动态布局添加
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = View.inflate(getContext(), R.layout.layout_list_foot, null);
        addView(view, lp);

    }

    public void onLoadMore() {
        Log.i("test","CustomRefreshFootView:onLoadMore...");
    }
    @Override
    public void onPrepare() {
        Log.i("test","CustomRefreshFootView:onPrepare...");
    }

    @Override
    public void onMove(int i, boolean b, boolean b1) {
        Log.i("test","CustomRefreshFootView:onMove...i:"+i+",b:"+b+",b1:"+b1);
    }

    @Override
    public void onRelease() {
        Log.i("test","CustomRefreshFootView:onRelease...");
    }

    @Override
    public void onComplete() {
        Log.i("test","CustomRefreshFootView:onComplete...");
    }

    @Override
    public void onReset() {
        Log.i("test","CustomRefreshFootView:onReset...");
    }
}
