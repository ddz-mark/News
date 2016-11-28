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
import com.dudaizhong.news.modules.zhihu.activity.SectionDetailActivity;
import com.dudaizhong.news.modules.zhihu.domain.SectionList;

import butterknife.Bind;

/**
 * Created by Dudaizhong on 2016/10/1.
 */

public class SectionViewHolder extends BaseViewHolder {


    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.descriptionc)
    TextView descriptionc;
    @Bind(R.id.name)
    TextView name;

    public SectionViewHolder(Context context, ViewGroup root, OnRecyclerViewListener onRecyclerViewListener) {
        super(context, root, R.layout.item_fragment_section, onRecyclerViewListener);
    }

    @Override
    public void bindData(Object o) {
        final SectionList.DataBean dataBean = (SectionList.DataBean) o;

        ImageLoader.load(getContext(),dataBean.getThumbnail(),image);

        name.setText(dataBean.getName());
        descriptionc.setText(dataBean.getDescription());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().startActivity(SectionDetailActivity.getSectionDetailIntent(getContext(), dataBean.getId(), dataBean.getName()));
            }
        });
    }
}
