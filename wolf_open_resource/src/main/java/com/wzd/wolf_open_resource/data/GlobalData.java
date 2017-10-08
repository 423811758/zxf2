package com.wzd.wolf_open_resource.data;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

import com.wzd.wolf_open_resource.util.AndroidUtil;
import com.wzd.wolf_open_resource.util.SystemSetting;

public class GlobalData extends CommonGlobalData {

	public static final String TAG = "com.kjcrm.mengziyue";
	/** 是否调试模式 */
	public static final boolean isDebug = true;
	/** 获取设备号 */
	public static String IMEI_NUM = "";
	/** 每页获取的图片数量 */
	public static final int ITEM_COUNT = 30;

	public static String getCurrentIMEI() {
		if (IMEI_NUM.endsWith("")) {
			IMEI_NUM = AndroidUtil.getDeviceIMEI();
		}
		return IMEI_NUM;
	}

	/**
	 * 获得屏幕高
	 * 
	 * @param islandscape
	 *            TODO
	 */
	public static int getScreenHeight(boolean islandscape) {
		if (islandscape) {
			return getScreenWidth(false);
		}
		if (SystemSetting.getInstance().screenHeight != 0)
			return SystemSetting.getInstance().screenHeight;
		WindowManager manager = (WindowManager) GlobalData.globalContext
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		SystemSetting.getInstance().setGlobalScreenHeight(
				Math.max(display.getWidth(), display.getHeight()));
		return SystemSetting.getInstance().screenHeight;
	}

	/**
	 * 获得屏幕宽
	 * 
	 * @param islandscape
	 *            TODO
	 */
	public static int getScreenWidth(boolean islandscape) {
		if (islandscape) {
			return getScreenHeight(false);
		}
		if (SystemSetting.getInstance().screenWidth != 0)
			return SystemSetting.getInstance().screenWidth;
		WindowManager manager = (WindowManager) GlobalData.globalContext
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		SystemSetting.getInstance().setGlobalScreenWidth(
				Math.min(display.getWidth(), display.getHeight()));
		return SystemSetting.getInstance().screenWidth;
	}

}
