package com.dudaizhong.news.modules.zhihu.adapter.viewholder;

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
import com.dudaizhong.news.modules.zhihu.activity.ZhihuDetailActivity;
import com.dudaizhong.news.modules.zhihu.domain.ThemeDetail;
import com.orhanobut.logger.Logger;

import butterknife.Bind;

/**
 * Created by Markable on 2016/11/21.
 */

public class ThemeDetailViewHolder extends BaseViewHolder {


    @Bind(R.id.image_item)
    ImageView mImageItem;
    @Bind(R.id.content_item)
    TextView mContentItem;
    private String images;

    public ThemeDetailViewHolder(Context context, ViewGroup root, OnRecyclerViewListener onRecyclerViewListener) {
        super(context, root, R.layout.item_zhihu_daily, onRecyclerViewListener);
    }

    @Override
    public void bindData(Object o) {
        final ThemeDetail.StoriesBean datas = (ThemeDetail.StoriesBean) o;
        if (null != datas.images)
            images = datas.images.get(0);

        ImageLoader.load(getContext(),images,mImageItem);
        mContentItem.setText(Util.safeText(datas.title));

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().startActivity(ZhihuDetailActivity.getZhihuDetailIntent(getContext(), datas.id));
            }
        });
    }
}
