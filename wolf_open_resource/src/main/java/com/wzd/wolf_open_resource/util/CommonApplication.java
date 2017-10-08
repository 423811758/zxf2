package com.wzd.wolf_open_resource.util;

import android.app.Application;

import com.wzd.wolf_open_resource.data.GlobalData;


public class CommonApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		GlobalData.globalContext = getApplicationContext();
	}

}
