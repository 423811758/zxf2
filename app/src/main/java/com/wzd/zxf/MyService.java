package com.wzd.zxf;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import com.wzd.wolf_open_resource.data.GlobalData;
import com.wzd.wolf_open_resource.util.Log4JUtil;
import com.wzd.zxf.fragment.TimeFragment;
import com.wzd.zxf.tools.DateUtil;
import com.wzd.zxf.tools.SPUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wzd on 2017/6/6.
 */

public class MyService extends Service {

    public static final String MY_SERVICE_ACTION = "com.wzd.zxf.action.myService";
    private static long mOldTime;
    // 定时器
    private Timer timer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
        /**
         * 参数：1.事件2.延时事件3.执行间隔事件
         */
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                updateView();
            }
        }, 0, 1000);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log4JUtil.debugInfo("onStart");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log4JUtil.debugInfo("onStartCommand");
//        mOldTime = intent.getLongExtra("OLD_TIME", 0);
//        if(mOldTime <= 0){
//            mOldTime = Long.parseLong(String.valueOf(SPUtil.get(this, TimeFragment.OLD_DATE, 1L)));
//        }
//        long lastDate = mOldTime - System.currentTimeMillis();
//        RemoteViews rv = new RemoteViews(this.getPackageName(), R.layout.widget_layout);
//        rv.setTextViewText(R.id.widget_tv, DateUtil.getLastDate(Integer.parseInt(String.valueOf(lastDate/1000))));
//
//        //将该界面显示到插件中
//        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
//        ComponentName componentName = new ComponentName(this,MyWidgetProvider.class);
//        appWidgetManager.updateAppWidget(componentName, rv);
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 更新事件的方法
     */
    private void updateView() {
        mOldTime = 0;
        if(mOldTime <= 0){
            mOldTime = Long.parseLong(String.valueOf(SPUtil.get(GlobalData.globalContext, TimeFragment.OLD_DATE, 1L)));
        }
        long lastDate = mOldTime - System.currentTimeMillis();
        RemoteViews rv = new RemoteViews(GlobalData.globalContext.getPackageName(), R.layout.widget_layout);
        rv.setTextViewText(R.id.widget_tv, DateUtil.getLastDate(Integer.parseInt(String.valueOf(lastDate/1000))));

        //将该界面显示到插件中
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(GlobalData.globalContext);
        ComponentName componentName = new ComponentName(GlobalData.globalContext,MyWidgetProvider.class);
        appWidgetManager.updateAppWidget(componentName, rv);
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, MyService.class);
        intent.setAction(MY_SERVICE_ACTION);
        return intent;
    }
}
