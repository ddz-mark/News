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
import com.dudaizhong.news.modules.zhihu.activity.ZhihuDetailActivity;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuList;

import butterknife.Bind;

/**
 * Created by Dudaizhong on 2016/9/21.
 */

public class DailyViewHolder extends BaseViewHolder {

    @Bind(R.id.image_item)
    ImageView imageItem;
    @Bind(R.id.content_item)
    TextView contentItem;

    //这里的布局记住高度是wrap_content。。。。
    public DailyViewHolder(Context context, ViewGroup root, OnRecyclerViewListener onRecyclerViewListener) {
        super(context, root, R.layout.item_zhihu_daily, onRecyclerViewListener);
    }

    @Override
    public void bindData(Object o) {
        final ZhihuList.StoriesBean data = (ZhihuList.StoriesBean) o;
        ImageLoader.load(getContext(),data.getImages().get(0),imageItem);

        contentItem.setText(data.getTitle());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().startActivity(ZhihuDetailActivity.getZhihuDetailIntent(getContext(),data.getId()));
            }
        });
    }
}
