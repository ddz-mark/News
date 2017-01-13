package com.dudaizhong.news.modules.gank.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseLazyFragment;
import com.dudaizhong.news.modules.zhihu.adapter.ViewPagerAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Markable on 2016/11/25.
 */

public class GankFragment extends BaseLazyFragment {

    @Bind(R.id.viewPager_zhihu)
    ViewPager mViewPager;
    @Bind(R.id.tabLayout_zhihu)
    TabLayout mTabLayout;

    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu;
    }

    @Override
    protected void initEventAndData() {

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getFragmentManager());
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
        mViewPager.setAdapter(viewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(viewPagerAdapter);

    }

}
