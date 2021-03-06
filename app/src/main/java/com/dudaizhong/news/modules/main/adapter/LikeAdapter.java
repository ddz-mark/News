package com.dudaizhong.news.modules.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.dudaizhong.news.base.BaseViewHolder;
import com.dudaizhong.news.modules.main.adapter.viewholder.LikeViewHolder;
import com.dudaizhong.news.modules.main.domain.RealmLikeBean;
import com.dudaizhong.news.modules.zhihu.adapter.viewholder.DailyViewHolder;
import com.dudaizhong.news.modules.zhihu.domain.HotList;

import java.util.ArrayList;

/**
 * Created by Markable on 2016/11/26.
 */

public class LikeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<RealmLikeBean> datas;
    private BaseViewHolder.OnRecyclerViewListener onRecyclerViewListener;
    private Context context;

    public LikeAdapter(Context context, ArrayList<RealmLikeBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LikeViewHolder(context, parent, onRecyclerViewListener);
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
