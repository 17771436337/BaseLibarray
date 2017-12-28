package cai.test.com.base.view.activitys;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cai.test.com.base.presenter.Presenter;
import cai.test.com.base.R;
import cai.test.com.base.view.widget.CenterTitleToolbar;
import cai.test.com.base.view.widget.toolbar.BaseToolbar;
import cai.test.com.base.view.widget.toolbar.TitleToolbar;


/**
 * Created by Administrator on 2017/10/24.
 */

public abstract class BaseTitleActivity<P extends Presenter> extends BasicsActivity<P> implements BaseToolbar.OnOptionItemClickListener {

    /** 标题返回按钮显示 */
    protected abstract boolean isShowBack();

    /** 标题名字*/
    protected abstract String getTitleName();

    private TitleToolbar mToolbar;

    /**
     * 返回键监听事件
     */
    protected abstract void onBackClick();

    public TitleToolbar getToolbar() {
        return mToolbar;
    }

    /**
     * 初始化视图，并且添加对应的标题
     * @return
     */
    private void setTableView(){
        mToolbar = (TitleToolbar) findViewById(R.id.toolbar);
        mToolbar.setBackVisible(isShowBack());
        mToolbar.setOnOptionItemClickListener(this);

        FrameLayout content = (FrameLayout)findViewById(R.id.activity_base_content);
        content.addView(LayoutInflater.from(this).inflate(getContentViewId(),null));

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.layout_base_activity);
        setTableView();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(getTitleName())) {//如果内容不为空，则设置标题
            mToolbar.setTitle(getTitleName());
        }
    }

    @Override
    public void onOptionItemClick(View v) {
        int i = v.getId();
        if (i == R.id.back) {//返回
            onBackClick();
        }
    }





}
