package com.dudaizhong.news.modules.gank.adapter.viewholder;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseViewHolder;
import com.dudaizhong.news.base.utils.Util;
import com.dudaizhong.news.modules.gank.domain.AIList;

import butterknife.Bind;

/**
 * Created by Markable on 2016/11/22.
 */

public class AIViewHolder extends BaseViewHolder {

    @Bind(R.id.image_item)
    ImageView mImageItem;
    @Bind(R.id.desc)
    TextView mDesc;
    @Bind(R.id.who)
    TextView mWho;
    @Bind(R.id.time)
    TextView mTime;

    public AIViewHolder(Context context, ViewGroup root, OnRecyclerViewListener onRecyclerViewListener) {
        super(context, root, R.layout.item_gank_ai, onRecyclerViewListener);
    }

    @Override
    public void bindData(Object o) {
        AIList data = (AIList) o;
        if(null != data.images){
            Glide.with(getContext())
                    .load(data.images.get(0))
                    .placeholder(R.mipmap.ic_launcher)
                    .into(mImageItem);
        }

        mDesc.setText(Util.safeText(data.desc));
        mWho.setText(Util.safeText(data.who));
        mTime.setText(Util.safeText(data.createdAt));
    }
}