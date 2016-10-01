package com.dudaizhong.news.modules.zhihu.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseFragment;
import com.dudaizhong.news.base.utils.DensityUtil;
import com.dudaizhong.news.modules.zhihu.adapter.ThemeAdapter;
import com.dudaizhong.news.modules.zhihu.domain.ThemeList;
import com.dudaizhong.news.modules.zhihu.presenter.ThemePresenter;
import com.dudaizhong.news.modules.zhihu.presenter.contract.ThemeContract;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;

/**
 * Created by Dudaizhong on 2016/9/18.
 */

public class ThemeFragment extends BaseFragment<ThemePresenter> implements ThemeContract.View ,SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.recycler_zhihu_theme)
    RecyclerView recyclerZhihuTheme;
    @Bind(R.id.swipe_zhihu_theme)
    SwipeRefreshLayout swipeZhihuTheme;

    private ArrayList<ThemeList.OthersBean> datas;
    private ThemeAdapter adapter;

    @Override
    protected ThemePresenter createPresenter() {
        return new ThemePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_theme;
    }

    @Override
    protected void initEventAndData() {
        //网格布局，设置成2列
        recyclerZhihuTheme.setLayoutManager(new GridLayoutManager(getContext(),2));
        datas = new ArrayList<>();
        adapter = new ThemeAdapter(getContext(),datas);
        recyclerZhihuTheme.setAdapter(adapter);
        swipeZhihuTheme.setOnRefreshListener(this);

        getPresenter().getContent();
    }


    @Override
    public <V> Observable.Transformer<V, V> bind() {
        return bindToLifecycle();
    }

    @Override
    public void onRefresh() {
        getPresenter().getContent();
    }

    @Override
    public void showLoading() {
        swipeZhihuTheme.setProgressViewOffset(false, 0, DensityUtil.dip2px(getContext(), 24));
        swipeZhihuTheme.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeZhihuTheme.setRefreshing(false);
    }

    @Override
    public void showContent(ThemeList themeList) {
        datas.clear();
        datas.addAll(themeList.getOthers());
        adapter.notifyDataSetChanged();
    }
}
