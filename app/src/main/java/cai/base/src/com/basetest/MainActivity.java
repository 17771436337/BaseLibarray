package cai.base.src.com.basetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import cai.base.src.com.basetest.annotation.ActivityFragmentInject;
import cai.base.src.com.basetest.annotation.FindById;
import cai.base.src.com.basetest.base.BaseActivity;


@ActivityFragmentInject(contentViewId = R.layout.activity_main,
        isBackShow = false,
        isLoading = false,
        isTable = true,
        tableName = "测试")
public class MainActivity extends BaseActivity {
    @FindById(ViewId = R.id.text,defaultString = "测试")
    private ImageButton text;

    @Override
    protected void initData() {
//        text.setText("测试成功");
    }

    @Override
    protected void onBackClick() {
        Toast.makeText(this,"返回成功",Toast.LENGTH_SHORT).show();
    }
}
