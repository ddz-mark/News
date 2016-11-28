package com.dudaizhong.news.modules.zhihu.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseViewHolder;
import com.dudaizhong.news.base.utils.ImageLoader;
import com.dudaizhong.news.base.utils.Util;
import com.dudaizhong.news.modules.zhihu.activity.ZhihuDetailActivity;
import com.dudaizhong.news.modules.zhihu.domain.SectionDetail;

import butterknife.Bind;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Markable on 2016/11/21.
 */

public class SectionDetailViewHolder extends BaseViewHolder {

    @Bind(R.id.image_item)
    ImageView mImageItem;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.date)
    TextView mDate;

    public SectionDetailViewHolder(Context context, ViewGroup root, OnRecyclerViewListener onRecyclerViewListener) {
        super(context, root, R.layout.item_section_detail, onRecyclerViewListener);
    }

    @Override
    public void bindData(Object o) {
        final SectionDetail.StoriesBean datas = (SectionDetail.StoriesBean) o;

        ImageLoader.load(getContext(),Util.safeText(datas.images.get(0)),mImageItem);
        mTitle.setText(Util.safeText(datas.title));
        mDate.setText(Util.safeText(datas.date));

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().startActivity(ZhihuDetailActivity.getZhihuDetailIntent(getContext(),datas.id));
            }
        });
    }
}
