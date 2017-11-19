package com.wzd.zxf;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.wzd.wolf_open_resource.app.CoreActivity;
import com.wzd.zxf.fragment.FundFragment;
import com.wzd.zxf.fragment.MyFragment;
import com.wzd.zxf.fragment.PlanFragment;
import com.wzd.zxf.fragment.SettingFragment;
import com.wzd.zxf.fragment.TimeFragment;
import com.wzd.zxf.tools.DateUtil;
import com.wzd.zxf.tools.SPUtil;
import com.wzd.zxf.ui.HomeMenu;

public class MainActivity extends CoreActivity implements View.OnClickListener {


    FragmentManager mFragmentMgr;
    Fragment mCurrentFragment;
    int mCurrentIndex;

    HomeMenu mHomeMenu;

    @Override
    protected void initComponent() {
        mFragmentMgr = getSupportFragmentManager();

        mHomeMenu = (HomeMenu) findViewById(R.id.menuview);
        mHomeMenu.setSelected(0);
        mHomeMenu.setOnMenuChangeListener(onMenuChangeListener);

        replaceFragment(0);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getMainContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {

    }

    private void replaceFragment(int index) {
        Fragment newFragment = createFragment(index);
        Fragment oldFragment = mCurrentFragment;

        if (newFragment != null) {
            boolean leftOrRight = index > mCurrentIndex ? true : false;
            replaceFragment(R.id.fragment_layout, leftOrRight, newFragment,
                    oldFragment);
            mCurrentFragment = newFragment;
            mCurrentIndex = index;
        }
    }

    private Fragment createFragment(int index) {
        if (index == 0) {
            return FundFragment.getInstance();
        } else if (index == 1) {
            return PlanFragment.getInstance();
        } else if (index == 2) {
            return TimeFragment.getInstance();
        } else if (index == 3) {
            return SettingFragment.getInstance();
        } else if (index == 4) {
            return MyFragment.getInstance();
        }
        return null;
    }

    protected void replaceFragment(int layoutId, boolean leftOrRight,
                                   Fragment fragment, Fragment hideFragment) {
        FragmentTransaction transaction = mFragmentMgr.beginTransaction();

        if (leftOrRight) {
            transaction.setCustomAnimations(R.anim.slide_in_right,
                    R.anim.slide_out_left);
        } else {// 从左进，右出
            transaction.setCustomAnimations(R.anim.slide_in_left,
                    R.anim.slide_out_right);
        }

        if (hideFragment != null) {
            transaction.hide(hideFragment);
        }

        if (fragment.isAdded()) { // 已添加过
            transaction.show(fragment);
        } else {
            transaction.add(layoutId, fragment);
        }
        transaction.commit();
    }

    private void clearData() {
        clearFragment();
    }

    private void clearFragment() {
        FundFragment.clear();
        PlanFragment.clear();
        TimeFragment.clear();
        SettingFragment.clear();
        MyFragment.clear();
    }

    HomeMenu.OnMenuChangeListener onMenuChangeListener = new HomeMenu.OnMenuChangeListener() {

        @Override
        public void onChange(int index) {
            replaceFragment(index);
        }
    };
}
