package com.dudaizhong.news.modules.main.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dudaizhong.news.R;
import com.dudaizhong.news.app.Constants;
import com.dudaizhong.news.base.BaseActivity;
import com.dudaizhong.news.base.utils.SharedPreferencesUtil;
import com.dudaizhong.news.base.utils.rxUtils.RxBus;
import com.dudaizhong.news.common.widget.CharAvatarView;
import com.dudaizhong.news.modules.gank.fragment.GankFragment;
import com.dudaizhong.news.modules.login.activity.LoginActivity;
import com.dudaizhong.news.modules.event.RefreshEvent;
import com.dudaizhong.news.modules.setting.SettingActivity;
import com.dudaizhong.news.modules.zhihu.fragment.ZhihuFragment;

import butterknife.Bind;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Dudaizhong on 2016/9/16.
 */

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.nav_view)
    NavigationView mNavView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    CharAvatarView mImageView;
    TextView desc;
    FragmentManager fragmentManager;
    ZhihuFragment mZhihuFragment;
    GankFragment mGankFragment;

    private Subscription subscription;

    @Override
    protected void initInject() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {
        fragmentManager = getSupportFragmentManager();
        initView();
        //默认显示知乎fragment
        setNowFragment(Constants.ZHIHU_FRAGMENT);

        subscription = RxBus.getDefault().toObserverable(RefreshEvent.class).subscribe(new Action1<RefreshEvent>() {
            @Override
            public void call(RefreshEvent event) {
                mImageView.setText(event.getName());
                desc.setText(event.getName());
            }
        });

        if (SharedPreferencesUtil.getDayNight()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }


    private void initView() {

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavView.setNavigationItemSelectedListener(this);
        View headerView = mNavView.getHeaderView(0);
        mImageView = (CharAvatarView) headerView.findViewById(R.id.imageView);
        mImageView.setText(getString(R.string.login));
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        desc = (TextView) headerView.findViewById(R.id.desc);
    }

    public void setNowFragment(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (index) {
            case Constants.ZHIHU_FRAGMENT:
                if (mZhihuFragment == null) {
                    mZhihuFragment = new ZhihuFragment();
                }
                fragmentTransaction.replace(R.id.fragment_container, mZhihuFragment);
                mToolbar.setTitle(R.string.zhihu);
                break;
            case Constants.GANK_FRAGMENT:
                if (mGankFragment == null) {
                    mGankFragment = new GankFragment();
                }
                fragmentTransaction.replace(R.id.fragment_container, mGankFragment);
                mToolbar.setTitle(R.string.Gank);
                break;
            default:
                break;
        }
        fragmentTransaction.commitAllowingStateLoss();
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            showExitDialog();
        }
    }

    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定退出GeekNews吗");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                Process.killProcess(Process.myPid());
                System.exit(0);
            }
        });
        builder.show();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            setNowFragment(Constants.ZHIHU_FRAGMENT);
        } else if (id == R.id.nav_gallery) {
            setNowFragment(Constants.GANK_FRAGMENT);
        } else if (id == R.id.nav_share) {
            startActivity(new Intent(MainActivity.this, SettingActivity.class));
        } else if (id == R.id.nav_send) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        } else if (id == R.id.nav_like) {
            startActivity(new Intent(MainActivity.this, LikeActivity.class));
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

}
