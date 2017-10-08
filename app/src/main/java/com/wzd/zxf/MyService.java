package com.wzd.zxf;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import com.wzd.zxf.fragment.TimeFragment;
import com.wzd.zxf.tools.DateUtil;
import com.wzd.zxf.tools.SPUtil;

/**
 * Created by wzd on 2017/6/6.
 */

public class MyService extends Service {

    public static final String MY_SERVICE_ACTION = "com.wzd.zxf.action.myService";
    private static long mOldTime;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        System.out.println("onStart");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand");
        mOldTime = intent.getLongExtra("OLD_TIME", 0);
        if(mOldTime <= 0){
            mOldTime = Long.parseLong(String.valueOf(SPUtil.get(this, TimeFragment.OLD_DATE, 1L)));
        }
        long lastDate = mOldTime - System.currentTimeMillis();
        RemoteViews rv = new RemoteViews(this.getPackageName(), R.layout.widget_layout);
        rv.setTextViewText(R.id.widget_tv, DateUtil.getLastDate(Integer.parseInt(String.valueOf(lastDate/1000))));

        //将该界面显示到插件中
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        ComponentName componentName = new ComponentName(this,MyWidgetProvider.class);
        appWidgetManager.updateAppWidget(componentName, rv);
        return super.onStartCommand(intent, flags, startId);
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, MyService.class);
        intent.setAction(MY_SERVICE_ACTION);
        return intent;
    }
}
