package com.dudaizhong.news.modules.gank.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseViewHolder;
import com.dudaizhong.news.base.utils.ImageLoader;
import com.dudaizhong.news.base.utils.Util;
import com.dudaizhong.news.modules.gank.activity.AIActivity;
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
        final AIList data = (AIList) o;
        if(null != data.images){
            ImageLoader.load(getContext(),data.images.get(0),mImageItem);
        }

        mDesc.setText(Util.safeText(data.desc));
        mWho.setText(Util.safeText(data.who));
        mTime.setText(Util.safeText(data.createdAt));

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().startActivity(AIActivity.getAIActivityIntent(getContext(),data.type,data.url));
            }
        });

    }
}
