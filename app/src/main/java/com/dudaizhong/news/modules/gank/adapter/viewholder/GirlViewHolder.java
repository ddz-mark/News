package com.dudaizhong.news.modules.gank.adapter.viewholder;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseViewHolder;
import com.dudaizhong.news.base.utils.DensityUtil;
import com.dudaizhong.news.modules.gank.domain.AIList;

import butterknife.Bind;

/**
 * Created by Markable on 2016/11/23.
 */

public class GirlViewHolder extends BaseViewHolder {


    @Bind(R.id.image)
    ImageView mImage;
    private Context mContext;

    public GirlViewHolder(Context context, ViewGroup root, OnRecyclerViewListener onRecyclerViewListener) {
        super(context, root, R.layout.item_gril, onRecyclerViewListener);
        mContext = context;
    }

    @Override
    public void bindData(Object o) {
        AIList data = (AIList) o;

        Glide.with(getContext())
                .load(data.url)
                .placeholder(R.mipmap.ic_launcher)
                .into(mImage);
//        mImage.setMaxHeight(200 + );

        ViewGroup.LayoutParams params = mImage.getLayoutParams();
        //(数据类型)(最小值+Math.random()*(最大值-最小值+1))
        params.height = (int) (((int) (Math.random() * (100))) + DensityUtil.getWindowheight(mContext) / 3.0);
        mImage.setLayoutParams(params);

    }
}
