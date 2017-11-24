package cai.base.src.com.basetest.test;

import cai.test.com.base.manger.AppManger;

/**
 * Created by Administrator on 2017/11/13.
 */

public class App extends AppManger {
    @Override
    public String getDBName() {
        return Config.DBName;
    }

    @Override
    protected String getDBPath() {
        return Config.DBPath;
    }
}
