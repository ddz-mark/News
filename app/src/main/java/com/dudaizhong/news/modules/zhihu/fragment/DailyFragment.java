package com.dudaizhong.news.modules.zhihu.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseFragment;
import com.dudaizhong.news.base.utils.DensityUtil;
import com.dudaizhong.news.modules.zhihu.adapter.DailyAdapter;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuList;
import com.dudaizhong.news.modules.zhihu.presenter.DailyPresenter;
import com.dudaizhong.news.modules.zhihu.presenter.contract.DailyContract;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Dudaizhong on 2016/9/18.
 */

public class DailyFragment extends BaseFragment<DailyPresenter> implements DailyContract.View {

    @Bind(R.id.recycler_zhihu_daily)
    RecyclerView recyclerZhihuDaily;
    @Bind(R.id.swipe_zhihu_daily)
    SwipeRefreshLayout swipeZhihuDaily;
    @Bind(R.id.error)
    LinearLayout mError;

    //TODO 为什么List在这里不行,解决了是LoadMoreRecyclerView
    private ArrayList<ZhihuList.StoriesBean> datas = new ArrayList<>();
    private ArrayList<ZhihuList.TopStoriesBean> top_datas = new ArrayList<>();
    private DailyAdapter dailyAdapter;

    @Override
    protected DailyPresenter createPresenter() {
        return new DailyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_daily;
    }

    @Override
    protected void initEventAndData() {

        recyclerZhihuDaily.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerZhihuDaily.setHasFixedSize(true);

        dailyAdapter = new DailyAdapter(getContext(), datas, top_datas);
        recyclerZhihuDaily.setAdapter(dailyAdapter);
        swipeZhihuDaily.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().getContent(getContext());
            }
        });
        getPresenter().getContent(getContext());
    }

    @Override
    public void showLoading() {
        swipeZhihuDaily.setProgressViewOffset(false, 0, DensityUtil.dip2px(getContext(), 24));
        swipeZhihuDaily.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        if (swipeZhihuDaily.isRefreshing() && null != swipeZhihuDaily)
            swipeZhihuDaily.setRefreshing(false);
        mError.setVisibility(View.GONE);
        recyclerZhihuDaily.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        mError.setVisibility(View.VISIBLE);
        recyclerZhihuDaily.setVisibility(View.GONE);
    }

    @Override
    public void showContent(ZhihuList zhihuList) {
        datas.clear();
        top_datas.clear();
        datas.addAll(zhihuList.getStories());
        top_datas.addAll(zhihuList.getTop_stories());
        dailyAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.error)
    public void onClick() {
        showLoading();
        getPresenter().getContent(getContext());
    }
}
