package com.dudaizhong.news.modules.zhihu.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
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
 * Created by Dudaizhong on 2016/9/16.
 */

public class ZhihuFragment extends BaseLazyFragment {

    @Bind(R.id.tabLayout_zhihu)
    TabLayout tabLayoutZhihu;
    @Bind(R.id.viewPager_zhihu)
    ViewPager viewPagerZhihu;

    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<String>();

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

        fragmentList.add(new DailyFragment());
        fragmentList.add(new ThemeFragment());
        fragmentList.add(new SectionFragment());
        fragmentList.add(new HotFragment());
        viewPagerAdapter.setData(fragmentList);

        titles.add("日报");
        titles.add("主题");
        titles.add("专栏");
        titles.add("热门");
        viewPagerAdapter.setTitles(titles);

        viewPagerZhihu.setAdapter(viewPagerAdapter);
        tabLayoutZhihu.setupWithViewPager(viewPagerZhihu);
        tabLayoutZhihu.setTabsFromPagerAdapter(viewPagerAdapter);
    }



}
