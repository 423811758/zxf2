package com.wzd.zxf.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.wzd.wolf_open_resource.app.BaseFragment;
import com.wzd.zxf.R;

/**
 * <p>Title: 设置</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011-2012 </p>
 * <p>Company: </p>
 * @author caijj
 * @version 1.0  2014年6月26日 下午4:08:54
 */
public class SettingFragment extends BaseFragment {
	
	public static final String TAG = "SettingFragment";

	private static Fragment sFragment;

	public static Fragment getInstance() {
		if (sFragment == null) {
			sFragment = new SettingFragment();
		}
		return sFragment;
	}

	public static void clear() {
		sFragment = null;
	}

	@Override
	protected void initComponent() {

	}

	@Override
	protected void initData() {

	}

	@Override
	protected int getMainContentViewId() {
		return R.layout.fragment_setting;
	}

}
