package com.dudaizhong.news.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Dudaizhong on 2016/9/21.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    public OnRecyclerViewListener onRecyclerViewListener;

    public BaseViewHolder(Context context, ViewGroup root, int res, OnRecyclerViewListener onRecyclerViewListener) {
        super(LayoutInflater.from(context).inflate(res, root, false));
        this.onRecyclerViewListener = onRecyclerViewListener;
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public abstract void bindData(T t);

    @Override
    public void onClick(View v) {
        if (onRecyclerViewListener != null) {
            onRecyclerViewListener.onItemClick(getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (onRecyclerViewListener != null) {
            onRecyclerViewListener.onItemLongClick(getAdapterPosition());
        }
        return true;
    }

    public interface OnRecyclerViewListener {
        void onItemClick(int position);
        boolean onItemLongClick(int position);
    }

    public Context getContext(){
        return itemView.getContext();
    }

}
