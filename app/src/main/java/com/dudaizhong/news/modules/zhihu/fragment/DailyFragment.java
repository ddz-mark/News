package com.dudaizhong.news.modules.zhihu.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseFragment;
import com.dudaizhong.news.base.utils.DensityUtil;
import com.dudaizhong.news.base.utils.ToastUtil;
import com.dudaizhong.news.common.widget.LoadMoreRecyclerView;
import com.dudaizhong.news.modules.zhihu.adapter.DailyAdapter;
import com.dudaizhong.news.modules.zhihu.presenter.DailyPresenter;
import com.dudaizhong.news.modules.zhihu.presenter.contract.DailyContract;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuList;

import java.util.ArrayList;

import butterknife.Bind;
import rx.Observable;

/**
 * Created by Dudaizhong on 2016/9/18.
 */

public class DailyFragment extends BaseFragment<DailyPresenter> implements DailyContract.View, SwipeRefreshLayout.OnRefreshListener, LoadMoreRecyclerView.LoadMoreListener {

    @Bind(R.id.recycler_zhihu_daily)
    LoadMoreRecyclerView recyclerZhihuDaily;
    @Bind(R.id.swipe_zhihu_daily)
    SwipeRefreshLayout swipeZhihuDaily;

    private ArrayList<ZhihuList.StoriesBean> data;
    private DailyAdapter dailyAdapter;

    private int currentPage = 1;

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

        data = new ArrayList<>();
        dailyAdapter = new DailyAdapter(getContext(), data);
        recyclerZhihuDaily.setAdapter(dailyAdapter);
        recyclerZhihuDaily.setLoadMoreListener(this);
        swipeZhihuDaily.setOnRefreshListener(this);

        getPresenter().getContent();
    }

    @Override
    public void showLoading() {
        swipeZhihuDaily.setProgressViewOffset(false, 0, DensityUtil.dip2px(getContext(), 24));
        swipeZhihuDaily.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeZhihuDaily.setRefreshing(false);
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
    public void showContent(ZhihuList zhihuList) {
        if (currentPage == 1)
            data.clear();
        data.addAll(zhihuList.getStories());
        recyclerZhihuDaily.notifyDataChange(currentPage + 1, data.size());
    }

    @Override
    public void onLoadMore() {
        ToastUtil.show("上拉加载");
    }
}
