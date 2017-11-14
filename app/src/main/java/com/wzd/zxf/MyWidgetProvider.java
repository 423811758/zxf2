package com.wzd.zxf;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.wzd.zxf.fragment.TimeFragment;
import com.wzd.zxf.tools.DateUtil;
import com.wzd.zxf.tools.SPUtil;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyWidgetProvider extends AppWidgetProvider {
    private static Timer myTimer;

    public static final String CLICK_ACTION = "com.wzd.zxf.action.CLICK";
    public static final String UPDATE_WIDGET_ACTION = "com.wzd.zxf.action.appWidgetUpdate";

    private static long mOldTime;

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);

        //在插件被创建的时候这里会被调用， 所以我们在这里开启一个timer 每秒执行一次
//        MyTask mMyTask = new MyTask(context);
//        myTimer = new Timer();
//        myTimer.schedule(mMyTask, 1000, 1000);
        context.startService(MyService.getIntent(context));
    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        if(intent.getAction().equals(UPDATE_WIDGET_ACTION))
//        {
//            mOldTime = intent.getLongExtra("OLD_TIME", 0);
//            if(mOldTime <= 0){
//                mOldTime = Long.parseLong(String.valueOf(SPUtil.get(context, TimeFragment.OLD_DATE, 1L)));
//            }
//            long lastDate = mOldTime - System.currentTimeMillis();
//            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
//            rv.setTextViewText(R.id.widget_tv, DateUtil.getLastDate(Integer.parseInt(String.valueOf(lastDate/1000))));
//
//            //将该界面显示到插件中
//            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
//            ComponentName componentName = new ComponentName(context,MyWidgetProvider.class);
//            appWidgetManager.updateAppWidget(componentName, rv);
//        }
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    class MyTask extends TimerTask
    {

        private Context mcontext = null;
        private Intent intent = null;

        public MyTask(Context context) {

            //新建一个要发送的Intent
            mcontext = context;
            intent = new Intent();
            intent.setAction(UPDATE_WIDGET_ACTION);
        }
        @Override
        public void run()
        {
            //发送广播(由onReceive来接收)
            mcontext.sendBroadcast(intent);
        }

    }
}
