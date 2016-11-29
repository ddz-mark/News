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
import com.dudaizhong.news.modules.zhihu.adapter.HotAdapter;
import com.dudaizhong.news.modules.zhihu.domain.HotList;
import com.dudaizhong.news.modules.zhihu.presenter.HotPresenter;
import com.dudaizhong.news.modules.zhihu.presenter.contract.HotContract;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Dudaizhong on 2016/9/18.
 */

public class HotFragment extends BaseFragment<HotPresenter> implements HotContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.recycler_zhihu_section)
    RecyclerView recyclerZhihuSection;
    @Bind(R.id.swipe_zhihu_section)
    SwipeRefreshLayout swipeZhihuSection;
    @Bind(R.id.error)
    LinearLayout mError;

    private ArrayList<HotList.RecentBean> datas;
    private HotAdapter adapter;

    @Override
    protected HotPresenter createPresenter() {
        return new HotPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_hot;
    }

    @Override
    protected void initEventAndData() {
        recyclerZhihuSection.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerZhihuSection.setHasFixedSize(true);

        datas = new ArrayList<>();
        adapter = new HotAdapter(getContext(), datas);
        recyclerZhihuSection.setAdapter(adapter);
        swipeZhihuSection.setOnRefreshListener(this);
        showLoading();
        getPresenter().getContent(getContext());
    }

    @Override
    public void showLoading() {
        swipeZhihuSection.setProgressViewOffset(false, 0, DensityUtil.dip2px(getContext(), 24));
        swipeZhihuSection.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        if (null != swipeZhihuSection)
            swipeZhihuSection.setRefreshing(false);
        mError.setVisibility(View.GONE);
        recyclerZhihuSection.setVisibility(View.VISIBLE);
    }


    @Override
    public void onRefresh() {
        getPresenter().getContent(getContext());
    }

    @Override
    public void showContent(HotList hotList) {
        datas.clear();
        datas.addAll(hotList.getRecent());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        mError.setVisibility(View.VISIBLE);
        recyclerZhihuSection.setVisibility(View.GONE);
    }

    @OnClick(R.id.error)
    public void onClick() {
        showLoading();
        getPresenter().getContent(getContext());
    }
}
