package com.wzd.wolf_open_resource.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.wzd.wolf_open_resource.data.GlobalConstant;
import com.wzd.wolf_open_resource.data.GlobalData;

/**
 * 系统设置模型类 增加属性时修改loadSystemProperty方法(根据具体的需求进行修改)
 */
public class SystemSetting {

	private static SystemSetting property;
	private SharedPreferences sp;
	/** 软件版本数字信息 */
	public String ver;
	/** 手机屏幕高 */
	public int screenHeight = 0;
	/** 手机屏幕宽 */
	public int screenWidth = 0;
	/** 手机可见屏幕高，包括状态栏 */
	public int screenVisiableHeight = 0;
	/** 省市 */
	public String provinceDataStr = "";

	public boolean isFirstStart = true;
	/** banner数据jsonarray的字符串 */
	public String bannerListStr = "";
	public String homeAdListStr = "";
	/** 上一次选择相册的路径ID */
	public String choosePhotoDirId;

	public static SystemSetting getInstance() {
		if (property == null) {
			property = new SystemSetting();
			property.loadSystemProperty(GlobalData.globalContext);
		}
		return property;
	}

	private SystemSetting() {
	}

	/**
	 * 获取配置文件写对象
	 * 
	 * @return
	 */
	private Editor getSPEditor() {
		Editor editor = sp.edit();
		return editor;
	}

	/**
	 * 读取配置文件中的系统属性
	 */
	private void loadSystemProperty(Context context) {
		if (sp == null) {
			sp = context.getSharedPreferences(GlobalConstant.SP_SETTING,
					Context.MODE_PRIVATE);
			isFirstStart = sp.getBoolean(GlobalConstant.SP_FIRST_START, true);
			// 获得版本信息
			// ver = sp.getString(GlobalConstant.SP_VERSION, "");
			ver = AndroidUtil.getVersion(context);

			screenHeight = sp.getInt(GlobalConstant.SP_GLOBAL_SCREEN_HEIGHT, 0);
			screenWidth = sp.getInt(GlobalConstant.SP_GLOBAL_SCREEN_WIDTH, 0);
			screenVisiableHeight = sp.getInt(
					GlobalConstant.SP_GLOBAL_SCREEN_VISIABLE_HEIGHT, 0);
			provinceDataStr = sp.getString(GlobalConstant.SP_PROVINCE_DATA_STR,
					"");
			bannerListStr = sp.getString(
					GlobalConstant.SP_BANNER_JSON_LIST_STR, "");
		}
	}

	/**
	 * 设置版本
	 *
	 * @param ver
	 */
	public void setVersion(String ver) {
		Editor editor = getSPEditor();
		this.ver = ver;
		editor.putString(GlobalConstant.SP_VERSION, ver);
		editor.commit();
	}

	/**
	 * 设置手机屏幕高
	 * 
	 * @param globalscreenheight
	 */
	public void setGlobalScreenHeight(int globalscreenheight) {
		this.screenHeight = globalscreenheight;
		Editor editor = getSPEditor();
		editor.putInt(GlobalConstant.SP_GLOBAL_SCREEN_HEIGHT,
				globalscreenheight);
		editor.commit();
	}

	/**
	 * 设置手机屏幕宽
	 * 
	 * @param globalscreenwidth
	 */
	public void setGlobalScreenWidth(int globalscreenwidth) {
		this.screenWidth = globalscreenwidth;
		Editor editor = getSPEditor();
		editor.putInt(GlobalConstant.SP_GLOBAL_SCREEN_WIDTH, globalscreenwidth);
		editor.commit();
	}

	/**
	 * 设置手机可见屏幕高，包括状态栏
	 * 
	 * @param globalscreenvisiableheight
	 */
	public void setGlobalScreenVisiableHeight(int globalscreenvisiableheight) {
		this.screenVisiableHeight = globalscreenvisiableheight;
		Editor editor = getSPEditor();
		editor.putInt(GlobalConstant.SP_GLOBAL_SCREEN_VISIABLE_HEIGHT,
				globalscreenvisiableheight);
		editor.commit();
	}

	/**
	 * 设置省市数据
	 * 
	 */
	public void setProvinceDataStr(String provinceDataStr) {
		this.provinceDataStr = provinceDataStr;
		Editor editor = getSPEditor();
		editor.putString(GlobalConstant.SP_PROVINCE_DATA_STR, provinceDataStr);
		editor.commit();
	}

	/**
	 * 设置banner数据jsonarray的字符串
	 * 
	 */
	public void setBannerListStr(String bannerListStr) {
		this.bannerListStr = bannerListStr;
		Editor editor = getSPEditor();
		editor.putString(GlobalConstant.SP_BANNER_JSON_LIST_STR, bannerListStr);
		editor.commit();
	}

	/**
	 * 设置是否是第一次启动app
	 * 
	 */
	public void setIsFirstStart(boolean isFirstStart) {
		this.isFirstStart = isFirstStart;
		Editor editor = getSPEditor();
		editor.putBoolean(GlobalConstant.SP_FIRST_START, isFirstStart);
		editor.commit();
	}

}
