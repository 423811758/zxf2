package com.wzd.wolf_open_resource.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.wzd.wolf_open_resource.data.CommonGlobalData;
import com.wzd.wolf_open_resource.data.GlobalData;

/**
 * 网络连接工具
 * */
public class CommonNetUtil {

	/**
	 * 判断系统的网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		if (context == null) {
			context = GlobalData.globalContext;
			if (context == null)
				return false;
		}
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 判断系统的网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isSystemNetAvailable(Context context) {
		if (context == null) {
			context = GlobalData.globalContext;
			if (context == null)
				return true;
		}
		ConnectivityManager cManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cManager.getActiveNetworkInfo();
		return (info != null && info.isAvailable() && info.isConnected());
	}

	/**
	 * 判断是否能使用wifi功能
	 * 
	 * @param mContext
	 *            当前环境上下文对象
	 * @return 是否能使用wifi功能
	 */
	public static boolean isWifi(Context mContext) {
		boolean isWifi = false;
		if (mContext == null)
			mContext = CommonGlobalData.globalContext;
		try {
			ConnectivityManager conn = (ConnectivityManager) mContext
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = conn.getActiveNetworkInfo();
			if (networkInfo != null && networkInfo.isAvailable()
					&& networkInfo.isConnected()) {
				int nType = networkInfo.getType();
				if (nType == ConnectivityManager.TYPE_WIFI) {
					isWifi = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isWifi;
	}

	/**
	 * 判断是否使用2g功能
	 * 
	 * @param mContext
	 *            当前环境上下文对象
	 * @return 是否能使用wifi功能
	 */
	public static boolean is2G(Context mContext) {
		boolean twoG = false;
		if (mContext == null)
			mContext = CommonGlobalData.globalContext;
		try {
			ConnectivityManager conn = (ConnectivityManager) mContext
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = conn.getActiveNetworkInfo();
			if (networkInfo != null && networkInfo.isAvailable()
					&& networkInfo.isConnected()) {
				int nType = networkInfo.getType();
				if (nType == ConnectivityManager.TYPE_MOBILE) {
					switch (networkInfo.getSubtype()) {
					case TelephonyManager.NETWORK_TYPE_EDGE:// 移动的2G是EGDE
					case TelephonyManager.NETWORK_TYPE_GPRS:// 联通的2G是GPRS
					case TelephonyManager.NETWORK_TYPE_CDMA:// 电信的2G为CDMA
						twoG = true;
						break;
					default:
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return twoG;
	}
}
