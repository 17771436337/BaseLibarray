package cai.base.src.com.basetest.base.activitys;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import cai.base.src.com.basetest.R;
import cai.base.src.com.basetest.enums.ActivityTypeEnum;

/**
 * Created by Administrator on 2017/10/24.
 */

public abstract class BaseTitleActivity extends BasicsActivity {

    /** 标题返回按钮显示 */
    protected abstract boolean isShowBack();

    /** 标题名字*/
    protected abstract String getTitleName();

    /**
     * 返回键监听事件
     */
    protected abstract void onBackClick();


    @Override
    protected void onTitleActivity(Bundle savedInstanceState) {
        setContentView(R.layout.layout_base_activity);
        setTableView();
    }



    /**
     * 初始化视图，并且添加对应的标题
     * @return
     */
    private void setTableView(){
        View tableView = findViewById(R.id.activity_base_title);
        ImageView back = tableView.findViewById(R.id.table_back);
        TextView name = tableView.findViewById(R.id.table_name);

        FrameLayout content = (FrameLayout)findViewById(R.id.activity_base_content);
        content.addView(LayoutInflater.from(this).inflate(contentViewId,null));
            if (!TextUtils.isEmpty(getTitleName())) {//如果内容不为空，则设置标题
                name.setText(getTitleName());
            }
            if (isShowBack()) {//判断返回键是否显示
                /**返回键的点击事件*/
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackClick();
                    }
                });
                back.setVisibility(View.VISIBLE);
            } else {
                back.setVisibility(View.GONE);
            }
            tableView.setVisibility(View.VISIBLE);
    }




    @Override
    protected ActivityTypeEnum getActivityType() {
        return ActivityTypeEnum.TitleActivity;
    }

    @Override
    protected void onHomeActivity(Bundle savedInstanceState) {}

    @Override
    protected void onFragmentActivity(Bundle savedInstanceState) {}

    @Override
    protected void onSpaceActivity(Bundle savedInstanceState) {}


}