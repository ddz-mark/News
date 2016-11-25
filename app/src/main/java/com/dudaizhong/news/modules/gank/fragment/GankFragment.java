package com.dudaizhong.news.modules.gank.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseLazyFragment;
import com.dudaizhong.news.modules.zhihu.adapter.ViewPagerAdapter;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Markable on 2016/11/25.
 */

public class GankFragment extends BaseLazyFragment {

    @Bind(R.id.tabLayout_zhihu)
    TabLayout tabLayoutZhihu;
    @Bind(R.id.viewPager_zhihu)
    ViewPager viewPagerZhihu;

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

        viewPagerZhihu.setAdapter(viewPagerAdapter);
        tabLayoutZhihu.setupWithViewPager(viewPagerZhihu);
        tabLayoutZhihu.setTabsFromPagerAdapter(viewPagerAdapter);

    }
}
