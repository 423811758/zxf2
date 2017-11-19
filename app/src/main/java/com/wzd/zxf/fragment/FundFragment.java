package com.wzd.zxf.fragment;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.wzd.wolf_open_resource.app.BaseFragment;
import com.wzd.wolf_open_resource.util.JsonUtil;
import com.wzd.wolf_open_resource.util.Log4JUtil;
import com.wzd.zxf.FundAdapter;
import com.wzd.zxf.R;
import com.wzd.zxf.http.FinanceApi;
import com.wzd.zxf.model.Fund;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import okhttp3.Call;

/**
 * <p>Title: 基金</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011-2012 </p>
 * <p>Company: </p>
 * @author caijj
 * @version 1.0  2014年6月26日 下午4:08:54
 */
public class FundFragment extends BaseFragment implements View.OnClickListener {
	
	public static final String TAG = "FundFragment";

	private static Fragment sFragment;

	private EditText mFundCodeEt;
	private Button mAddFundBtn;
	private ListView mFundLv;
	private FundAdapter mAdapter;

	public static Fragment getInstance() {
		if (sFragment == null) {
			sFragment = new FundFragment();
		}
		return sFragment;
	}

	public static void clear() {
		sFragment = null;
	}

	@Override
	protected void initComponent() {
		mFundCodeEt = (EditText) findViewById(R.id.fund_code_et);
		mAddFundBtn = (Button) findViewById(R.id.add_fund_btn);
		mFundLv = (ListView) findViewById(R.id.fund_lv);

		mAddFundBtn.setOnClickListener(this);
	}

	@Override
	protected void initData() {
		mAdapter = new FundAdapter(mActivity);
		mFundLv.setAdapter(mAdapter);
		getFundList();
	}

	private void getFundList() {
		Fund fund = new Fund();
		fund.findAll(new FindListener<Fund>() {
			@Override
			public void done(List<Fund> list, BmobException e) {
				if(list != null && list.size() > 0) {
					mAdapter.refresh(list);
					mAdapter.notifyDataSetChanged();
					Log4JUtil.debugInfo(list.toString());
				}
			}
		});
	}

	@Override
	protected int getMainContentViewId() {
		return R.layout.fragment_fund;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.add_fund_btn:
				addFund();
				break;
		}
	}

	private void addFund() {
		String fundcode = mFundCodeEt.getText().toString().trim();
		FinanceApi.getInfo(fundcode, new StringCallback() {
			@Override
			public void onError(Call call, Exception e, int i) {

			}

			@Override
			public void onResponse(String s, int i) {
				if(s != null && s.length() > 0) {
					s = s.replace("jsonpgz(", "");
					s = s.replace(");", "");
					try {
						Fund fund = (Fund) JsonUtil.json2Bean(s, Fund.class);
						fund.saveData();
						getFundList();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		});
	}
}
