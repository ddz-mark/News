package com.dudaizhong.news.modules.zhihu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.dudaizhong.news.base.BaseViewHolder;
import com.dudaizhong.news.modules.zhihu.adapter.viewholder.PhotoViewHolder;
import com.dudaizhong.news.modules.zhihu.adapter.viewholder.ThemeDetailViewHolder;
import com.dudaizhong.news.modules.zhihu.domain.ThemeDetail;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * Created by Markable on 2016/11/21.
 */

public class ThemeDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int PHOTO = 0;
    private static final int CONTENT = 1;
    private Context mContext;
    private BaseViewHolder.OnRecyclerViewListener onRecyclerViewListener;
    private ArrayList<ThemeDetail.StoriesBean> datas;
    private ThemeDetail mThemeDetail;

    public ThemeDetailAdapter(Context context, ArrayList<ThemeDetail.StoriesBean> datas, ThemeDetail themeDetail) {
        mContext = context;
        this.datas = datas;
        mThemeDetail = themeDetail;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case PHOTO:
                return new PhotoViewHolder(mContext, parent, onRecyclerViewListener);
            case CONTENT:
                return new ThemeDetailViewHolder(mContext, parent, onRecyclerViewListener);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case PHOTO:
                ((BaseViewHolder) holder).bindData(mThemeDetail);
                break;
            case CONTENT:
                ((BaseViewHolder) holder).bindData(datas.get(position - 1));
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return PHOTO;
        } else {
            return CONTENT;
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size() + 1;
    }

    public void addData(ThemeDetail themeDetail){
        mThemeDetail = themeDetail;
        datas = (ArrayList<ThemeDetail.StoriesBean>) themeDetail.stories;
        notifyDataSetChanged();
    }

    public void setOnRecyclerViewListener(BaseViewHolder.OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

}
