package com.dudaizhong.news.modules.gank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.dudaizhong.news.base.BaseViewHolder;
import com.dudaizhong.news.modules.gank.adapter.viewholder.AIViewHolder;
import com.dudaizhong.news.modules.gank.domain.AIList;
import com.dudaizhong.news.modules.zhihu.domain.HotList;

import java.util.ArrayList;

/**
 * Created by Markable on 2016/11/22.
 */

public class AIAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<AIList> datas;
    private BaseViewHolder.OnRecyclerViewListener onRecyclerViewListener;
    private Context context;

    public AIAdapter(Context context, ArrayList<AIList> datas) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AIViewHolder(context, parent, onRecyclerViewListener);
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
