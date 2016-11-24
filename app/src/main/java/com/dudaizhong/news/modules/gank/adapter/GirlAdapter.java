package com.dudaizhong.news.modules.gank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.dudaizhong.news.base.BaseViewHolder;
import com.dudaizhong.news.modules.gank.adapter.viewholder.GirlViewHolder;
import com.dudaizhong.news.modules.gank.domain.AIList;

import java.util.ArrayList;

/**
 * Created by Markable on 2016/11/23.
 */

public class GirlAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<AIList> datas;
    private BaseViewHolder.OnRecyclerViewListener onRecyclerViewListener;
    private Context context;

    public GirlAdapter(ArrayList<AIList> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GirlViewHolder(context, parent, onRecyclerViewListener);
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
