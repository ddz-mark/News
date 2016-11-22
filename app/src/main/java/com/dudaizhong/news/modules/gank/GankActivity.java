package com.dudaizhong.news.modules.gank;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseActivity;
import com.dudaizhong.news.modules.gank.fragment.AIFragment;
import com.dudaizhong.news.modules.gank.fragment.MeiziFragment;
import com.dudaizhong.news.modules.gank.fragment.VideoFragment;
import com.dudaizhong.news.modules.zhihu.adapter.ViewPagerAdapter;
import com.dudaizhong.news.modules.zhihu.fragment.CommentFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Dudaizhong on 2016/9/16.
 */

public class GankActivity extends BaseActivity {


    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.tab_comment)
    TabLayout mTabComment;
    @Bind(R.id.vp_comment)
    ViewPager mVpComment;

    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<>();

    @Override
    protected void initInject() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {
        setToolBar(mToolbar, "Gank");

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        fragmentList.add(AIFragment.newInstance("Android"));
        fragmentList.add(AIFragment.newInstance("iOS"));
        fragmentList.add(new MeiziFragment());
        fragmentList.add(new VideoFragment());
        viewPagerAdapter.setData(fragmentList);

        titles.add("Android");
        titles.add("IOS");
        titles.add("福利");
        titles.add("视频");
        viewPagerAdapter.setTitles(titles);

        mVpComment.setAdapter(viewPagerAdapter);
        mTabComment.setupWithViewPager(mVpComment);
        mTabComment.setTabsFromPagerAdapter(viewPagerAdapter);
    }

}


