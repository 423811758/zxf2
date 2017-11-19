package com.wzd.zxf.service;

import android.app.Application;

import com.pgyersdk.crash.PgyCrashManager;
import com.wzd.wolf_open_resource.data.GlobalData;
import com.wzd.zxf.tools.BmobUtil;

/**
 * Created by wzd on 2017/9/9.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GlobalData.globalContext = this;
        BmobUtil.initBomb(this);
        PgyCrashManager.register(this);
    }
}
