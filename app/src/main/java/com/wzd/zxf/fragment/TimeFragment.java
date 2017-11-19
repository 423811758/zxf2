package com.wzd.zxf.fragment;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.wzd.wolf_open_resource.app.BaseFragment;
import com.wzd.wolf_open_resource.util.JsonUtil;
import com.wzd.wolf_open_resource.util.Log4JUtil;
import com.wzd.zxf.MainActivity;
import com.wzd.zxf.MyWidgetProvider;
import com.wzd.zxf.R;
import com.wzd.zxf.broadcast.FundReceiver;
import com.wzd.zxf.http.FinanceApi;
import com.wzd.zxf.model.Fund;
import com.wzd.zxf.tools.DateUtil;
import com.wzd.zxf.tools.SPUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import okhttp3.Call;

import static com.pgyersdk.update.UpdateManagerListener.getAppBeanFromString;

/**
 * <p>Title: 设置</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011-2012 </p>
 * <p>Company: </p>
 * @author caijj
 * @version 1.0  2014年6月26日 下午4:08:54
 */
public class TimeFragment extends BaseFragment implements View.OnClickListener {
	
	public static final String TAG = "SettingFragment";

	private static Fragment sFragment;

	public static Fragment getInstance() {
		if (sFragment == null) {
			sFragment = new TimeFragment();
		}
		return sFragment;
	}

	public static void clear() {
		sFragment = null;
	}

	public static final String OLD_DATE = "OLD_DATE";

	private TextView mBirthdayTv;
	private EditText mAgeEt;
	private Button mSaveBtn;
	private Button mUpdateBtn;
	private Button mRestartBtn;
	private Button mFundAlarmBtn;
	private TextView mResultTv;
	private String mBirthdayStr = "1989-12-23";
	private int mAge;
	
	@Override
	protected void initComponent() {
		mBirthdayTv = (TextView)findViewById(R.id.birthday_tv);
		mAgeEt = (EditText) findViewById(R.id.age_et);
		mSaveBtn = (Button) findViewById(R.id.save_btn);
		mUpdateBtn = (Button) findViewById(R.id.update_btn);
		mRestartBtn = (Button) findViewById(R.id.restart_btn);
		mResultTv = (TextView) findViewById(R.id.result_tv);
		mFundAlarmBtn = (Button) findViewById(R.id.fund_alarm_btn);

		mSaveBtn.setOnClickListener(this);
		mUpdateBtn.setOnClickListener(this);
		mRestartBtn.setOnClickListener(this);
		mFundAlarmBtn.setOnClickListener(this);
	}

	@Override
	protected void initData() {
		mBirthdayStr = mBirthdayTv.getText().toString().trim();
		mAge = Integer.parseInt(mAgeEt.getText().toString().trim());
		SPUtil.put(mActivity, OLD_DATE, DateUtil.getOld(mBirthdayStr, "yyyy-MM-dd", mAge).getTime());
	}

	@Override
	protected int getMainContentViewId() {
		return R.layout.fragment_time;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.save_btn:
				save();
				break;
			case R.id.update_btn:
				update();
				break;
			case R.id.restart_btn:
				restart();
				break;
			case R.id.fund_alarm_btn:
				try {
					setFundAlarm();
				} catch (Exception e) {

				}
				break;
			default:
				break;
		}
	}

	private void setFundAlarm() throws ParseException {
		Intent intent = new Intent(FundReceiver.ACTION_FUND_RECEIVER);
		intent.putExtra("msg", "fund alarm");
		PendingIntent pi = PendingIntent.getBroadcast(mContext, 0, intent, 0);
		AlarmManager am = (AlarmManager) mActivity.getSystemService(mActivity.ALARM_SERVICE);
		long startTime = DateUtil.parseDatetime((DateUtil.getDay(System.currentTimeMillis())+ " 14:30:00"), DateUtil.FORMAT).getTime();
		Log4JUtil.info(DateUtil.getMillon(startTime));
		am.setRepeating(AlarmManager.RTC, startTime, 24 * 60 * 60 * 1000, pi);
	}

	private void restart() {
//		Fund fund = new Fund();
//		List<String> values = new ArrayList<>();
//		values.add("001186");
//		fund.findByKey("fundcode", values, new FindListener<Fund>() {
//			@Override
//			public void done(List<Fund> list, BmobException e) {
//				if(list != null && list.size() > 0) {
//					mResultTv.setText(list.get(0).getName());
//				}
//			}
//		});
//		FinanceApi.getInfo("001986", new StringCallback() {
//			@Override
//			public void onError(Call call, Exception e, int i) {
//				mResultTv.setText("失败");
//			}
//
//			@Override
//			public void onResponse(String s, int i) {
//				s = s.replace("jsonpgz(", "");
//				s = s.replace(");", "");
//				try {
//					Fund fund = (Fund) JsonUtil.json2Bean(s, Fund.class);
//					fund.saveData();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				mResultTv.setText(s);
//			}
//		});
	}

	private void update() {
		PgyUpdateManager.setIsForced(true); //设置是否强制更新。true为强制更新；false为不强制更新（默认值）。
//		PgyUpdateManager.register(mActivity, "com.wzd.zxf.pgyprovider");
        PgyUpdateManager.register(mActivity, "com.wzd.zxf.pgyprovider",
                new UpdateManagerListener() {

                    @Override
                    public void onUpdateAvailable(final String result) {

                        // 将新版本信息封装到AppBean中
                        final AppBean appBean = getAppBeanFromString(result);
                        new AlertDialog.Builder(mActivity)
                                .setTitle("更新")
                                .setMessage("")
                                .setNegativeButton(
                                        "确定",
                                        new DialogInterface.OnClickListener() {

                                            @Override
                                            public void onClick(
                                                    DialogInterface dialog,
                                                    int which) {
                                                startDownloadTask(
														mActivity,
                                                        appBean.getDownloadURL());
                                            }
                                        }).show();
                    }

                    @Override
                    public void onNoUpdateAvailable() {
						Log4JUtil.debugInfo("当前是最新版本");
                        Toast.makeText(mActivity, "当前是最新版本", Toast.LENGTH_SHORT);
                    }
                });
	}

	private void save(){
		mBirthdayStr = mBirthdayTv.getText().toString().trim();
		mAge = Integer.parseInt(mAgeEt.getText().toString().trim());
		long oldTime = DateUtil.getOld(mBirthdayStr, "yyyy-MM-dd", mAge).getTime();
		SPUtil.put(mActivity, OLD_DATE, oldTime);
		Intent intent = new Intent();
		intent.putExtra("OLD_TIME", oldTime);
		intent.setAction(MyWidgetProvider.UPDATE_WIDGET_ACTION);
		mActivity.sendBroadcast(intent);
		Toast.makeText(mActivity, "设置成功", Toast.LENGTH_SHORT).show();
	}
}
