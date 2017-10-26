package cai.base.src.com.basetest.base.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;

import cai.base.src.com.basetest.enums.ActivityTypeEnum;

/**
 * Created by Administrator on 2017/10/24.
 */

public abstract class BaseFragmentActivity extends BasicsActivity {

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(contentViewId);
    }



    /**
     *  添加Fragment
     * @param id
     *      布局中Fragment的ID
     * @param fragment
     *        需要填的Fragment
     */
    protected void addFragment(int id, Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(id, fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }


    /**
     * 移除Fragment
     */
    protected void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
