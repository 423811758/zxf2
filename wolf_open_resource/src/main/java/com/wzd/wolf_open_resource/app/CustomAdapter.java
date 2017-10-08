package com.wzd.wolf_open_resource.app;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Adapter基本类 所有adapter继承该类
 */
@SuppressWarnings("static-access")
public abstract class CustomAdapter<T> extends BaseAdapter {

	protected Context mContext;
	protected static LayoutInflater sInflater;

	public CustomAdapter(Context context) {
		this.mContext = context;
		this.sInflater = LayoutInflater.from(mContext);
	}

	protected List<T> mData = new ArrayList<T>(3);

	public void addAll(List<T> data) {
		if (data != null && data.size() > 0) {
			synchronized (mData) {
				mData.addAll(data);
			}
		}
	}

	public void refresh(List<T> data) {
		synchronized (mData) {
			mData.clear();
			if (data != null && data.size() > 0) {
				mData.addAll(data);
			}
		}
	}

	public void deleteByPosition(int position) {
		synchronized (mData) {
			if (mData != null && mData.size() > 0) {
				mData.remove(position);
			}
		}
	}

	public void refreshItem(int position, T data) {
		synchronized (mData) {
			for (int i = 0; i < mData.size(); i++) {
				if (i == position) {
					mData.remove(position);
					mData.add(position, data);
				}
			}
		}
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public T getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	protected View inflater(int resId, ViewGroup root) {
		return sInflater.inflate(resId, root);
	}

	public List<T> getAll() {
		return mData;
	}
}
