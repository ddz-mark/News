package com.dudaizhong.news.modules.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.dudaizhong.news.R;
import com.dudaizhong.news.app.Constants;
import com.dudaizhong.news.modules.gank.fragment.GankFragment;
import com.dudaizhong.news.modules.login.LoginActivity;
import com.dudaizhong.news.modules.setting.SettingActivity;
import com.dudaizhong.news.modules.zhihu.fragment.ZhihuFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Dudaizhong on 2016/9/16.
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.nav_view)
    NavigationView mNavView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    FragmentManager fragmentManager;
    ZhihuFragment mZhihuFragment;
    GankFragment mGankFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        initView();
        //默认显示知乎fragment
        setNowFragment(Constants.ZHIHU_FRAGMENT);
    }

    private void initView() {
        fragmentManager = getSupportFragmentManager();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavView.setNavigationItemSelectedListener(this);
        View headerView = mNavView.getHeaderView(0);
        ImageView imageView = (ImageView) headerView.findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });
    }

    public void setNowFragment(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (index) {
            case Constants.ZHIHU_FRAGMENT:
                if (mZhihuFragment == null) {
                    mZhihuFragment = new ZhihuFragment();
                }
                fragmentTransaction.replace(R.id.fragment_container, mZhihuFragment);
                mToolbar.setTitle("知乎");
                break;
            case Constants.GANK_FRAGMENT:
                if (mGankFragment == null) {
                    mGankFragment = new GankFragment();
                }
                fragmentTransaction.replace(R.id.fragment_container, mGankFragment);
                mToolbar.setTitle("Gank");
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
        builder.setTitle("提示");
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
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}
