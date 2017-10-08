package com.wzd.wolf_open_resource.data;

public class GlobalConstant {
	/** 通知栏标题 */
	public final static String Notifacation_Title = "app";
	public final static String REAL_PACKAGE_NAME = "com.wzd.openresource";
	public static final String EXTRA_BUNDLE = "launchBundle";
	/** 本机SD卡中文件的存储目录 */
	public final static String path = "/openresource";
	/** SDCARD首级目录 */
	public final static String DIR_HEAD = "openresource";
	/** 图片文件目录 */
	public static final String IMAGE_DIR = "openresource/image";
	/** 网络图片缓存路径 写死在包中，不能只修改这里 */
	public static final String IMAGE_CACHE_DIR = "openresource/cache";
	/** 更新文件名 */
	public final static String APK_NAME = "mengziyue.apk";
	// 存储空间
	/** 未发现SD卡 */
	public static final int SDCARD_IS_UNMOUNT = 1001;
	/** 存储空间不足 */
	public static final int SDCARD_IS_FULL = 1002;
	/** 显示的通知ID */
	public final static int ONGOING_NOTIFICATION_ID = 2000;// 显示的通知ID
	public final static int UPGRADE_NOTIFICATION_ID = 2001;// 下载更新通知栏ID
	public final static String BUNDLE_MEAL_DETAIL_ID = "bundle_meal_detail_id";
	/** 图片预览加载失败(大图） */
	public static final String IMAGE_PREVIEW_LOAD_FAILURE = "image_preview_load_failure";
	/** 加载webview的类型 */
	public static final String WEBVIEW_TYPE = "WEBVIEW_TYPE";
	public static final int TYPE_WX_PAY = 100;
	public static final int TYPE_WX_RECHARGE = 101;

	// *********************** 和帐号无关的设置 **********************************
	public static final String SP_SETTING = "setting";
	public static final String SP_FIRST_START = "is_first_start";
	/** 程序版本 */
	public static final String SP_VERSION = "version";
	/** 是否需要升级 */
	public static final String SP_IS_UPGRADE = "isupgrade";
	/** 手机屏幕高 */
	public static final String SP_GLOBAL_SCREEN_HEIGHT = "globalscreenheight";
	/** 手机屏幕宽 */
	public static final String SP_GLOBAL_SCREEN_WIDTH = "globalscreenwidth";
	/** 手机可见屏幕高，包括状态栏 */
	public static final String SP_GLOBAL_SCREEN_VISIABLE_HEIGHT = "globalscreenvisiableheight";
	/** 省市数据 */
	public static final String SP_PROVINCE_DATA_STR = "province_data_str";
	/** banner数据jsonarray的字符串 */
	public static final String SP_BANNER_JSON_LIST_STR = "banner_json_list_str";
	/** 广告 */
	public static final String SP_ADVERT_JSON_STR = "advert_json_str";
	public static final String SP_IS_SHARE_WEIXIN = "is_share_weixin";
	public static final String SP_IS_GO_TO_MARKET = "is_go_to_market";

}
