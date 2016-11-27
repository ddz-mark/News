package com.dudaizhong.news.modules.gank.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseFragment;
import com.dudaizhong.news.common.widget.LoadMoreRecyclerView;
import com.dudaizhong.news.modules.gank.adapter.AIAdapter;
import com.dudaizhong.news.modules.gank.domain.AIList;
import com.dudaizhong.news.modules.gank.presenter.AIPresenter;
import com.dudaizhong.news.modules.gank.presenter.contract.AIContract;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Markable on 2016/11/22.
 */

public class VideoFragment extends BaseFragment<AIPresenter> implements AIContract.View {
    @Bind(R.id.recycler_zhihu_section)
    LoadMoreRecyclerView mRecyclerZhihuSection;
    @Bind(R.id.swipe_zhihu_section)
    SwipeRefreshLayout mSwipeZhihuSection;

    private ArrayList<AIList> datas = new ArrayList<>();
    private AIAdapter adapter;
    private int currentPage = 1;
    private static final int NUM = 10;

    @Override
    protected AIPresenter createPresenter() {
        return new AIPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank;
    }

    @Override
    protected void initEventAndData() {
        mRecyclerZhihuSection.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerZhihuSection.setLoadMoreListener(new LoadMoreRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                getPresenter().getContent("休息视频", NUM, currentPage + 1);
            }
        });

        adapter = new AIAdapter(getContext(), datas);
        mRecyclerZhihuSection.setAdapter(adapter);
        mSwipeZhihuSection.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().getContent("休息视频", NUM, 1);
            }
        });

        getPresenter().getContent("休息视频", NUM, currentPage);
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
    public void showContent(ArrayList<AIList> aiList, int page) {
        currentPage = page;
        if (currentPage == 1 && null != datas) {
            datas.clear();
        }
        datas.addAll(aiList);
        mRecyclerZhihuSection.notifyDataChange(currentPage, aiList.size() * 10);
    }

}
