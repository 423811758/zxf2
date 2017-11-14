package com.wzd.zxf.http;

import com.wzd.wolf_open_resource.util.Log4JUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.HttpUrl;

/**
 * Created by wzd on 2017/11/14.
 */

public class FinanceApi extends BaseApi {

    public static void getInfo(StringCallback callback) {
        OkHttpUtils.get().url(URL_FINANCE_API).build().execute(callback);
    }
}
