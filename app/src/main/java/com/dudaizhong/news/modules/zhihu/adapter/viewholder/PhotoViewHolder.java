package com.dudaizhong.news.modules.zhihu.adapter.viewholder;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseViewHolder;
import com.dudaizhong.news.base.utils.Util;
import com.dudaizhong.news.modules.zhihu.domain.ThemeDetail;
import com.orhanobut.logger.Logger;

import butterknife.Bind;

/**
 * Created by Markable on 2016/11/21.
 */

public class PhotoViewHolder extends BaseViewHolder {

    @Bind(R.id.imageView)
    ImageView mImageView;
    @Bind(R.id.title)
    TextView mTitle;

    public PhotoViewHolder(Context context, ViewGroup root, OnRecyclerViewListener onRecyclerViewListener) {
        super(context, root, R.layout.item_theme_detail_photo, onRecyclerViewListener);
    }

    @Override
    public void bindData(Object o) {
        ThemeDetail data = (ThemeDetail) o;
        Glide.with(getContext())
                .load(data.background)
                .placeholder(R.mipmap.ic_launcher)
                .into(mImageView);
        mTitle.setText(Util.safeText(data.description));

    }
}
