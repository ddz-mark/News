package com.dudaizhong.news.modules.gank.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseFragment;
import com.dudaizhong.news.modules.gank.domain.AIList;
import com.dudaizhong.news.modules.gank.presenter.AIPresenter;
import com.dudaizhong.news.modules.gank.presenter.contract.AIContract;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Markable on 2016/11/22.
 */

public class AIFragment extends BaseFragment<AIPresenter> implements AIContract.View {

    @Bind(R.id.recycler_zhihu_section)
    RecyclerView mRecyclerZhihuSection;
    @Bind(R.id.swipe_zhihu_section)
    SwipeRefreshLayout mSwipeZhihuSection;

    @Override
    protected AIPresenter createPresenter() {
        return new AIPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_hot;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showContent(AIList aiList) {

    }

}
