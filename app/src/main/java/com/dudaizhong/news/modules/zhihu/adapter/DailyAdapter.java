package com.dudaizhong.news.modules.zhihu.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.dudaizhong.news.base.BaseViewHolder;
import com.dudaizhong.news.modules.zhihu.adapter.viewholder.DailyViewHolder;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuListNews;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dudaizhong on 2016/9/21.
 */

public class DailyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<ZhihuListNews.StoriesBean> datas;
    private BaseViewHolder.OnRecyclerViewListener onRecyclerViewListener;

    public DailyAdapter(Context context, ArrayList<ZhihuListNews.StoriesBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DailyViewHolder(context, parent, onRecyclerViewListener);
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
