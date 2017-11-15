package cai.test.com.base.interfaces;

import android.content.Intent;

/**
 * Created by Administrator on 2017/11/15.
 * Activity回调方法
 */

public interface ActivityResult {

    /**onActivityResult的回调*/
    public void onActivityResult(int requestCode, int resultCode, Intent data);
}
