package com.dudaizhong.news.modules.zhihu.adapter.viewholder;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseViewHolder;
import com.dudaizhong.news.modules.zhihu.domain.ThemeList;

import butterknife.Bind;

/**
 * Created by Dudaizhong on 2016/10/1.
 */

public class ThemeViewHolder extends BaseViewHolder {

    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.name)
    TextView name;

    public ThemeViewHolder(Context context, ViewGroup root, OnRecyclerViewListener onRecyclerViewListener) {
        super(context, root, R.layout.item_fragment_theme, onRecyclerViewListener);
    }

    @Override
    public void bindData(Object o) {
        ThemeList.OthersBean data = (ThemeList.OthersBean) o;

        Glide.with(getContext())
                .load(data.getThumbnail())
                .placeholder(R.mipmap.ic_launcher)
                .into(image);

        name.setText(data.getName());
    }
}
