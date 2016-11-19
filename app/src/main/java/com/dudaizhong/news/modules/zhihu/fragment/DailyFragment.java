package com.dudaizhong.news.modules.zhihu.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseFragment;
import com.dudaizhong.news.base.utils.DensityUtil;
import com.dudaizhong.news.base.utils.ToastUtil;
import com.dudaizhong.news.common.widget.LoadMoreRecyclerView;
import com.dudaizhong.news.modules.zhihu.adapter.DailyAdapter;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuData;
import com.dudaizhong.news.modules.zhihu.presenter.DailyPresenter;
import com.dudaizhong.news.modules.zhihu.presenter.contract.DailyContract;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.Bind;
import rx.Observable;

/**
 * Created by Dudaizhong on 2016/9/18.
 */

public class DailyFragment extends BaseFragment<DailyPresenter> implements DailyContract.View{

    @Bind(R.id.recycler_zhihu_daily)
    RecyclerView recyclerZhihuDaily;
    @Bind(R.id.swipe_zhihu_daily)
    SwipeRefreshLayout swipeZhihuDaily;

    //TODO 为什么List在这里不行,是LoadMoreRecyclerView
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

        dailyAdapter = new DailyAdapter(getContext(), datas,top_datas);
        recyclerZhihuDaily.setAdapter(dailyAdapter);
        swipeZhihuDaily.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().getContent();
            }
        });
        getPresenter().showLoading();
        getPresenter().getContent();
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
    }

    @Override
    public void showContent(ZhihuList zhihuList) {
        datas.clear();
        top_datas.clear();
        datas.addAll(zhihuList.getStories());
        top_datas.addAll(zhihuList.getTop_stories());
        dailyAdapter.notifyDataSetChanged();
    }

}
