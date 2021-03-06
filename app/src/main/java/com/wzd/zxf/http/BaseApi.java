package com.wzd.zxf.http;

public class BaseApi {

	// 陈
	// public static final String URL_BASE = "http://192.168.129.112:8080/";
	// public static final String URL_BASE = "http://192.168.146.18:8080/";
	public static final String URL_BASE = "http://weduoduo.net/";
	public static final String URL_BASE_API = URL_BASE + "api/";
	public static final String URL_FINANCE_API = "http://fundgz.1234567.com.cn/js/";

	public static String getUrl(String path) {
		return URL_BASE_API + path;
	}
	public static String getFundUrl(String fundcode){
		return URL_FINANCE_API + fundcode + ".js";
	}

}
