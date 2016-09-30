package com.dudaizhong.news.modules.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dudaizhong.news.R;
import com.dudaizhong.news.app.Constants;
import com.dudaizhong.news.base.BaseActivity;
import com.dudaizhong.news.modules.gank.GankFragment;
import com.dudaizhong.news.modules.login.LoginActivity;
import com.dudaizhong.news.modules.zhihu.fragment.ZhihuFragment;

import butterknife.Bind;
import rx.Observable;

/**
 * Created by Dudaizhong on 2016/9/16.
 */

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @Bind(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;


    FragmentManager fragmentManager;
    ZhihuFragment mZhihuFragment;
    GankFragment mGankFragment;

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news;
    }

    /**
     * 代替onCreate方法
     *
     * @param savedInstanceState
     */
    @Override
    protected void initEventAndData(Bundle savedInstanceState) {

        fragmentManager = getSupportFragmentManager();

        fab.setOnClickListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);
        View headerView = navView.getHeaderView(0);
        ImageView imageView = (ImageView) headerView.findViewById(R.id.imageView);
        imageView.setOnClickListener(this);

        //默认显示知乎fragment
        setNowFragment(Constants.ZHIHU_FRAGMENT);
    }

    public void setNowFragment(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (index) {
            case Constants.ZHIHU_FRAGMENT:
                if (mZhihuFragment == null) {
                    mZhihuFragment = new ZhihuFragment();
                }
                fragmentTransaction.replace(R.id.fragment_container, mZhihuFragment);
                toolbar.setTitle("知乎");
                break;
            case Constants.GANK_FRAGMENT:
                if (mGankFragment == null) {
                    mGankFragment = new GankFragment();
                }
                fragmentTransaction.replace(R.id.fragment_container, mGankFragment);
                toolbar.setTitle("Gank");
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }


    @Override
    public <V> Observable.Transformer<V, V> bind() {
        return bindToLifecycle();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
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
                android.os.Process.killProcess(android.os.Process.myPid());
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

        } else if (id == R.id.nav_send) {

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Snackbar.make(v, "点点点", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            case R.id.imageView:
                startActivity(new Intent(mActivity,LoginActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
    }
}
