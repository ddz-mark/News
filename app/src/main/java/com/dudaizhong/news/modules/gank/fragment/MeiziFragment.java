package com.dudaizhong.news.modules.gank.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseFragment;
import com.dudaizhong.news.base.BasePresenter;
import com.dudaizhong.news.modules.gank.adapter.AIAdapter;
import com.dudaizhong.news.modules.gank.adapter.GirlAdapter;
import com.dudaizhong.news.modules.gank.domain.AIList;
import com.dudaizhong.news.modules.gank.presenter.AIPresenter;
import com.dudaizhong.news.modules.gank.presenter.contract.AIContract;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Markable on 2016/11/22.
 */

public class MeiziFragment extends BaseFragment<AIPresenter> implements AIContract.View {

    @Bind(R.id.recycler_zhihu_section)
    RecyclerView mRecyclerZhihuSection;
    @Bind(R.id.swipe_zhihu_section)
    SwipeRefreshLayout mSwipeZhihuSection;

    private ArrayList<AIList> datas;
    private GirlAdapter adapter;
    StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private int page = 1;
    private static final int SPAN_COUNT = 2;

    @Override
    protected AIPresenter createPresenter() {
        return new AIPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_hot;
    }

    @Override
    protected void initEventAndData() {
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL);
        //解决item左右切换时闪烁问题
        mStaggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerZhihuSection.setLayoutManager(mStaggeredGridLayoutManager);

        datas = new ArrayList<>();
        adapter = new GirlAdapter(datas, getContext());
        mRecyclerZhihuSection.setAdapter(adapter);
        mRecyclerZhihuSection.setItemAnimator(new DefaultItemAnimator());
        mRecyclerZhihuSection.setHasFixedSize(true);
        mSwipeZhihuSection.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().getContent("福利", 10, 1);
            }
        });

        getPresenter().getContent("福利", 10, page);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        if (null != mSwipeZhihuSection) {
            mSwipeZhihuSection.setRefreshing(false);
        }
    }

    @Override
    public void showContent(ArrayList<AIList> aiList) {
        datas.clear();
        datas.addAll(aiList);
        adapter.notifyDataSetChanged();
    }
}
