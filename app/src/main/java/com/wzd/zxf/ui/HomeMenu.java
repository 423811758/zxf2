package com.wzd.zxf.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wzd.zxf.R;

/**
 * APP底部导航栏
 * @author wzd
 *
 */
@SuppressLint("InflateParams")
public class HomeMenu extends LinearLayout {

	private static final int[] MENU_ICON_RES = new int[] {
			R.mipmap.icon_menu_card, R.mipmap.icon_menu_find,
			R.mipmap.icon_menu_welfare, R.mipmap.icon_menu_notice,
			R.mipmap.icon_menu_user };
	private static final int[] MENU_ICON_SELECTED_RES = new int[] {
			R.mipmap.icon_menu_card_selected,
			R.mipmap.icon_menu_find_selected,
			R.mipmap.icon_menu_welfare_selected,
			R.mipmap.icon_menu_notice_selected,
			R.mipmap.icon_menu_user_selected };
	private static final int[] MENU_NAME_RES = new int[] {
			R.string.home_menu_mycard, R.string.home_menu_find,
			R.string.home_menu_welfare, R.string.home_menu_notice,
			R.string.home_menu_user };

	private Context mContext;

	private View mSelectedView;
	private int mSelectedIndex = -1;

	public HomeMenu(Context context) {
		this(context, null);
	}

	public HomeMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		setOrientation(LinearLayout.HORIZONTAL);

		init();
	}

	private void init() {
		final LayoutInflater inflater = LayoutInflater.from(mContext);
		LayoutParams params = new LayoutParams(0,
				LayoutParams.WRAP_CONTENT);
		params.weight = 1;
		params.gravity = Gravity.CENTER;

		for (int i = 0; i < 5; i++) {
			View view = inflater.inflate(R.layout.item_linearlayout_home_menu,
					null);
			TextView menuView = (TextView) view.findViewById(R.id.menu_name);
			menuView.setText(MENU_NAME_RES[i]);
			menuView.setTextColor(getResources().getColor(R.color.text_black));

			ImageView icon = (ImageView) view.findViewById(R.id.menu_icon);
			Drawable drawable = getResources().getDrawable(MENU_ICON_RES[i]);
			icon.setImageDrawable(drawable);

			view.setOnClickListener(mTabClickListener);
			view.setTag(i);
			addView(view, params);
		}
	}

	public void setSelected(int index) {
		doSelectedTab(index);
	}

	private void doSelectedTab(int index) {
		final int count = getChildCount();
		if (index >= count || index == mSelectedIndex) {
			return;
		}

		View view = getChildAt(index);
		TextView menuView = (TextView) view.findViewById(R.id.menu_name);

		menuView.setTextColor(getResources().getColor(R.color.text_orange));
		ImageView icon = (ImageView) view.findViewById(R.id.menu_icon);
		Drawable drawable = getResources().getDrawable(
				MENU_ICON_SELECTED_RES[index]);
		icon.setImageDrawable(drawable);

		if (mSelectedView != null) {
			menuView = (TextView) mSelectedView.findViewById(R.id.menu_name);
			menuView.setTextColor(getResources().getColor(R.color.text_black));

			icon = (ImageView) mSelectedView.findViewById(R.id.menu_icon);
			drawable = getResources()
					.getDrawable(MENU_ICON_RES[mSelectedIndex]);
			icon.setImageDrawable(drawable);
		}

		mSelectedView = view;
		mSelectedIndex = index;
	}

	OnClickListener mTabClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			int index = (Integer) v.getTag();
			doSelectedTab(index);

			if (mChangeListener != null) {
				mChangeListener.onChange(index);
			}
		}
	};

	private OnMenuChangeListener mChangeListener;

	public void setOnMenuChangeListener(OnMenuChangeListener l) {
		mChangeListener = l;
	}

	public interface OnMenuChangeListener {
		public void onChange(int index);
	}
}
