package com.dudaizhong.news.modules.gank.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dudaizhong.news.R;
import com.dudaizhong.news.app.Constants;
import com.dudaizhong.news.base.BaseFragment;
import com.dudaizhong.news.modules.gank.adapter.AIAdapter;
import com.dudaizhong.news.modules.gank.domain.AIList;
import com.dudaizhong.news.modules.gank.domain.VideoList;
import com.dudaizhong.news.modules.gank.presenter.AIPresenter;
import com.dudaizhong.news.modules.gank.presenter.contract.AIContract;
import com.dudaizhong.news.modules.zhihu.adapter.HotAdapter;
import com.dudaizhong.news.modules.zhihu.domain.HotList;
import com.dudaizhong.news.modules.zhihu.fragment.CommentFragment;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Collection;

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

    private ArrayList<AIList> datas;
    private AIAdapter adapter;
    private String type;
    private int page = 1;

    @Override
    protected AIPresenter createPresenter() {
        return new AIPresenter(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_hot;
    }

    public static AIFragment newInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.TYPE, type);
        AIFragment aiFragment = new AIFragment();
        aiFragment.setArguments(bundle);
        return aiFragment;
    }


    @Override
    protected void initEventAndData() {

        Bundle bundle = getArguments();
        if (null != bundle) {
            type = bundle.getString(Constants.TYPE);
        }

        mRecyclerZhihuSection.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerZhihuSection.setHasFixedSize(true);

        datas = new ArrayList<>();
        adapter = new AIAdapter(getContext(), datas);
        mRecyclerZhihuSection.setAdapter(adapter);
        mSwipeZhihuSection.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().getContent(type, 10, 1);
            }
        });

        getPresenter().getContent(type, 10, page);
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
