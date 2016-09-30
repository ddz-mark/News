package com.dudaizhong.news.modules;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dudaizhong.news.R;
import com.dudaizhong.news.app.App;
import com.dudaizhong.news.app.Constants;
import com.dudaizhong.news.modules.gank.GankFragment;
import com.dudaizhong.news.modules.zhihu.fragment.ZhihuFragment;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

public class NewsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private FragmentManager fragmentManager;
    ZhihuFragment mZhihuFragment;
    GankFragment mGankFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
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
                    Log.e("<<<<<<<<<<<<<<<", "GankFragment");
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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            setNowFragment(Constants.ZHIHU_FRAGMENT);
        } else if (id == R.id.nav_gallery) {
            setNowFragment(Constants.GANK_FRAGMENT);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
