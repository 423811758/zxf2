package com.wzd.zxf.http.Callback;

import com.wzd.wolf_open_resource.util.JsonUtil;
import com.wzd.zxf.model.Fund;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by wzd on 2017/11/19.
 */

public class FundCallBack extends StringCallback {
    @Override
    public void onError(Call call, Exception e, int i) {

    }

    @Override
    public void onResponse(String s, int i) {
        s = s.replace("jsonpgz(", "");
        s = s.replace(");", "");
        try {
            Fund fund = (Fund) JsonUtil.json2Bean(s, Fund.class);
            fund.saveData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
