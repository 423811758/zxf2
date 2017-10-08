package com.wzd.wolf_open_resource.http;

public class BaseApi {

	// 陈
	// public static final String URL_BASE = "http://192.168.129.112:8080/";
	// public static final String URL_BASE = "http://192.168.146.18:8080/";
	public static final String URL_BASE = "http://weduoduo.net/";
	public static final String URL_BASE_API = URL_BASE + "api/";
	// 壁纸
	public static final String URL_WALLPAPER_NEW = "wallpaper/new.do";
	public static final String URL_WALLPAPER_HOT = "wallpaper/hot.do";
	public static final String URL_WALLPAPER_CLASS = "wallpaper_class/get.do";
	public static final String URL_WALLPAPER_ALBUM = "wallpaper_album/get.do";
	public static final String URL_WALLPAPER = "wallpaper/get.do";
	public static final String URL_HOT_WORDS = "hotwords/get.do";
	public static final String URL_WALLPAPER_SEARCH = "wallpaper/search.do";
	public static final String URL_WALLPAPER_ADD_PV = "wallpaper/addpv.do";
	public static final String URL_WALLPAPER_ADD_DOWANLOAD = "wallpaper/adddownload.do";
	// 精选
	public static final String URL_SUGGEST_CLASS = "suggest_class/get.do";
	public static final String URL_SUGGEST_ALBUM = "suggest_album/get.do";
	public static final String URL_SUGGEST_IMAGE = "suggest_image/get.do";
	public static final String URL_SUGGEST_IMAGE_ADD_PV = "suggest_image/addpv.do";
	public static final String URL_SUGGEST_IMAGE_ADD_DOWNLOAD = "suggest_image/adddownload.do";
	// 专辑
	public static final String URL_ALBUM_CLASS = "album_class/get.do";
	public static final String URL_ALBUM = "album/get.do";
	public static final String URL_ALBUM_IMAGE = "album_image/get.do";
	public static final String URL_ALBUM_IMAGE_ADD_PV = "album_image/addpv.do";
	public static final String URL_ALBUM_IMAGE_ADD_DOWNLOAD = "album_image/adddownload.do";

	// 广告
	public static final String URL_ADVERT = "advert/get.do";
	public static final String URL_ADVERT_ADD_PV = "advert/addpv.do";
	public static final String URL_ADVERT_ADD_CLICK = "advert/addclick.do";
	// 系统配置
	public static final String URL_GET_CONFIG = "config/get.do";

	public static String getUrl(String path) {
		return URL_BASE_API + path;
	}

}
