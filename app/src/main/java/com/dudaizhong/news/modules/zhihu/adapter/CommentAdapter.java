package com.dudaizhong.news.modules.zhihu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.dudaizhong.news.base.BaseViewHolder;
import com.dudaizhong.news.modules.zhihu.adapter.viewholder.CommentViewHolder;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuShortCommentData;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * Created by Markable on 2016/11/21.
 */

public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<ZhihuShortCommentData.CommentsBean> datas;
    private BaseViewHolder.OnRecyclerViewListener onRecyclerViewListener;

    public CommentAdapter(Context context, ArrayList<ZhihuShortCommentData.CommentsBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentViewHolder(context,parent,onRecyclerViewListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BaseViewHolder) holder).bindData(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void setOnRecyclerViewListener(BaseViewHolder.OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }
}
