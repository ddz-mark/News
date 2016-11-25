package com.dudaizhong.news.modules.gank.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseFragment;
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
    RecyclerView mRecyclerZhihuSection;
    @Bind(R.id.swipe_zhihu_section)
    SwipeRefreshLayout mSwipeZhihuSection;

    private ArrayList<AIList> datas;
    private AIAdapter adapter;
    private int page = 1;

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
        mRecyclerZhihuSection.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerZhihuSection.setHasFixedSize(true);

        datas = new ArrayList<>();
        adapter = new AIAdapter(getContext(),datas);
        mRecyclerZhihuSection.setAdapter(adapter);
        mSwipeZhihuSection.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().getContent("休息视频", 10, 1);
            }
        });

        getPresenter().getContent("休息视频", 10, page);
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
        Logger.d(aiList);
        adapter.notifyDataSetChanged();
    }

}
