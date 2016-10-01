package com.dudaizhong.news.modules.zhihu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.dudaizhong.news.base.BaseViewHolder;
import com.dudaizhong.news.modules.zhihu.adapter.viewholder.SectionViewHolder;
import com.dudaizhong.news.modules.zhihu.domain.SectionList;

import java.util.ArrayList;

/**
 * Created by Dudaizhong on 2016/10/1.
 */

public class SectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private BaseViewHolder.OnRecyclerViewListener onRecyclerViewListener;
    private Context context;
    private ArrayList<SectionList.DataBean> datas;

    public SectionAdapter(Context context,ArrayList<SectionList.DataBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SectionViewHolder(context,parent,onRecyclerViewListener);
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
