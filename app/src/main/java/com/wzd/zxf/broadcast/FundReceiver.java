package com.wzd.zxf.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.wzd.wolf_open_resource.util.JsonUtil;
import com.wzd.wolf_open_resource.util.Log4JUtil;
import com.wzd.zxf.http.Callback.FundCallBack;
import com.wzd.zxf.http.FinanceApi;
import com.wzd.zxf.model.Fund;
import com.wzd.zxf.tools.DateUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import okhttp3.Call;

public class FundReceiver extends BroadcastReceiver {
    public static final String ACTION_FUND_RECEIVER = "com.wzd.zxf.broadcast.FundReceiver";

    public FundReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ACTION_FUND_RECEIVER.equals(intent.getAction())) {
            Log4JUtil.info("FundReceiver:: "+DateUtil.getMillon(System.currentTimeMillis()));
            getCurrentFundData();
        }
    }

    private void getCurrentFundData() {
        Fund fund = new Fund();
        fund.findAll(new FindListener<Fund>() {
            @Override
            public void done(List<Fund> list, BmobException e) {
                if (list != null && list.size() > 0) {
                    saveFundData(list);
                }
            }
        });
    }

    private void saveFundData(List<Fund> list) {
        for (final Fund item : list) {
            FinanceApi.getInfo(item.getFundcode(), new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int i) {

                }

                @Override
                public void onResponse(String s, int i) {
                    Log4JUtil.debugInfo(s);
                    if (s != null && s.length() > 0) {
                        s = s.replace("jsonpgz(", "");
                        s = s.replace(");", "");
                        try {
                            Fund fund = (Fund) JsonUtil.json2Bean(s, Fund.class);
                            fund.updateData(item.getObjectId());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}
