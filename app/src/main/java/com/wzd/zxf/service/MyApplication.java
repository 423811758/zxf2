package com.wzd.zxf.service;

import android.app.Application;

import com.pgyersdk.crash.PgyCrashManager;

/**
 * Created by wzd on 2017/9/9.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PgyCrashManager.register(this);
    }
}
