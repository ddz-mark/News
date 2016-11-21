package com.dudaizhong.news.modules.zhihu.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dudaizhong.news.R;
import com.dudaizhong.news.app.Constants;
import com.dudaizhong.news.base.BaseFragment;
import com.dudaizhong.news.modules.zhihu.adapter.CommentAdapter;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuShortCommentData;
import com.dudaizhong.news.modules.zhihu.presenter.CommentPresenter;
import com.dudaizhong.news.modules.zhihu.presenter.contract.CommentContract;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Markable on 2016/11/20.
 */

public class CommentFragment extends BaseFragment<CommentPresenter> implements CommentContract.View {

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    private ArrayList<ZhihuShortCommentData.CommentsBean> datas;
    private CommentAdapter adapter;
    private int id = 0;
    private int kind = 0;

    @Override
    protected CommentPresenter createPresenter() {
        return new CommentPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_short_comment;
    }

    public static CommentFragment newInstance(int id, int kind) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.ID, id);
        bundle.putInt(Constants.KIND_FRAGMENT, kind);
        CommentFragment shortCommentFragment = new CommentFragment();
        shortCommentFragment.setArguments(bundle);
        return shortCommentFragment;
    }

    @Override
    protected void initEventAndData() {

        Bundle bundle = getArguments();
        if (null != bundle) {
            id = bundle.getInt(Constants.ID);
            kind = bundle.getInt(Constants.KIND_FRAGMENT);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);

        datas = new ArrayList<>();
        adapter = new CommentAdapter(getContext(), datas);
        mRecyclerView.setAdapter(adapter);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        showLoading();
        getPresenter().getContent(id,kind);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showShortComment(ZhihuShortCommentData zhihuShortCommentData) {
        datas.clear();
        datas.addAll(zhihuShortCommentData.comments);
        adapter.notifyDataSetChanged();
    }


}
