package com.wzd.wolf_open_resource.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 所有Fragment必须继承该类
 * @author kj-8796
 *
 */
public abstract class BaseFragment extends Fragment {

	protected CoreActivity mActivity;
	protected Context mContext;

	protected LayoutInflater mInflater;
	protected View mRoot;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mInflater = inflater;
		if (getMainContentViewId() != 0) {
			mRoot = mInflater.inflate(getMainContentViewId(), null);
		}
		return mRoot;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mActivity = (CoreActivity) getActivity();
		mContext = mActivity.getApplicationContext();

		initComponent();

		initData();
	}

	protected View findViewById(int resid) {
		return mActivity.findViewById(resid);
	}

	protected abstract void initComponent();

	protected abstract void initData();

	/**
	 * 布局ID
	 */
	protected abstract int getMainContentViewId();
}
