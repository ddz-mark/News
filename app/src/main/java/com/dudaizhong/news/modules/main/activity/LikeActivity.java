package com.dudaizhong.news.modules.main.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseActivity;
import com.dudaizhong.news.modules.main.adapter.LikeAdapter;
import com.dudaizhong.news.modules.main.domain.RealmLikeBean;
import com.dudaizhong.news.modules.main.presenter.LikePresenter;
import com.dudaizhong.news.modules.main.presenter.contract.LikeContract;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Markable on 2016/11/26.
 */

public class LikeActivity extends BaseActivity<LikePresenter> implements LikeContract.View {


    LikeAdapter adapter;
    ArrayList<RealmLikeBean> datas;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_like;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {

        setToolBar(mToolbar, "收藏");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        datas = new ArrayList<>();
        adapter = new LikeAdapter(this, datas);
        mRecyclerView.setAdapter(adapter);
        getPresenter().getContent(LikeActivity.this);
    }

    @Override
    public void showContent(ArrayList<RealmLikeBean> data) {
        datas.clear();
        datas.addAll(data);
        adapter.notifyDataSetChanged();
    }

}
