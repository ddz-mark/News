package com.dudaizhong.news.modules.zhihu.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseFragment;
import com.dudaizhong.news.base.BaseViewHolder;
import com.dudaizhong.news.base.utils.DensityUtil;
import com.dudaizhong.news.modules.zhihu.adapter.DailyAdapter;
import com.dudaizhong.news.modules.zhihu.presenter.DailyPresenter;
import com.dudaizhong.news.modules.zhihu.presenter.contract.DailyContract;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuListNews;

import java.util.ArrayList;

import butterknife.Bind;
import rx.Observable;

/**
 * Created by Dudaizhong on 2016/9/18.
 */

public class DailyFragment extends BaseFragment<DailyPresenter> implements DailyContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.recycler_zhihu_daily)
    RecyclerView recyclerZhihuDaily;
    @Bind(R.id.swipe_zhihu_daily)
    SwipeRefreshLayout swipeZhihuDaily;

    private ArrayList<ZhihuListNews.StoriesBean> data;
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

        data = new ArrayList<>();
        dailyAdapter = new DailyAdapter(getContext(), data);
        recyclerZhihuDaily.setAdapter(dailyAdapter);

        swipeZhihuDaily.setOnRefreshListener(this);
        dailyAdapter.setOnRecyclerViewListener(new BaseViewHolder.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });
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
    public void showContent(ZhihuListNews zhihuListNews) {
        data.clear();
        data.addAll(zhihuListNews.getStories());
        dailyAdapter.notifyDataSetChanged();
    }
}
